package com.example.retourexperience.ui.controller;

import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.enumeration.FunctionEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/list")
    public ModelAndView showExperiencesList(Model model) {
        ResponseEntity<List<Experience>> listExperiences = getExperiences(1, 25, "desc");

        model.addAttribute("listeExperience", listExperiences.getBody());

        return new ModelAndView("listeExperiences", (Map<String, ?>) model);
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
    public Experience updateExperience(@PathVariable String experienceId, @Valid @RequestBody Experience experienceDto) {
        Experience updatedExperience = experienceService.updateExperience(experienceId, experienceDto);
        return updatedExperience;
    }

    @PostMapping("/modify/{experienceId}")
    public ModelAndView updateExperience(@Valid @ModelAttribute("experience") Experience experience, BindingResult result, Model model, @PathVariable String experienceId) {
        if (result.hasErrors()) {
            return new ModelAndView("experienceForm");
        }
        experienceService.updateExperience(experienceId, experience);
//TODO: corriger le fait que une nouvelle entrée soit ajoutée et non modifiée
        model.addAttribute("isEditMode", true);

        return new ModelAndView("redirect:/index");
    }
    @DeleteMapping(path = "/{experienceId}")
    public ResponseEntity<Void> delete(@PathVariable String experienceId) {
        experienceService.deleteExperience(experienceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/delete/{experienceId}")
    public ModelAndView deleteExperience(@PathVariable String experienceId) {
        experienceService.deleteExperience(experienceId);
        return new ModelAndView("redirect:/experience/list");
    }

    @GetMapping("/registration")
    public ModelAndView showExperienceForm(Model model) {
        model.addAttribute("experience", new Experience());
        model.addAttribute("occupiedFunctionValuesEnum", FunctionEnum.values());
        model.addAttribute("listFunctionsToSelect", new HashSet<>());

        return new ModelAndView("experienceForm", (Map<String, ?>) model);
    }

    @GetMapping("/modify/{experienceId}")
    public ModelAndView showExperienceFormToModify(@PathVariable String experienceId, Model model) {
        Experience experience = experienceService.getExperience(experienceId).orElse(new Experience());

        model.addAttribute("experience", experience);
        model.addAttribute("action", "modify");
        model.addAttribute("experienceId", experienceId);

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

    @PostMapping(path = "",
            consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Experience> createExperience(@Valid @ModelAttribute("experience") Experience experience) {
            experienceService.createExperience(experience);
            return new ResponseEntity<Experience>(experience, HttpStatus.CREATED);
    }

}


