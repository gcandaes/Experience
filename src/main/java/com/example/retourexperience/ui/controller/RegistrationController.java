package com.example.retourexperience.ui.controller;

import com.example.retourexperience.service.UserService;
import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public ModelAndView showRegistrationForm(Model model) {
        model.addAttribute("userDetails", new UserDetailsRequestDtoModel());
        return new ModelAndView("register", (Map<String, ?>) model);
    }

    @PostMapping(path = "/register")
    public ModelAndView createUser(@Valid @ModelAttribute("userDetails") UserDetailsRequestDtoModel userDetails, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return new ModelAndView("register");
        }
        userService.createUser(userDetails);

        return new ModelAndView(new RedirectView("login"));
        // return "redirect:/login"; // Exemple de redirection vers une page de connexion
    }


}
