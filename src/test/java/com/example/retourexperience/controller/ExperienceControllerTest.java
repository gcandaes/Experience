package com.example.retourexperience.controller;

import com.example.retourexperience.service.ExperienceService;
import com.example.retourexperience.service.UserService;
import com.example.retourexperience.ui.controller.ExperienceController;
import com.example.retourexperience.ui.model.entity.*;
import com.example.retourexperience.ui.model.enumeration.FunctionEnumBis;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
@WebMvcTest(ExperienceController.class)
@RunWith(MockitoJUnitRunner.class)
public class ExperienceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExperienceService experienceService;

    @InjectMocks
    ExperienceController experienceController;
   /* @MockBean
    private LocalValidatorFactoryBean validator;*/
UserRest user1 = new UserRest("1", "user@company.com", "password","username", new HashSet<>(),
           true,true,true,"USER");
    Place place1 = new Place("1", "auberge de la foret", "62575", "Blendecques"  );

    HumanResources humanResources1 = new HumanResources("1", "Serge", "Haller", "0405060708", "auberge@gmail.com");
    Employer employer1 = new Employer("1", "Serge", "Haller", "0405060708", "auberge@gmail.com",humanResources1 );

    ArrayList functions =  new ArrayList<FunctionEnumBis>(Arrays.asList(FunctionEnumBis.BARMAN, FunctionEnumBis.SERVEUR));

    Work work1 = new Work("1", functions,
            "servir le client", "1 an", new Date(2023,11,01),  new Date(2023,11,30));

    Experience experience1 = new Experience("1",user1,place1,employer1, work1);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(experienceController).build();
    }

    @Test
    public void getExperienceList() throws Exception {
        List<Experience> experienceList = Arrays.asList(experience1);
        Mockito.when(experienceService.getExperiences()).thenReturn(experienceList);
        mockMvc.perform(MockMvcRequestBuilders.
                get("http://localhost:8080/experience/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].place",is(place1) ));
    }

/*    @Test
    public void testShowRegistrationForm() throws Exception {

        mockMvc.perform(get("/experience/registration"))
                //.andExpect(status().is2xxSuccessful())
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
    }*/
}
