package com.example.task.service;

import com.example.task.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    boolean changeGuild(String guild, User user);

    List<User> findAllByLogin(String login);

    List<User> findAllByEmail(String email);
}
