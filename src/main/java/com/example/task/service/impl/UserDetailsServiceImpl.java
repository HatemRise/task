package com.example.task.service.impl;

import com.example.task.model.User;
import com.example.task.model.UserPrincipal;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserPrincipal(userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login)));
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
}
