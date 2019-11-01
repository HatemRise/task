package com.example.task.model;

import com.example.task.annotation.UniqueEmail;
import com.example.task.annotation.UniqueLogin;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.*;

public class UserDTO {
    public interface New {
    }

    interface Exist {
    }

    interface UpdateName extends Exist {
    }

    interface Details {
    }

    @Null(groups = {New.class})
    @NotBlank
    private Long id;

    @NotBlank(groups = {New.class, UpdateName.class}, message = "Login can't be empty")
    @Size(groups = {New.class,  UpdateName.class}, min = 3, max = 20, message = "Login size should be between 3 and 20")
    @UniqueLogin(message = "Login already Used", groups = {New.class, UpdateName.class})
    @JsonView({Details.class})
    private String login;

    @NotBlank(groups = {New.class}, message = "Password can't be empty")
    @Size(groups = {New.class}, min = 6, max = 60, message = "Password size should be between 6 and 60")
    @Null(groups = {UpdateName.class})
    private String password;

    @NotBlank(groups = {New.class})
    @Null(groups = {UpdateName.class})
    private String confirmPassword;

    @NotBlank(groups = {New.class}, message = "Email can't be empty")
    @Email(groups = {New.class}, message = "Invalid email")
    @Null(groups = {UpdateName.class})
    @UniqueEmail(message = "Email already Used", groups = {New.class})
    private String email;

    @Null(groups = {New.class, UpdateName.class})
    private String guild;

    public UserDTO() {
    }

    public UserDTO(@Null(groups = {New.class}) @NotNull(groups = {UpdateName.class}) Long id, @NotNull(groups = {New.class}) String login, @NotNull(groups = {New.class}) @Null(groups = {UpdateName.class}) String password, @NotNull(groups = {New.class}) @Null(groups = {UpdateName.class}) String confirmPassword, @NotNull(groups = {New.class}) @Email(groups = {New.class}) @Null(groups = {UpdateName.class}) String email, @Null(groups = {New.class, UpdateName.class}) String guild) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.guild = guild;
    }

    public UserDTO(User user){
        login = user.getLogin();
        guild = user.getGuild();
    }

    public UserDTO(long id, String guild){
        this.id = id;
        this.guild = guild;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public boolean confirmPassword(){
        if(confirmPassword.equals(password)) return true;
        return false;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getUser(){
        return new User(login, password, email, "ROLE_USER");
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return login;
    }
}
