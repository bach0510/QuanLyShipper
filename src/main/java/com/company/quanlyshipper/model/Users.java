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
    
    @Column(name="role_Id")
    private int RoleId ;

     
    public int getId() {
        return Id;
    }
 
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    } 
    @Override
    public String toString() {
        return ""+UserName+"";
    }
    @PersistenceConstructor
    public Users(String userName, String password){
        this.Password = password;
        this.UserName = userName;
    }
    public Users(){}
}
