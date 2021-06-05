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
 * @author Admin
 */
@Data
@Entity(name = "Areas")
public class Areas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
    
    @Basic
    @Column(name="area_name",columnDefinition = "nvarchar(50)")
    private String AreaName;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public String getAreaName() {
        return AreaName;
    }
    public Areas(int Id, String AreaName) {
        this.Id = Id;
        this.AreaName = AreaName;
    }
    
    public Areas(){}
    
    @Override
    public String toString(){
        return AreaName;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Areas )) return false;
        Areas area = (Areas)o;
        return Id == area.Id;
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(Id);
    }
    
    
}
