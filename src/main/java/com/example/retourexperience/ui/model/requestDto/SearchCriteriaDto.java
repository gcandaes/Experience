package com.example.retourexperience.ui.model.requestDto;

import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import com.example.retourexperience.ui.model.enumeration.FunctionEnumBis;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaDto {
    private String name;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = FunctionEnumBis.class)
    private ArrayList<FunctionEnumBis> occupiedFunctions;

    private String startDate;
    private String endDate;
}
