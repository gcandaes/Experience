package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExperienceRepository extends JpaRepository<Experience, String> {
    @Query("SELECT e FROM Experience e WHERE e.place.id = ?1")
    Experience findByPlaceId(String placeId);

}
