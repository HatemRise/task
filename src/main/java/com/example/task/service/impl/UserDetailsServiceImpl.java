package com.example.task.service.impl;

import com.example.task.model.User;
import com.example.task.model.UserPrincipal;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserPrincipal(userRepository.findByLogin(login).orElseThrow(()->new UsernameNotFoundException(login)));
    }

    public void save(User person){
        userRepository.save(person);
    }

    public List<User> findAllByLogin(String login){
        return userRepository.findAllByLogin(login);
    }

    public List<User> findAllByEmail(String email){
        return userRepository.findAllByEmail(email);
    }
}
