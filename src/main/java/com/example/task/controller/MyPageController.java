package com.example.task.controller;

import com.example.task.model.Character;
import com.example.task.model.User;
import com.example.task.model.UserPrincipal;
import com.example.task.repository.UserRepository;
import com.example.task.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class MyPageController {

    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private UserRepository userRepository;

    public List<String> className = Arrays.asList(
            "Archer", "Berserker", "Dark Knight", "Kunoichi", "Lahn", "Maehwa", "Musa",
            "Musa", "Mystic", "Ninja", "Ranger", "Sorceress", "Striker", "Tamer", "Valkyrie",
            "Warrior", "Witch", "Wizard"
    );
    @GetMapping("MyPage")
    public String getOwnCharacters(Model model, Authentication authentication){
        List<Character> characters = characterService.findAllByOwner(((UserPrincipal)authentication
                .getPrincipal()).getUser());
        if(characters.isEmpty()){
            model.addAttribute("errorMessage", "You have no characters" );
        }
        if(authentication.isAuthenticated()){
            model.addAttribute("user", ((UserPrincipal)authentication.getPrincipal())
                    .getUser());
        }
        model.addAttribute("character", characters );
        model.addAttribute("characterClass", className);
        return "MyPage";
    }

    @PostMapping("/MyPage/add")
    public String addCharacter(@RequestParam String Nickname, @RequestParam int Level, @RequestParam String ClassName, Authentication authentication, Model model) throws ClassNotFoundException {
        if(characterService.findByName(Nickname).isEmpty()) {
            Character character = new Character(Nickname, Level, ((UserPrincipal)authentication.getPrincipal()).getUser(), ClassName);
            characterService.save(character);
        }else{
            model.addAttribute("errorMessage", "This name already used");
            return "redirect:/MyPage?addError";
        }
        return "redirect:/MyPage?add";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "change")
    public String changeCharacter(@RequestParam long change, Model model){
        Character character = characterService.findById(change);
        model.addAttribute("character", character);
        return "change";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
    public String deleteCharacter(@RequestParam long delete, Map<String, Object> model){
        characterService.deleteById(delete);
        return "redirect:/MyPage?deleted";
    }

    @PostMapping("ok")
    public String applyChange(Model model, @RequestParam long Id, @RequestParam String Nickname, @RequestParam int Lvl){
        if(characterService.findByName(Nickname).isEmpty()) {
            Character character = characterService.findById(Id);
            character.setName(Nickname);
            character.setLvl(Lvl);
            characterService.save(character);
        }else{
            model.addAttribute("errorMessage", "This name already used");
            return "/change?error";
        }
        return "redirect:/MyPage?change";
    }

    @PostMapping("/MyPage/guild")
    public String changeGuild(@RequestParam String guild, Authentication authentication, Model model){
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        user.setGuild(guild);
        userRepository.save(user);
        return "redirect:/MyPage?guild";
    }
}
