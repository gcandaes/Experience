package com.example.retourexperience.controller;

import com.example.retourexperience.service.UserService;
import com.example.retourexperience.ui.controller.RegistrationController;
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

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    //this allow to ignore the @Valid
/*        @MockBean
    private LocalValidatorFactoryBean validator;*/

    @Test
    public void testShowRegistrationForm() throws Exception {

        mockMvc.perform(get("/register"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("register"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("userDetails"));
    }

    @Test
    public void testSubmitRegistrationForm() throws Exception {
        UserDetailsRequestDtoModel userDetails = new UserDetailsRequestDtoModel("Jean", "Bidon", "jean.bidon@gmail.com", "Password1", "jeanb");
        mockMvc.perform(post("/register")
                .flashAttr("userDetails",userDetails))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
      // .andExpect(content().string(containsString("Hello, Mock")))
    }

    @Test
    public void testShowLoginForm() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"));
    }
}
