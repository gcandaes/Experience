package com.example.retourexperience.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

}