package com.example.task.repository;

import com.example.task.model.Character;
import com.example.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Character findById(long id);
    List<Character> findAllByOwner(User user);
    List<Character> findAll();
    List<Character> findByName(String name);
}
