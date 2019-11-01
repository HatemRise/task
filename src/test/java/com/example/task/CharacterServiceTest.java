package com.example.task;

import com.example.task.model.Character;
import com.example.task.model.CharacterDTO;
import com.example.task.model.User;
import com.example.task.repository.CharacterRepository;
import com.example.task.service.CharacterService;
import com.example.task.service.impl.CharacterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService = new CharacterServiceImpl();

    @Before
    public void init() {
        Mockito.when(characterRepository.findByName("exist")).thenReturn(new Character());
        Mockito.when(characterRepository.findByName("notExist")).thenReturn(null);
        Mockito.when(characterRepository.findById(Mockito.anyLong())).thenReturn(new Character());
    }

    @Test
    public void validSaveExistNameTest() {
        Assert.assertFalse(characterService.characterValidSave(new CharacterDTO(0, "exist", null, null, null), null));
    }

    @Test
    public void validSaveNotExistNameTest() {
        Assert.assertTrue(characterService.characterValidSave(new CharacterDTO(0, "notExist", "test", 0, null), new User("test", "test", "test", "test")));
    }

    @Test
    public void changeCharacterExistNameTest() {
        Assert.assertFalse(characterService.changeCharacter(new CharacterDTO(0, "exist", null, null, null)));
    }

    @Test
    public void changeCharacterNorExistNameTest() {
        Assert.assertTrue(characterService.changeCharacter(new CharacterDTO(0, "NotExist", null, null, null)));
    }
}
