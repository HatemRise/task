package com.example.task.service;

import com.example.task.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> findPlayerByNickname(String nickname);
    List<Player> findPlayerByLvl(int lvl);
    List<Player> findPlayerByCharacter(String charecter);
    List<Player> findPlayerByGuild(String guild);
}
