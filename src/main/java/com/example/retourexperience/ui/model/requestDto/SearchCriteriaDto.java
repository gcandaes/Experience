package com.example.retourexperience.ui.model.requestDto;

import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaDto {
    //@Size(min = 2, max = 25, message = "le nom de l'établissement doit comporter au moins 2 caractères")
    private String name;

    //@Size(min = 2, max = 5, message = "le code postal doit être composé de 5 chiffres et le département de 2 chiffres")
    private String zipCode;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = FunctionEnum.class)
    private Set<FunctionEnum> occupiedFunction;

    private String startDate;
    private String endDate;


}
