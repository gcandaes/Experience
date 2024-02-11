package com.example.retourexperience.service;

import com.example.retourexperience.ui.model.entity.Experience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ExperienceService {
    List<Experience> getExperiences();

    Optional<Experience> getExperience(String experienceId);

    Experience findByPlace(String placeId);

    Experience updateExperience(String experienceId, Experience experienceDto);

    void deleteExperience(String experienceId);

    void createExperience(Experience experience);

    Page<Experience> findBySearchCriteria(Specification<Experience> spec, Pageable page);

}
