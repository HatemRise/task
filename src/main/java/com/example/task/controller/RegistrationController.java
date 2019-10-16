package com.example.task.controller;

import com.example.task.model.Person;
import com.example.task.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping
    public String regForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regForm(@RequestParam String login, @RequestParam String password, @RequestParam String email, Map<String, Object> model){
        if(!userDetailsService.findAllByLogin(login).isEmpty() && login == null){
            return "redirect:/registration";
        }else if(!userDetailsService.findAllByEmail(email).isEmpty() && email == null){
            return "redirect:/registration";
        }else if(password == null){
            return "redirect:/registration";
        }else{
            userDetailsService.save(new Person(login, new BCryptPasswordEncoder().encode(password), email, "USER"));
            return "redirect:/registration";
        }
    }
}
