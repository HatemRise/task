package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Login can't be empty")
    @Size(min = 3, max = 20, message = "Login size should be between 3 and 20")
    private String login;

    @Column
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 60, message = "Password size should be between 6 and 60")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(unique = true)
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return login;
    }
}
