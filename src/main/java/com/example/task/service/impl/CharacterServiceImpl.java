package com.example.task.service.impl;

import com.example.task.model.Character;
import com.example.task.model.CharacterDTO;
import com.example.task.model.User;
import com.example.task.repository.CharacterRepository;
import com.example.task.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

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
    public List<CharacterDTO> findAllDTO() {
        List<Character> characters = findAll();
        List<CharacterDTO> characterDTOS = new ArrayList<CharacterDTO>();
        for(Character character : characters){
            characterDTOS.add(new CharacterDTO(character));
        }
        return characterDTOS;
    }

    @Override
    public List<CharacterDTO> findAllDTOByOwner(User user) {
        List<Character> characters = findAllByOwner(user);
        List<CharacterDTO> characterDTOS = new ArrayList<CharacterDTO>();
        for(Character character : characters){
            characterDTOS.add(new CharacterDTO(character));
        }
        return characterDTOS;
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
    public CharacterDTO findDTOByName(String name) {
        return new CharacterDTO(findByName(name));
    }

    @Override
    public CharacterDTO findDTOById(Long id) {
        return new CharacterDTO(findById(id));
    }

    @Override
    public boolean characterValidSave(CharacterDTO characterDTO, User user){
        if(characterRepository.findByName(characterDTO.getName()) == null){
            Character character = characterDTO.getCharacter(user);
            save(character);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean changeCharacter(CharacterDTO NewCharacterData){
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
