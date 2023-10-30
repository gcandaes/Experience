package com.example.retourexperience.mapper;

import com.example.retourexperience.shared.Utils;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.requestDto.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceMapper {
    Utils utils;

    public ExperienceMapper() {
    }

    @Autowired
    public ExperienceMapper(Utils utils) {
        this.utils = utils;
    }


    public Experience mapToExperienceEntity(ExperienceDto experienceDto) {

        return new Experience(utils.generateUserId(),
                experienceDto.getPlace(),
                experienceDto.getEmployer(),
                experienceDto.getWork()/*,
                experienceDto.getConditions(),
                experienceDto.getPractical()*/
        );
    }
}