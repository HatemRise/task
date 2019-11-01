package com.example.task.controller;

import com.example.task.model.CharacterDTO;
import com.example.task.model.User;
import com.example.task.model.UserDTO;
import com.example.task.model.UserPrincipal;
import com.example.task.service.CharacterService;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
public class MyPageController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    public List<String> className = Arrays.asList(
            "Archer", "Berserker", "Dark Knight", "Kunoichi", "Lahn", "Maehwa", "Musa",
            "Musa", "Mystic", "Ninja", "Ranger", "Sorceress", "Striker", "Tamer", "Valkyrie",
            "Warrior", "Witch", "Wizard"
    );

    @GetMapping("MyPage")
    public ModelAndView getOwnCharacters(ModelMap model, Authentication authentication) {
        User user = ((UserPrincipal) authentication.getPrincipal())
                .getUser();
        List<CharacterDTO> characters = characterService.findAllDTOByOwner(user);
        if (characters.isEmpty()) {
            model.addAttribute("errorMessage", messageSource.getMessage("characters.noCharacters", null, Locale.getDefault()));
        }
        model.addAttribute("user", new UserDTO(user));
        model.addAttribute("characters", characters);
        model.addAttribute("characterClass", className);
        return new ModelAndView("MyPage");
    }

    @PostMapping("/MyPage/add")
    public RedirectView addCharacter(@Validated(CharacterDTO.New.class) CharacterDTO character,
                                     BindingResult bindingResult,
                                     Authentication authentication,
                                     RedirectAttributes model) throws ClassNotFoundException {
        User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        Map<String, String> errorMap = new HashMap<String, String>();
        if (bindingResult.hasErrors()) {
            errorMap = ValidateErrors.getErrors(bindingResult);
        }
        if (characterService.findByName(character.getName()) != null) {
            errorMap.put("nameError", messageSource.getMessage("name.used", null, Locale.getDefault()));
        }
        if (errorMap.isEmpty()) {
            characterService.characterValidSave(character, user);
            return new RedirectView("/MyPage?add");
        } else {
            model.addAttribute("user", new UserDTO(user));
            model.addFlashAttribute("errorMap", errorMap);
            return new RedirectView("/MyPage");
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "change")
    public String changeCharacter(@RequestParam long change, Model model) {
        CharacterDTO character = characterService.findDTOById(change);
        model.addAttribute("character", character);
        return "change";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, params = "delete")
    public String deleteCharacter(@RequestParam long delete, Map<String, Object> model) {
        characterService.deleteById(delete);
        return "redirect:/MyPage?deleted";
    }

    @PostMapping("ok")
    public String applyChange(@Validated(CharacterDTO.UpdateNameAndLvl.class) CharacterDTO character, BindingResult bindingResult, Model model) {
        Map<String, String> errorMap = Collections.<String, String>emptyMap();
        if (bindingResult.hasErrors()) {
            errorMap = ValidateErrors.getErrors(bindingResult);
        }
        if (characterService.findByName(character.getName()) != null &&
                characterService.findByName(character.getName()).getId() != character.getId()) {
            errorMap.put("nameError", messageSource.getMessage("name.used", null, Locale.getDefault()));
        }
        if (errorMap.isEmpty()) {
            characterService.changeCharacter(character);
            return "redirect:/MyPage?change";
        } else {
            model.mergeAttributes(errorMap);
            return "change";
        }

    }

    @PostMapping("/MyPage/guild")
    public String changeGuild(@RequestParam String guild, Authentication authentication, Model model) {
        if (userService.changeGuild(guild, ((UserPrincipal) authentication.getPrincipal()).getUser())) {
            return "redirect:/MyPage?guild";
        }
        return "redirect:/MyPage?guildError";
    }


}
