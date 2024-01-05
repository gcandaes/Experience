package com.example.retourexperience.ui.controller;

import com.example.retourexperience.ui.model.requestDto.UserDetailsRequestDtoModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/user")
    public String getUser() {
        return "Welcome, User";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome, Admin";
    }

    @GetMapping("/customLogin")
    public ModelAndView showLoginForm() {
        return new ModelAndView("customLogin"); // Ceci renverra la page de connexion (login.html) à l'utilisateur.
    }

    @PostMapping("/customLogin")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index"); // Ceci renverra la page de connexion (login.html) à l'utilisateur.
    }

    @GetMapping("/index")
    public ModelAndView showHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());

        return new ModelAndView("/index");
    }

    @GetMapping("/")
    public ModelAndView showHomePageBis(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());

        return new ModelAndView("/index");
    }
}
