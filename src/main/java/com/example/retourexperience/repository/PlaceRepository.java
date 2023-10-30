package com.example.retourexperience.repository;

import com.example.retourexperience.ui.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, String> {
    List<Place> findByNameLike(String placeName);

}
