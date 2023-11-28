package com.example.retourexperience.service.impl;

import com.example.retourexperience.repository.PlaceRepository;
import com.example.retourexperience.service.PlaceService;
import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.requestDto.PlaceDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public Place createPlace(PlaceDtoModel placeDtoModel) {
        return null;
    }

    @Override
    public List<Place> findPlace(String placeName) {
        List<Place> listPlace = placeRepository.findByNameLike(placeName);
        return listPlace;
    }

    @Override
    @Transactional
    public Page<Place> findBySearchCriteria(Specification<Place> spec, Pageable page) {
        Page<Place> searchResult = placeRepository.findAll(spec, page);
        return searchResult;
    }
}
