package com.example.task.service.impl;

import com.example.task.model.Character;
import com.example.task.model.User;
import com.example.task.repository.CharacterRepository;
import com.example.task.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public Character findById(long id) {
        return characterRepository.findById(id);
    }

    public List<Character> findAll(){
        return characterRepository.findAll();
    }

    public List<Character> findAllByOwner(User user){
        return characterRepository.findAllByOwner(user);
    }

    public void save(Character character){
        characterRepository.save(character);
    }

    public void deleteById(long id){
        characterRepository.deleteById(id);
    }
}
