package com.example.task.service;

import com.example.task.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> findByNickname(String nickname);
    List<Player> findByLvl(int lvl);
    List<Player> findByCharacter(String charecter);
    List<Player> findByGuild(String guild);
    List<Player> findById(long id);
}
