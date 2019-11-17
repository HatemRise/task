package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "name", unique = true)
    @NotBlank(message = "Name can't be blank")
    @Size(min = 3, max = 24, message = "Name size should be between 3 and 20")
    private String name;

    @Column(name = "lvl")
    private Integer lvl;

    @Column(name = "class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;


    public Character() {
    }

    public Character(Long id, String name, Integer lvl, User owner, String className) {
        this.id = id;
        this.className = className;
        this.owner = owner;
        this.name = name;
        this.lvl = lvl;
    }

    public Character(String name, String className, int lvl, User owner) {
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

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
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
