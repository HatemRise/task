package com.example.task.service.impl;

import com.example.task.model.Player;
import com.example.task.repository.PlayerRepository;
import com.example.task.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> findByNickname(String nickname) {
        return playerRepository.findByNickname(nickname);
    }

    @Override
    public List<Player> findByLvl(int lvl) {
        return playerRepository.findByLvl(lvl);
    }

    @Override
    public List<Player> findByCharacter(String character) {
        return playerRepository.findByCharacter(character);
    }

    @Override
    public List<Player> findByGuild(String guild) {
        return playerRepository.findByGuild(guild);
    }

    @Override
    public List<Player> findById(long id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public void save(Player player){
        playerRepository.save(player);
    }

    public void deleteById(long id){
        playerRepository.deleteById(id);
    }
}
