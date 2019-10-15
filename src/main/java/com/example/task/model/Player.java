package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column
    private String nickname;
    @NotNull
    @Column
    private String character;
    @NotNull
    @Column
    private int lvl;
    @Column
    private String guild;

    public Player() {
    }

    public Player(String nickname, String character, int lvl, String guild) {
        this.id = id;
        this.nickname = nickname;
        this.character = character;
        this.lvl = lvl;
        this.guild = guild;
    }

    public Player(String nickname, String character, int lvl) {
        this.id = id;
        this.nickname = nickname;
        this.character = character;
        this.lvl = lvl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    @Override
    public String toString(){
        return "id: " + id + " nickname: ";
    }
}
