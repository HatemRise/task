package com.example.task.controller;

import com.example.task.model.User;
import com.example.task.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/registration")
    public String regForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regFormSubmit(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model){
        if(!userDetailsService.findAllByLogin(username).isEmpty()){
            model.addAttribute("message", "The name is already exist");
            return "registration";
        }else if(!userDetailsService.findAllByEmail(email).isEmpty()){
            model.addAttribute("message", "The email is already exist");
            return "registration";
        }else if(password == null){
            model.addAttribute("message", "Password can't bee empty");
            return "registration";
        }else{
            userDetailsService.save(new User(username, new BCryptPasswordEncoder().encode(password), email, "ROLE_USER"));
            return "login";
        }
    }
}
