package com.example.task.service.impl;

import com.example.task.model.User;
import com.example.task.model.UserDTO;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        User user = userDTO.getUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAllByLogin(String login) {
        return userRepository.findAllByLogin(login);
    }

    public List<User> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public boolean changeGuild(String guild, User user) {
        user.setGuild(guild);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean changeGuild(UserDTO userDTO) {
        return false;
    }
}
