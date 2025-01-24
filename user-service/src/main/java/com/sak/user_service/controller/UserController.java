package com.sak.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sak.user_service.repository.UserRepository;
import com.sak.user_service.entity.User;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/signup") 
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user) {
        UserRepository.save(user);
        return "redirect:/users/signup";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "redirect:http://52.66.200.111:8083/auth/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:http://52.66.200.111:8081";
    }
}
