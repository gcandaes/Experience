package com.example.retourexperience;

import com.example.retourexperience.service.impl.ExperienceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/test-context.xml")
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @Qualifier("experienceServiceImpl")
    ExperienceServiceImpl experienceServiceImpl;

    @Test
    public void testShowRegistrationForm() throws Exception {

        mockMvc.perform(get("/register"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("register"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("userDetails"));
    }

    @Test
    public void testSubmitWatchlistItemForm() throws Exception {
        mockMvc.perform(post("/register"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
