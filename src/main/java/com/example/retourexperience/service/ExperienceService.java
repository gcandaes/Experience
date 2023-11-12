package com.example.retourexperience.service;

import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.requestDto.UpdateExperienceDto;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    List<Experience> getExperiences();
    Optional<Experience> getExperience(String experienceId);
    Experience updateExperience(String experienceId, UpdateExperienceDto experienceDto);
    Experience updateExperience(String experienceId, Experience experienceDto);
    void deleteExperience(String experienceId);
    void createExperience(Experience experience);
}
