/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

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
@Entity(name = "Bonus")
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
    
    @Basic
    @Column(name="bonus_name",columnDefinition = "nvarchar(50)")
    private String BonusName;
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getBonusName() {
        return BonusName;
    }

    public void setBonusName(String BonusName) {
        this.BonusName = BonusName;
    }

    public Bonus(int Id, String BonusName) {
        this.Id = Id;
        this.BonusName = BonusName;
    }
    

    public Bonus(){}
}
