package com.example.task.service.impl;

import com.example.task.model.Player;
import com.example.task.repository.PlayerRepository;
import com.example.task.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    private List<Player> players;


    @Override
    public List<Player> findPlayerByNickname(String nickname) {
        List<Player> result = players.stream()
                .filter(player -> player.getNickname().equals(nickname))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Player> findPlayerByLvl(int lvl) {
        List<Player> result = players.stream()
                .filter(player -> player.getLvl() == lvl)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Player> findPlayerByCharacter(String charecter) {
        List<Player> result = players.stream()
                .filter(player -> player.getCharacter().equals(charecter))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Player> findPlayerByGuild(String guild) {
        List<Player> result = players.stream()
                .filter(player -> player.getGuild().equals(guild))
                .collect(Collectors.toList());
        return result;
    }
}
