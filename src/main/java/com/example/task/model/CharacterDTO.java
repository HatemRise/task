package com.example.task.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class CharacterDTO {
    public interface New {
    }

    public interface Exist {
    }

    public interface UpdateNameAndLvl extends Exist {
    }

    public interface Details{
    }

    @Autowired
    private MessageSource messageSource;

    @JsonView({Details.class})
    private long id;

    @NotBlank(groups = {UpdateNameAndLvl.class, New.class}, message = "Name can't be blank")
    @JsonView({Details.class})
    @Size(min = 3, max = 24, message = "Name size should be between 3 and 20", groups = {UpdateNameAndLvl.class, New.class})
    private String name;

    @NotBlank(groups = {New.class})
    @JsonView({Details.class})
    private String className;

    @NotNull(message = "Level can't be empty", groups = {UpdateNameAndLvl.class, New.class})
    @JsonView({Details.class})
    private Integer lvl;

    @Null
    @JsonView({Details.class})
    private UserDTO owner;

    public CharacterDTO() {
    }

    public CharacterDTO(Character character) {
        id = character.getId();
        name = character.getName();
        className = character.getClassName();
        lvl = character.getLvl();
        owner = new UserDTO(character.getOwner());
    }

    public CharacterDTO(@Null(groups = {New.class}) @NotNull(groups = {UpdateNameAndLvl.class}) long id, @NotNull(groups = {UpdateNameAndLvl.class, New.class}) String name, @NotNull(groups = {New.class}) String className, @NotNull Integer lvl, @Null UserDTO owner) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.lvl = lvl;
        this.owner = owner;
    }

    public CharacterDTO(@NotNull(groups = {UpdateNameAndLvl.class, New.class}) String name, @NotNull(groups = {New.class}) String className, @NotNull Integer lvl, @Null UserDTO owner) {
        this.name = name;
        this.className = className;
        this.lvl = lvl;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public Character getCharacter(User user){
        return new Character(name, className, lvl, user);
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
