package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, String> {
}
