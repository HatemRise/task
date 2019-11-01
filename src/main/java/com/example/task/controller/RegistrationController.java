package com.example.task.controller;

import com.example.task.model.UserDTO;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registration")
    public String regForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regFormSubmit(@Validated(UserDTO.New.class) UserDTO userDTO, BindingResult bindingResult, Model model){
        Map<String, String> errorMap = new HashMap<String, String>();
        if (bindingResult.hasErrors()) {
            errorMap = ValidateErrors.getErrors(bindingResult);
        }
        if(!userDTO.confirmPassword()){
            errorMap.put("passwordConfirmError", messageSource.getMessage("password.confirmPasswordMatchError", null, Locale.getDefault()));
        }
        if(errorMap.isEmpty()) {
            userService.save(userDTO);
            return "login";
        }else{
            model.mergeAttributes(errorMap);
            return "registration";
        }
    }
}
