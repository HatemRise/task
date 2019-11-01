package com.example.task.annotation;

import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueLoginConstraintValidator implements ConstraintValidator<UniqueLogin, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueLogin uniqueLogin) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext cxt) {
        return userService.findAllByLogin(login).isEmpty();
    }
}
