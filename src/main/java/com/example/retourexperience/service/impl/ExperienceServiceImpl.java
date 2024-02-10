package com.example.retourexperience.service.impl;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.ExperienceMapper;
import com.example.retourexperience.repository.*;
import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.service.UserService;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    ExperienceRepository experienceRepository;
    PlaceRepository placeRepository;
    EmployerRepository employerRepository;
    WorkRepository workRepository;
    UserRepository userRepository;
    HumanResourcesRepository humanResourcesRepository;
    ExperienceMapper experienceMapper;
    Utils utils;

    UserService userService;

    public ExperienceServiceImpl() {
    }

    //annotation pour ne pas avoir a faire un new
    @Autowired
    public ExperienceServiceImpl(Utils utils, ExperienceMapper experienceMapper, ExperienceRepository experienceRepository,
                                 PlaceRepository placeRepository, EmployerRepository employerRepository,
                                 WorkRepository workRepository, HumanResourcesRepository humanResourcesRepository,
                                 UserRepository userRepository, UserService userService) {
        this.utils = utils;
        this.experienceMapper = experienceMapper;
        this.experienceRepository = experienceRepository;
        this.placeRepository = placeRepository;
        this.employerRepository = employerRepository;
        this.workRepository = workRepository;
        this.humanResourcesRepository = humanResourcesRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public List<Experience> getExperiences() {
       // return experienceRepository.findAll();

        return experienceRepository.findByUserIdentification(getCurrentUserId());
    }

    @Override
    public Optional<Experience> getExperience(String experienceId) {
        return experienceRepository.findById(experienceId);
    }

    @Override
    public Experience findByPlace(String placeId) {
        return experienceRepository.findByPlaceId(placeId);
    }

    @Override
    public Experience updateExperience(String experienceId, UpdateExperienceDto experienceDto) {
        return null;
    }

    @Override
    public Experience updateExperience(String experienceId, Experience experience) {
        Experience experienceBeforeUpdate = experienceRepository.findById(experienceId).orElseThrow(
                () -> new UserServiceException("The experience you want to modify doesn't exist")
        );

        Experience experienceUpdated = experienceMapper.mapToUpdateExperienceEntity(experience, experienceBeforeUpdate);
        createExperience(experienceUpdated);

        return experienceUpdated;
    }

    @Override
    public void deleteExperience(String experienceId) {
        experienceRepository.delete(getExperience(experienceId).orElseThrow(() -> new UserServiceException("The experience you want to delete doesn't exist")));
    }

    @Override
    public void createExperience(Experience experience) {
        UserRest currentUser = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //UserRest currentUser = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        experience.setUser(currentUser);
        //grace au cascade ALL defini sur les entités, plus besoin d'enregistrer chacunes des sous tables

 /*       HumanResources hr = experience.getEmployer().getHumanResources();

        //save human resources if only we have a phone number or a mail
        if(!hr.getEmail().isEmpty() || !hr.getPhoneNumber().isEmpty()){
            humanResourcesRepository.save(experience.getEmployer().getHumanResources());
        }
        else{
            experience.getEmployer().setHumanResources(null);
        }*/
/*        employerRepository.save(experience.getEmployer());
        placeRepository.save(experience.getPlace());
        workRepository.save(experience.getWork());*/
        experienceRepository.save(experience);
    }

    @Override
    @Transactional
    public Page<Experience> findBySearchCriteria(Specification<Experience> spec, Pageable page) {
        Page<Experience> searchResult = experienceRepository.findAll(spec, page);
        return searchResult;
    }

    String getCurrentUserId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserRest currentUser = userRepository.findByUserName(username);

        return currentUser.getUserId();
    }
}
