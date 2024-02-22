package com.roshan.thymeleafpractice.controller;

import com.roshan.thymeleafpractice.dto.login.UserLoginRequestDto;
import com.roshan.thymeleafpractice.dto.login.UserLoginResponseDto;
import com.roshan.thymeleafpractice.dto.register.UserRegistrationRequestDto;
import com.roshan.thymeleafpractice.entity.User;
import com.roshan.thymeleafpractice.service.UserServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("login", new UserLoginRequestDto());
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("login") UserLoginRequestDto userLoginRequestDto, Model model) {
        UserLoginResponseDto userLoginResponseDto = userServiceImplementation.userLogin(userLoginRequestDto.getIdentifier(), userLoginRequestDto.getPassword(), userLoginRequestDto);
        if (userLoginResponseDto.getUsername().isBlank()) {
            model.addAttribute("message", "Invalid Credentials");
            return "login";
        }
        try {
            model.addAttribute("welcome", "Welcome " + userLoginResponseDto.getUsername());
            return "home";
        } catch (Exception e) {
            model.addAttribute("message", "Invalid Credentials");
            return "login";
        }
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("register") UserRegistrationRequestDto userRegistrationRequestDto, Model model) {
        User userCreated = userServiceImplementation.createUser(userRegistrationRequestDto);
        if (userCreated == null) {
            model.addAttribute("message", "Registration failed!");
            return "createuser";
        }
        model.addAttribute("message", "Registration Success Login to continue.");
        return "createuser";
    }

    @GetMapping("/createusers")
    public String createUser(Model model) {
        model.addAttribute("register", new UserRegistrationRequestDto());
        return "createuser";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("welcome", "Welcome to home page");
        return "home";
    }
}
