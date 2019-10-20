package com.example.task.controller;

import com.example.task.model.Character;
import com.example.task.model.UserPrincipal;
import com.example.task.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CharacterController {
    @Autowired
    private CharacterServiceImpl characterService;

    public List<String> className = Arrays.asList(
            "Archer", "Berserker", "Dark Knight", "Kunoichi", "Lahn", "Maehwa", "Musa",
            "Musa", "Mystic", "Ninja", "Ranger", "Sorceress", "Striker", "Tamer", "Valkyrie",
            "Warrior", "Witch", "Wizard"
    );

    @GetMapping("/")
    public String getCharacters(Model model, Authentication authentication){
        List<Character> characters = characterService.findAll();
        if(characters.isEmpty()){
            model.addAttribute("message", "Out of data" );
        }
        if(authentication.isAuthenticated()){
            model.addAttribute("user", ((UserPrincipal)authentication.getPrincipal())
                    .getUser());
        }
        model.addAttribute("character", characters );
        model.addAttribute("characterClass", className);
        return "characters";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String Nickname, Model model){

        return "characters";
    }
    @PostMapping("delete")
    public String deleteCharacter(@RequestParam long id, Model model){
        characterService.deleteById(id);
        List<Character> character = characterService.findAll();
        if(character.isEmpty()){
            model.addAttribute("message", "No match found");
        }else {
            model.addAttribute("player", character);
        }
        return "redirect:/";
    }
}
