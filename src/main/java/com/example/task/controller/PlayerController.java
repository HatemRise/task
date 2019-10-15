package com.example.task.controller;

import com.example.task.model.Player;
import com.example.task.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players/{nickname}")
    public String getPlayerByNickname(@PathVariable String nickname, Map<String, Object> model){
        Iterable<Player> players = playerRepository.findByNickname(nickname);
        model.put("player", players);
        return "Players";
    }

    @GetMapping("/players")
    public String getPlayers(Map<String, Object> model){
        Iterable<Player> players = playerRepository.findAll();
        model.put("player", players);
        return "players";
    }

    @PostMapping("add")
    public String add(@RequestParam String Nickname, @RequestParam String Character, @RequestParam int Level, @RequestParam String Guild, Map<String, Object> model){
        playerRepository.save(new Player(Nickname, Character, Level, Guild));
        Iterable<Player> players = playerRepository.findAll();
        model.put("player", players);
        return "redirect:/players";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String Nickname, Map<String, Object> model){
        List<Player> players = playerRepository.findByNickname(Nickname);
        if(players.isEmpty()){
            model.put("player", new Player(null, "Out of data", 0));
        }else {
            model.put("player", players);
        }
        return "players";
    }
    @PostMapping("delete")
    public String delete(@RequestParam long id, Map<String, Object> model){
        playerRepository.deleteById(id);
        List<Player> players = playerRepository.findAll();
        if(players.isEmpty()){
            model.put("player", new Player(null, "Out of data", 0));
        }else {
            model.put("player", players);
        }
        return "redirect:/players";
    }

    @PostMapping("change")
    public String change(@RequestParam long id,Map<String, Object> model){
        List<Player> players = playerRepository.findById(id);
        model.put("player", players);
        return "change";
    }

    @PostMapping("ok")
    public String applyChange(Map<String, Object> model, @ModelAttribute Player player){
        playerRepository.save(player);
        return "redirect:/players";
    }

    @GetMapping("cancel")
    public String cancelChange(Map<String, Object> model){
        return "redirect:/players";
    }
}
