package com.example.task.service;

import com.example.task.model.User;
import com.example.task.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    void save(User user);

    void save(UserDTO userDTO);

    boolean changeGuild(String guild, User user);

    boolean changeGuild(UserDTO userDTO);

    List<User> findAllByLogin(String login);

    List<User> findAllByEmail(String email);
}
