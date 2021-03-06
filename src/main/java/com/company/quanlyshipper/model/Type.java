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
 * @author Admin
 */
@Data
@Entity(name = "Type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public int getId() {
        return Id;
    }

    public String getTypeName() {
        return TypeName;
    }
    
    @Basic
    @Column(name="type_name",columnDefinition = "nvarchar(50)")
    private String TypeName;

    public Type(int Id, String TypeName) {
        this.Id = Id;
        this.TypeName = TypeName;
    }
    
    public Type(){}
}
