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

    @Override
    public List<Character> findAll(){
        return characterRepository.findAll();
    }

    @Override
    public List<Character> findAllByOwner(User user){
        return characterRepository.findAllByOwner(user);
    }

    @Override
    public void save(Character character){
        characterRepository.save(character);
    }

    @Override
    public void deleteById(long id){
        characterRepository.deleteById(id);
    }

    @Override
    public Character findByName(String name){
        return characterRepository.findByName(name);
    }

    @Override
    public boolean characterValidSave(Character character, User user){
        if(characterRepository.findByName(character.getName()) == null){
            character.setOwner(user);
            save(character);
            return true;
        }else{
            return false;
        }
    }

    public boolean changeCharacter(Character NewCharacterData){
        if(characterRepository.findByName(NewCharacterData.getName()) == null) {
            Character character = characterRepository.findById(NewCharacterData.getId());
            character.setName(NewCharacterData.getName());
            character.setLvl(NewCharacterData.getLvl());
            characterRepository.save(character);
            return true;
        }
        return false;
    }
}
