package com.example.retourexperience.service.impl;

import com.example.retourexperience.repository.PlaceRepository;
import com.example.retourexperience.service.PlaceService;
import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.requestDto.PlaceDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
