package com.example.task.controller;

import com.example.task.model.Character;
import com.example.task.model.UserPrincipal;
import com.example.task.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getCharacter(Model model, Authentication authentication){
        List<Character> characters = characterService.findAll();
        if(characters.isEmpty()){
            model.addAttribute("message", "Out of data" );
        }
        model.addAttribute("character", characters );
        model.addAttribute("characterClass", className);
        return "characters";
    }

    @PostMapping("add")
    public String add(@RequestParam String Nickname, @RequestParam int Level, @RequestParam String ClassName, Authentication authentication, Model model) throws ClassNotFoundException {
        Character character = new Character(Nickname, Level, ((UserPrincipal)authentication.getPrincipal()).getUser(), ClassName);
        characterService.save(character);
        return "redirect:/";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String Nickname, Model model){

        return "characters";
    }
    @PostMapping("delete")
    public String delete(@RequestParam long id, Model model){
        characterService.deleteById(id);
        List<Character> character = characterService.findAll();
        if(character.isEmpty()){
            model.addAttribute("message", "No match found");
        }else {
            model.addAttribute("player", character);
        }
        return "redirect:/";
    }

    @PostMapping("change")
    public String change(@RequestParam long id, Model model){
        List<Character> character = characterService.findById(id);
        model.addAttribute("player", character);
        return "change";
    }

    @PostMapping("ok")
    public String applyChange(Model model, @ModelAttribute Character character){
        characterService.save(character);
        return "redirect:/";
    }
}
