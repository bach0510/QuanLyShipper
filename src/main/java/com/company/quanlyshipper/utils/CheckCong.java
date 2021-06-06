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
public enum CheckCong {
    checkin("checkin"),
    checkout("checkout");
    
    private String title;

    CheckCong(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getFxml() {
        return String.format("view/%s.fxml", name());
    }
}
