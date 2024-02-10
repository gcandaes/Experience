package com.example.retourexperience.service;

import com.example.retourexperience.search.SearchCriteria;
import com.example.retourexperience.ui.model.requestDto.SearchCriteriaDto;

import java.util.List;

public interface SearchService {
    List<SearchCriteria> getListSearchCriteria(SearchCriteriaDto searchCriteriaDto);
}
