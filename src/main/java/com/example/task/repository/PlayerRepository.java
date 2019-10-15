package com.example.task.repository;

import com.example.task.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByNickname(String nickname);
    List<Player> findById(long id);
}
