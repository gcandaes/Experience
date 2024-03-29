package com.example.retourexperience.service;

import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.requestDto.PlaceDtoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PlaceService {
    List<Place> findPlace(String placeName);
}
