package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String>, JpaSpecificationExecutor<Place> {
    List<Place> findByNameLike(String placeName);

}
