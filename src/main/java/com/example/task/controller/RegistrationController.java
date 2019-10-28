package com.example.task.controller;

import com.example.task.model.User;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String regForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regFormSubmit(@Valid User user, BindingResult bindingResult, Model model){
        Map<String, String> errorMap = new HashMap<String, String>();
        if (bindingResult.hasErrors()) {
            errorMap = ValidateErrors.getErrors(bindingResult);
        }
        if (!userService.findAllByLogin(user.getLogin()).isEmpty()) {
            errorMap.put("loginError", "Name already used");
        }
        if (!userService.findAllByEmail(user.getEmail()).isEmpty()) {
            errorMap.put("emailError", "Email already used");
        }
        if(!user.getPasswordConfirm().equals(user.getPassword())){
            errorMap.put("passwordConfirmError", "Password and confirm password does not match");
        }
        if(errorMap.isEmpty()) {
            user.setRole("ROLE_USER");
            userService.save(user);
            return "login";
        }else{
            model.mergeAttributes(errorMap);
            return "registration";
        }
    }
}
