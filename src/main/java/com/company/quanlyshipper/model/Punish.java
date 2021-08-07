/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Bach
 */
@Data
@Entity(name = "Punish")
public class Punish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
    
    @Basic
    @Column(name="punish_name",columnDefinition = "nvarchar(50)")
    private String PunishName;
    
    @Column(name="price")
    private double Price;
    
    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getPunishName() {
        return PunishName;
    }

    public void setPunishName(String PunishName) {
        this.PunishName = PunishName;
    }

    public Punish(int Id, String PunishName, double Price) {
        this.Id = Id;
        this.PunishName = PunishName;
        this.Price = Price;
    }
    

    public Punish(){}
    
    @Override
    public String toString(){
        return PunishName + '-' + String.valueOf(Price);
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Punish )) return false;
        Punish b = (Punish)o;
        return Id == b.Id;
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(Id);
    }
}
