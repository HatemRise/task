package com.example.task.controller;

import com.example.task.model.Player;
import com.example.task.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PlayerController {
    @Autowired
    private PlayerServiceImpl playerService;

    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String getPlayers(Map<String, Object> model, Authentication authentication){
        List<Player> players = playerService.findAll();
        model.put("player", players);
        return "players";
    }

    @PostMapping("add")
    public String add(@RequestParam String Nickname, @RequestParam String Character, @RequestParam int Level, @RequestParam String Guild, Map<String, Object> model){
        playerService.save(new Player(Nickname, Character, Level, Guild));
        Iterable<Player> players = playerService.findAll();
        model.put("player", players);
        return "redirect:/players";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String Nickname, Map<String, Object> model){
        List<Player> players = playerService.findByNickname(Nickname);
        if(players.isEmpty()){
            model.put("player", new Player(null, "Out of data", 0));
        }else {
            model.put("player", players);
        }
        return "players";
    }
    @PostMapping("delete")
    public String delete(@RequestParam long id, Map<String, Object> model){
        playerService.deleteById(id);
        List<Player> players = playerService.findAll();
        if(players.isEmpty()){
            model.put("player", new Player(null, "Out of data", 0));
        }else {
            model.put("player", players);
        }
        return "redirect:/players";
    }

    @PostMapping("change")
    public String change(@RequestParam long id,Map<String, Object> model){
        List<Player> players = playerService.findById(id);
        model.put("player", players);
        return "change";
    }

    @PostMapping("ok")
    public String applyChange(Map<String, Object> model, @ModelAttribute Player player){
        playerService.save(player);
        return "redirect:/players";
    }

    @GetMapping("cancel")
    public String cancelChange(Map<String, Object> model){
        return "redirect:/players";
    }
}
