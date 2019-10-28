package com.example.task.service;

import com.example.task.model.Character;
import com.example.task.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterService {
    Character findById(long id);

    List<Character> findAllByOwner(User user);

    List<Character> findAll();

    Character findByName(String name);

    boolean characterValidSave(Character character, User user);

    void save(Character character);

    boolean changeCharacter(Character NewCharacterData);

    void deleteById(long id);
}
