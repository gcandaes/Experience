package com.example.retourexperience.service;

import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.requestDto.PlaceDtoModel;

import java.util.List;

public interface PlaceService {
    Place createPlace(PlaceDtoModel placeDtoModel);

    List<Place> findPlace(String placeName);

}
