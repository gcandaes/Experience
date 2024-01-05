package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, String>, JpaSpecificationExecutor<Experience> {
    @Query("SELECT e FROM Experience e WHERE e.place.id = ?1")
    Experience findByPlaceId(String placeId);

    @Query("SELECT e FROM Experience e WHERE e.user.userId=?1")
    List<Experience> findByUserIdentification(String userId);

}
