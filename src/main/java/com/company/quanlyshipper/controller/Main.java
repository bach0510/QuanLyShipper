/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;

import com.company.quanlyshipper.QuanlyshipperApplication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Main implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void loadView() {
        try {
            Stage stage = new Stage();
            Parent view = FXMLLoader.load(QuanlyshipperApplication.class.getClassLoader().getResource("view/Main.fxml"));
            stage.setScene(new Scene(view));
            
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
