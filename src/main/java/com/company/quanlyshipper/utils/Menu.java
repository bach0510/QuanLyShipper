/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.utils;

/**
 *
 * @author Admin
 */
public enum Menu {
    home("home"),
    khachhang("khachhang"),    
    quanlydonhang("quanlydonhang"),
    quanlyttnv("quanlyttnv"),    
    tinhluong("tinhluong"),
    donhangduocgiao("donhangduocgiao");
    
    private String title;

    Menu(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getFxml() {
        return String.format("view/%s.fxml", name());
    }
}
