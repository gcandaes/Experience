package com.example.retourexperience.service.impl;

import com.example.retourexperience.search.SearchCriteria;
import com.example.retourexperience.service.SearchService;
import com.example.retourexperience.ui.model.requestDto.SearchCriteriaDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public List<SearchCriteria> getListSearchCriteria(SearchCriteriaDto searchCriteriaDto) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        if (!searchCriteriaDto.getName().isEmpty()) {
            searchCriteriaList.add(new SearchCriteria("name", "placeNameEQ", searchCriteriaDto.getName()));
        }
        if (!searchCriteriaDto.getZipCode().isEmpty()) {
            //dans le cas ou on indique un numero de département
            if (searchCriteriaDto.getZipCode().length() == 2) {
                searchCriteriaList.add(new SearchCriteria("zipCode", "placeZipCodeBW", searchCriteriaDto.getZipCode()));
            }
            //dans le cas ou on indique un code postal
            else {
                searchCriteriaList.add(new SearchCriteria("zipCode", "placeZipCodeEQ", searchCriteriaDto.getZipCode()));
            }
        }
        if (!searchCriteriaDto.getStartDate().isEmpty()) {
            searchCriteriaList.add(new SearchCriteria("startDate", "workStartDateGE", searchCriteriaDto.getStartDate()));
        }
        if (!searchCriteriaDto.getEndDate().isEmpty() ) {
            searchCriteriaList.add(new SearchCriteria("endDate", "workEndDateLE", searchCriteriaDto.getEndDate()));
        }
        if(!searchCriteriaDto.getOccupiedFunction().isEmpty()){
            searchCriteriaList.add(new SearchCriteria("occupiedFunction", "occupiedFunction", searchCriteriaDto.getOccupiedFunction()));
        }

        return searchCriteriaList;
    }
}
