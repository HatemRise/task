package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "persons")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column
    private String role;

    public User() {
    }

    public User(@NotNull String login, @NotNull String password, @NotNull String email, String userRole) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = userRole;
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
}
