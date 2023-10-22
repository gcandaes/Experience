package com.example.retourexperience.ui.controller;

import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import com.example.retourexperience.userservice.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDetails", new UserDetailsRequestDtoModel());
        return "register";
    }

    @PostMapping(path = "/register")
    public String createUser(@Valid @ModelAttribute("userDetails") UserDetailsRequestDtoModel userDetails) {

        userService.createUser(userDetails);

        return "redirect:/login"; // Exemple de redirection vers une page de connexion
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Ceci renverra la page de connexion (login.html) à l'utilisateur.
    }
}
