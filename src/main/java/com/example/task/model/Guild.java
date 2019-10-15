package com.example.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Guild")
public class Guild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private int skillPoints;


    @Column
    @NotNull
    private int members;

    public Guild() {
    }

    public Guild(String name, int skillPoints, int members) {
        this.name = name;
        this.skillPoints = skillPoints;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public void plusMember(){
        this.members++;
    }

    public void minusMember(){
        if(members > 0)this.members--;
    }
}
