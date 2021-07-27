/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.io.FileInputStream;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity(name = "Customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
     private int Id ;
    
    @Column(name="cus_name",columnDefinition = "nvarchar(MAX)")
    
     private String CusName;
    
    @Column(name="cus_email",columnDefinition = "nvarchar(MAX)")
    
     private String CusEmail;
    
    @Column(name="cus_tel",columnDefinition = "nvarchar(MAX)")
    
    private String CusTel;
    
    @Column(name="cus_cmnd",columnDefinition = "nvarchar(MAX)")
    
    private String CusCmnd;
    
    @Column(name="cus_add",columnDefinition = "nvarchar(MAX)")
    
    private String CusAdd;

//    @Column(name="area_id")
    @ManyToOne
    private Areas Area;

    public Customer(int Id, String CusName, String CusEmail, String CusTel, String CusCmnd, String CusAdd, Areas Area) {
        this.Id = Id;
        this.CusName = CusName;
        this.CusEmail = CusEmail;
        this.CusTel = CusTel;
        this.CusCmnd = CusCmnd;
        this.CusAdd = CusAdd;
        this.Area = Area;
    }

    public int getId() {
        return Id;
    }

    public String getCusName() {
        return CusName;
    }

    public String getCusEmail() {
        return CusEmail;
    }

    public String getCusTel() {
        return CusTel;
    }

    public String getCusCmnd() {
        return CusCmnd;
    }

    public String getCusAdd() {
        return CusAdd;
    }

    public Areas getArea() {
        return Area;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public void setCusEmail(String CusEmail) {
        this.CusEmail = CusEmail;
    }

    public void setCusTel(String CusTel) {
        this.CusTel = CusTel;
    }

    public void setCusCmnd(String CusCmnd) {
        this.CusCmnd = CusCmnd;
    }

    public void setCusAdd(String CusAdd) {
        this.CusAdd = CusAdd;
    }

    public void setArea(Areas Area) {
        this.Area = Area;
    }

    
    public Customer(){}

     @Override
    public String toString(){
        return CusName;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Customer )) return false;
        Customer c = (Customer)o;
        return Id == c.Id;
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(Id);
    }
    

}
