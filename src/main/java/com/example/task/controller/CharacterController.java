package com.example.task.controller;

import com.example.task.model.Character;
import com.example.task.model.CharacterDTO;
import com.example.task.model.UserDTO;
import com.example.task.model.UserPrincipal;
import com.example.task.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class CharacterController {
    @Autowired
    private CharacterServiceImpl characterService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String getCharacters(Model model, Authentication authentication) {
        List<Character> characters = characterService.findAll();
        if (characters.isEmpty()) {
            model.addAttribute("message", messageSource.getMessage("characters.outOfData", null, Locale.getDefault()));
        }
        if (authentication.isAuthenticated()) {
            model.addAttribute("user", new UserDTO(((UserPrincipal) authentication.getPrincipal())
                    .getUser()));
        }
        model.addAttribute("character", characters);
        return "characters";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String Nickname, Model model, Authentication authentication) {
        if(Nickname.isEmpty()) {
            return "redirect:/";
        }
        CharacterDTO characters = characterService.findDTOByName(Nickname);
        if (characters == null) {
            model.addAttribute("message", messageSource.getMessage("filter.noMatchFound", null,Locale.getDefault()));
        }
        if (authentication.isAuthenticated()) {
            model.addAttribute("user", new UserDTO(((UserPrincipal) authentication.getPrincipal())
                    .getUser()));
        }
        model.addAttribute("character", characters);
        return "characters";
    }
}
