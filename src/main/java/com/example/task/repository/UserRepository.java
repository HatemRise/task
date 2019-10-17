package com.example.task.repository;

import com.example.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    List<User> findAllByLogin(String login);
    List<User> findAllByEmail(String email);

}
