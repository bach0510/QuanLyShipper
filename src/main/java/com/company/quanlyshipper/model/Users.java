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
import org.springframework.data.annotation.PersistenceConstructor;
//import javax.persistence.Table;

//@Entity
//@Table(name = "user")

@Entity(name = "Users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
     private int Id ;
    
    //@Id
    @Column(name="user_name",columnDefinition = "nvarchar(MAX)")
    
     private String UserName;
    @Basic
    @Column(name="password")
     private String Password;
    
    @Column(name="full_name",columnDefinition = "nvarchar(MAX)")
    
     private String FullName;
    
    @Column(name="email",columnDefinition = "nvarchar(MAX)")
    
     private String Email;
    
    @Column(name="tel",columnDefinition = "nvarchar(MAX)")
    
    private String Tel;
    
    @Column(name="code",columnDefinition = "nvarchar(MAX)")
    
    private String Code;
    @Column(name="cmnd",columnDefinition = "nvarchar(MAX)")
    
    private String Cmnd;

    @Column(name="area_id")
    
    private int AreaId;
    @Column(name="role_id")
    private int RoleId ;

    public int getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public void setAreaId(int AreaId) {
        this.AreaId = AreaId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getTel() {
        return Tel;
    }

    public int getAreaId() {
        return AreaId;
    }

    public int getRoleId() {
        return RoleId;
    }

    @Override
    public String toString() {
        return ""+UserName+"";
    }
    
    @PersistenceConstructor
    public Users(int Id, String UserName, String Password, String FullName, String Email, String Tel, int AreaId, int RoleId) {
        this.Id = Id;
        this.UserName = UserName;
        this.Password = Password;
        this.FullName = FullName;
        this.Email = Email;
        this.Tel = Tel;
        this.AreaId = AreaId;
        this.RoleId = RoleId;
    }
    public Users(){}
}
