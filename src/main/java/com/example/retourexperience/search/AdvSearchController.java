package com.example.retourexperience.search;

import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.service.PlaceService;
import com.example.retourexperience.service.SearchService;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.requestDto.SearchCriteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/search")
public class AdvSearchController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private SearchService searchService;

    @Autowired
    private ExperienceService experienceService;

    @GetMapping()
    public ModelAndView showSearchForm(Model model) {

        model.addAttribute("searchCriteriaDto", new SearchCriteriaDto("", "", null, "", ""));

        return new ModelAndView("search", (Map<String, ?>) model);
    }

    @PostMapping(path = "/criteria")
    public ModelAndView transformData(@ModelAttribute("searchCriteriaDto") SearchCriteriaDto searchCriteriaDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("search");
        }

        List<SearchCriteria> criteriaList = searchService.getListSearchCriteria(searchCriteriaDto);
        ExperienceSearchDto experienceSearchDto = new ExperienceSearchDto(criteriaList, "ALL");

        APIResponse apiResponse = new APIResponse();
        ExperienceSpecificationBuilder builder = new ExperienceSpecificationBuilder();

        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(experienceSearchDto.getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(0, 10,
                Sort.by("id")
                        .ascending());
                        /*.and(Sort.by("city"))
                        .ascending());*/

        Page<Experience> placePage = experienceService.findBySearchCriteria(builder.build(), page);
        List<Experience> listPlacePage = placePage.toList();
        apiResponse.setData(listPlacePage);
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved employee record");

        List<Experience> listExperiences = new ArrayList<>();
        for (Experience p : listPlacePage) {
/*
            listExperiences.add(experienceService.findByPlace(p.getId()));
*/
            listExperiences.add(experienceService.findByPlace(p.getId()));
        }
        model.addAttribute("listeExperience", listPlacePage);

        return new ModelAndView("listeExperiences", (Map<String, ?>) model);
    }

    @PostMapping(path = "/place", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<APIResponse> searchPlaces
            (@RequestParam(name = "pageNum",
                    defaultValue = "0") int pageNum,
             @RequestParam(name = "pageSize",
                     defaultValue = "10") int pageSize,
             @RequestBody ExperienceSearchDto
                     experienceSearchDto) {
        APIResponse apiResponse = new APIResponse();
        ExperienceSpecificationBuilder builder = new ExperienceSpecificationBuilder();
        List<SearchCriteria> criteriaList =
                experienceSearchDto.getSearchCriteriaList();
        if (criteriaList != null) {
            criteriaList.forEach(x ->
            {
                x.setDataOption(experienceSearchDto
                        .getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize,
                Sort.by("id")
                        .ascending());
                        /*.and(Sort.by("city"))
                        .ascending());*/

        Page<Experience> placePage =
                experienceService.findBySearchCriteria(builder.build(),
                        page);
        apiResponse.setData(placePage.toList());
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved employee record");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());

    }
}