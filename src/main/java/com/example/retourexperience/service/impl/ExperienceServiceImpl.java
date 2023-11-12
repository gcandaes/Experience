package com.example.retourexperience.service.impl;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.ExperienceMapper;
import com.example.retourexperience.repository.*;
import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.HumanResources;
import com.example.retourexperience.ui.model.entity.UserRest;
import com.example.retourexperience.ui.model.requestDto.UpdateExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    ExperienceRepository experienceRepository;
    PlaceRepository placeRepository;
    EmployerRepository employerRepository;
    WorkRepository workRepository;
    HumanResourcesRepository humanResourcesRepository;
    ExperienceMapper experienceMapper;
    Utils utils;

    public ExperienceServiceImpl() {
    }

    //annotation pour ne pas avoir a faire un new
    @Autowired
    public ExperienceServiceImpl(Utils utils, ExperienceMapper experienceMapper, ExperienceRepository experienceRepository,
                                 PlaceRepository placeRepository, EmployerRepository employerRepository,
                                 WorkRepository workRepository, HumanResourcesRepository humanResourcesRepository) {
        this.utils = utils;
        this.experienceMapper = experienceMapper;
        this.experienceRepository = experienceRepository;
        this.placeRepository = placeRepository;
        this.employerRepository = employerRepository;
        this.workRepository = workRepository;
        this.humanResourcesRepository = humanResourcesRepository;
    }

    @Override
    public List<Experience> getExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Optional<Experience> getExperience(String experienceId) {
        return experienceRepository.findById(experienceId);
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
        HumanResources hr = experience.getEmployer().getHumanResources();
        //save human resources if only we have a phone number or a mail
        if(!hr.getEmail().isEmpty() || !hr.getPhoneNumber().isEmpty()){
            humanResourcesRepository.save(experience.getEmployer().getHumanResources());
        }
        else{
            experience.getEmployer().setHumanResources(null);
        }
        employerRepository.save(experience.getEmployer());
        placeRepository.save(experience.getPlace());
        workRepository.save(experience.getWork());
        experienceRepository.save(experience);
    }
}
