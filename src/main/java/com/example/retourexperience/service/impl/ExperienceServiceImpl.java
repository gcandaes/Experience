package com.example.retourexperience.service.impl;

import com.example.retourexperience.exceptions.UserServiceException;
import com.example.retourexperience.mapper.ExperienceMapper;
import com.example.retourexperience.repository.EmployerRepository;
import com.example.retourexperience.repository.ExperienceRepository;
import com.example.retourexperience.repository.PlaceRepository;
import com.example.retourexperience.repository.WorkRepository;
import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Experience;
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
    ExperienceMapper experienceMapper;

    Utils utils;


    public ExperienceServiceImpl() {
    }

    //annotation pour ne pas avoir a faire un new
    @Autowired
    public ExperienceServiceImpl(Utils utils, ExperienceMapper experienceMapper, ExperienceRepository experienceRepository,
                                 PlaceRepository placeRepository, EmployerRepository employerRepository, WorkRepository workRepository) {
        this.utils = utils;
        this.experienceMapper = experienceMapper;
        this.experienceRepository = experienceRepository;
        this.placeRepository = placeRepository;
        this.employerRepository = employerRepository;
        this.workRepository = workRepository;
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
    public void deleteExperience(String experienceId) {
        experienceRepository.delete(getExperience(experienceId).orElseThrow(() -> new UserServiceException("The experience you want to delete doesn't exist")));
    }

    @Override
    public void createExperience(Experience experience) {
        employerRepository.save(experience.getEmployer());
        placeRepository.save(experience.getPlace());
        workRepository.save(experience.getWork());
        experienceRepository.save(experience);
    }
}
