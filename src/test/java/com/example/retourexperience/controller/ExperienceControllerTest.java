package com.example.retourexperience.controller;

import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.service.UserService;
import com.example.retourexperience.ui.controller.ExperienceController;
import com.example.retourexperience.ui.model.entity.Experience;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(ExperienceController.class)
public class ExperienceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExperienceService experienceService;

   /* @MockBean
    private LocalValidatorFactoryBean validator;*/


    @Test
    public void testShowRegistrationForm() throws Exception {

        mockMvc.perform(get("/experience/registration"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("experienceForm"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("experience"));
    }

    @Test
    public void testSubmitRegistrationExperienceForm() throws Exception {
        mockMvc.perform(post("/experience/registration"))
                        //.flashAttr("experience", new Experience()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
