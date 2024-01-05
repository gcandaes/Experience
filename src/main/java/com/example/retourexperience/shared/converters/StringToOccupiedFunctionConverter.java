package com.example.retourexperience.shared.converters;

import com.example.retourexperience.ui.model.entity.OccupiedFunction;
import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringToOccupiedFunctionConverter implements Converter<String, OccupiedFunction> {

    @Override
    public OccupiedFunction convert(String source) {
        OccupiedFunction occupiedFunction = new OccupiedFunction();
        occupiedFunction.setFunctionName(FunctionEnum.valueOf(source));
        return occupiedFunction;
    }
}