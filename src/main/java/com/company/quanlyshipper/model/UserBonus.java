/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Bach
 */
@Data
@Entity(name = "UserBonus")
public class UserBonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
     @Column(name="bonus_time")
    
    private LocalDate BonusTime; 
     
    @ManyToOne
    private Users User;
    
    @ManyToOne
    private Bonus Bonus;

    public int getId() {
        return Id;
    }

    public LocalDate getBonusTime() {
        return BonusTime;
    }

    public Users getUser() {
        return User;
    }

    public Bonus getBonus() {
        return Bonus;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setBonusTime(LocalDate BonusTime) {
        this.BonusTime = BonusTime;
    }

    public void setUser(Users User) {
        this.User = User;
    }

    public void setBonus(Bonus Bonus) {
        this.Bonus = Bonus;
    }

    public UserBonus(int Id, LocalDate BonusTime, Users User, Bonus Bonus) {
        this.Id = Id;
        this.BonusTime = BonusTime;
        this.User = User;
        this.Bonus = Bonus;
    }


    public UserBonus(){}
}
