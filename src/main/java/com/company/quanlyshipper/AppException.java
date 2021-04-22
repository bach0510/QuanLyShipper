/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper;

/**
 *
 * @author Bach
 */
public class AppException extends RuntimeException {
    public AppException(String mess){
        super(mess);
    }
}
