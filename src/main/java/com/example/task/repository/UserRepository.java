package com.example.task.repository;

import com.example.task.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByLogin(String login);
    List<Person> findAllByLogin(String login);
    List<Person> findAllByEmail(String email);

}
