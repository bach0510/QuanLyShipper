/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;

import com.company.quanlyshipper.QuanlyshipperApplication;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.utils.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Admin
 */
@Controller
public class Chamcong implements Initializable {
    
    @FXML
    private StackPane contentView;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkIn();
    }
    
    @FXML
    void checkIn(){
        try{
            contentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/checkin.fxml"));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            contentView.getChildren().add(view);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void checkOut(){
        try{
            contentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/checkout.fxml"));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            contentView.getChildren().add(view);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
 
}
