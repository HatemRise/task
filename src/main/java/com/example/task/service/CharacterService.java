package com.example.task.service;

import com.example.task.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findById(long id);
}
