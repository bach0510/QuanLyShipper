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
@Entity(name = "UserPunish")
public class UserPunish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
     @Column(name="punish_time")
    
    private LocalDate PunishTime; 
     
    @ManyToOne
    private Users User;
    
    @ManyToOne
    private Punish Punish;

    public int getId() {
        return Id;
    }

    public LocalDate getPunishTime() {
        return PunishTime;
    }

    public Users getUser() {
        return User;
    }

    public Punish getPunish() {
        return Punish;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setPunishTime(LocalDate PunishTime) {
        this.PunishTime = PunishTime;
    }

    public void setUser(Users User) {
        this.User = User;
    }

    public void setPunish(Punish Punish) {
        this.Punish = Punish;
    }

    public UserPunish(int Id, LocalDate BonusTime, Users User, Punish Punish) {
        this.Id = Id;
        this.PunishTime = PunishTime;
        this.User = User;
        this.Punish = Punish;
    }


    public UserPunish(){}
}
