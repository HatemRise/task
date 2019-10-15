package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String email;

    public Person() {
    }

    public Person(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
