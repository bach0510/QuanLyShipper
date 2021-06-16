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
//import javax.persistence.Table;

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
    @Column(name="image",columnDefinition = "image")
    
    private byte[] Image;
    @Column(name="type",columnDefinition = "nvarchar(MAX)")
    
    private String Type;
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
    
    @Column(name="register_no",columnDefinition = "nvarchar(MAX)")
    
    private String RegisterNo;

//    @Column(name="area_id")
    @ManyToOne
    private Areas Area;
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

    public void setArea(Areas Area) {
        this.Area = Area;
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

    public Areas getArea() {
        return Area;
    }

    public int getRoleId() {
        return RoleId;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }

    public void setCmnd(String Cmnd) {
        this.Cmnd = Cmnd;
    }

    public String getCode() {
        return Code;
    }

    public String getCmnd() {
        return Cmnd;
    }

    public void setImage(byte[] Image) {
        this.Image = Image;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public byte[] getImage() {
        return Image;
    }

    public String getType() {
        return Type;
    }
    
    @PersistenceConstructor
    public Users(int Id, String UserName, String Password, String FullName, String Email, String Tel, Areas Area, int RoleId) {
        this.Id = Id;
        this.UserName = UserName;
        this.Password = Password;
        this.FullName = FullName;
        this.Email = Email;
        this.Tel = Tel;
        this.Area = Area;
        this.RoleId = RoleId;
    }
    public Users(){}

     @Override
    public String toString(){
        return Code;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Users )) return false;
        Users user = (Users)o;
        return Id == user.Id;
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(Id);
    }
    

}
