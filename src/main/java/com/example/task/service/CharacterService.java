package com.example.task.service;

import com.example.task.model.Character;
import com.example.task.model.CharacterDTO;
import com.example.task.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacterService {
    Character findById(long id);

    List<Character> findAllByOwner(User user);

    List<Character> findAll();

    Character findByName(String name);

    boolean characterValidSave(CharacterDTO characterDTO, User user);

    void save(Character character);

    boolean changeCharacter(CharacterDTO NewCharacterData);

    void deleteById(long id);

    List<CharacterDTO> findAllDTO();

    CharacterDTO findDTOByName(String name);

    CharacterDTO findDTOById(Long id);

    List<CharacterDTO> findAllDTOByOwner(User user);
}
