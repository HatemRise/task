package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persons")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String role;

    @Column
    private String guild;

    public User() {
    }

    public User(@NotNull String login, @NotNull String password, @NotNull String email, String userRole) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserRole() {
        return role;
    }

    public void setUserRole(String userRole) {
        this.role = userRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    @Override
    public String toString() {
        return login;
    }
}
