package com.example.task.model;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lvl")
    private int lvl;

    @Column(name = "class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;


    public Character() {
    }

    public Character(Long id, String name, int lvl, User owner, String className) {
        this.id = id;
        this.className = className;
        this.owner = owner;
        this.name = name;
        this.lvl = lvl;
    }

    public Character(String name, int lvl, User owner, String className) {
        this.className = className;
        this.owner = owner;
        this.name = name;
        this.lvl = lvl;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getLvl(){
        return lvl;
    }

    public void setLvl(int lvl){
        this.lvl = lvl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
