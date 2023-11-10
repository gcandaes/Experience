package com.example.retourexperience.ui.controller;

import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.requestDto.UpdateExperienceDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/experience")//http://localhost:8080/experience
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping()
    public ResponseEntity<List<Experience>> getExperiences(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "limit", defaultValue = "25") int limit,
                                                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        List<Experience> returnValue = experienceService.getExperiences();
        if (returnValue.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{experienceId}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Experience> getExperience(@PathVariable String experienceId) {

        Optional<Experience> returnValue = experienceService.getExperience(experienceId);


        if (returnValue.isPresent()) {
            return new ResponseEntity<>(returnValue.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping(path = "/{experienceId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public Experience updateExperience(@PathVariable String experienceId, @Valid @RequestBody UpdateExperienceDto experienceDto) {
        Experience updatedExperience = experienceService.updateExperience(experienceId, experienceDto);
        return updatedExperience;
    }

    @DeleteMapping(path = "/{experienceId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable String experienceId) {
        experienceService.deleteExperience(experienceId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/registration")
    public ModelAndView showExperienceForm(Model model) {
        model.addAttribute("experience", new Experience());

        return new ModelAndView("experienceForm", (Map<String, ?>) model);
    }


    @PostMapping(path = "/registration")
    public ModelAndView createExperience(@Valid @ModelAttribute("experience") Experience experience, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return new ModelAndView("experienceForm");
        }

        experienceService.createExperience(experience);

        return new ModelAndView("redirect:/index"); // Exemple de redirection vers une page de connexion
    }
}


