package com.example.task.service;

import com.example.task.model.Character;
import com.example.task.model.User;

import java.util.List;

public interface CharacterService {
    Character findById(long id);
    List<Character> findAllByOwner(User user);
}
