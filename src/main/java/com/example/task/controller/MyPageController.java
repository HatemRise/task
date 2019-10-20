package com.example.task.controller;

import com.example.task.model.Character;
import com.example.task.model.UserPrincipal;
import com.example.task.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    private CharacterServiceImpl characterService;

    public List<String> className = Arrays.asList(
            "Archer", "Berserker", "Dark Knight", "Kunoichi", "Lahn", "Maehwa", "Musa",
            "Musa", "Mystic", "Ninja", "Ranger", "Sorceress", "Striker", "Tamer", "Valkyrie",
            "Warrior", "Witch", "Wizard"
    );
    @GetMapping("/MyPage")
    public String getOwnCharacters(Model model, Authentication authentication){
        List<Character> characters = characterService.findAllByOwner(((UserPrincipal)authentication
                .getPrincipal()).getUser());
        if(characters.isEmpty()){
            model.addAttribute("message", "You have no characters" );
        }
        if(authentication.isAuthenticated()){
            model.addAttribute("user", ((UserPrincipal)authentication.getPrincipal())
                    .getUser().getLogin());
        }
        model.addAttribute("character", characters );
        model.addAttribute("characterClass", className);
        return "MyPage";
    }

    @PostMapping("/MyPage/add")
    public String addCharacter(@RequestParam String Nickname, @RequestParam int Level, @RequestParam String ClassName, Authentication authentication, Model model) throws ClassNotFoundException {
        Character character = new Character(Nickname, Level, ((UserPrincipal)authentication.getPrincipal()).getUser(), ClassName);
        characterService.save(character);
        return "redirect:/MyPage";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "change")
    public String changeCharacter(@RequestParam long change, Model model){
        Character character = characterService.findById(change);
        model.addAttribute("character", character);
        return "change";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
    public String deleteCharacter(@RequestParam long delete, Model model){
        characterService.deleteById(delete);
        return "redirect:/MyPage";
    }

    @PostMapping("ok")
    public String applyChange(Model model, @RequestParam long Id, @RequestParam String Nickname, @RequestParam int Lvl){
        Character character = characterService.findById(Id);
        character.setName(Nickname);
        character.setLvl(Lvl);
        characterService.save(character);
        return "redirect:/MyPage";
    }
}
