package com.example.retourexperience.mapper;

import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Place;
import com.example.retourexperience.ui.model.requestDto.PlaceDtoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceMapper {
    Utils utils;

    public PlaceMapper() {
    }

    @Autowired
    public PlaceMapper(Utils utils) {
        this.utils = utils;
    }

    public Place mapToPlaceEntity(PlaceDtoModel placeDto) {

        return new Place(utils.generateUserId(),
                placeDto.getName(),
                placeDto.getZipCode(),
                placeDto.getCity()
        );
    }
}
