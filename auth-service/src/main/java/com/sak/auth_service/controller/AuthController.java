package com.sak.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sak.auth_service.entity.User;
import com.sak.auth_service.repository.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Login Page - Show the login form
    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("Login Page from auth-service");
        model.addAttribute("user", new User());
        return "login.html"; 
    }

    // Login POST - Check if the user exists in the UserRepository
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        // Fetch user from the UserRepository
        User user = userRepository.findByUsername(username);

        // Validate user
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user); 
            return "redirect:http://52.66.200.111:8081";  
        } else {
            model.addAttribute("error", "Invalid credentials!");  
            return "login";  
        }
    }


    @PostMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("user", null); 
        return "redirect:/auth/login";  
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        System.out.println("Signup Page from auth-service");
        return "redirect:http://52.66.200.111:8082/users/signup";  
    }

    @GetMapping("/")
    public String home() {
        return "redirect:http://52.66.200.111:8081/";
    }
}
