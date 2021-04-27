/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;

import com.company.quanlyshipper.QuanlyshipperApplication;
import com.company.quanlyshipper.model.Role;
import com.company.quanlyshipper.repo.RoleRepo;
import com.company.quanlyshipper.service.LoginService;
import com.company.quanlyshipper.service.SignUpService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SignUp implements Initializable {

    @Autowired
    private SignUpService service;
    @FXML
    private TextField userNameTxt;
    
    @FXML
    private TextField passwordTxt;
    
    @FXML
    private ComboBox roleCbb;
    
    public static void loadView() { 
        try {
            FXMLLoader loader = new FXMLLoader(SignUp.class.getClassLoader().getResource("view/SignUp.fxml"));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            Stage stage = new Stage();
            //Parent view = FXMLLoader.load(QuanlyshipperApplication.class.getClassLoader().getResource("view/SignUp.fxml"));
            stage.setScene(new Scene(view));
            
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Role> roles = service.getAllRole();
        //roleCbb.setItems((ObservableList) roles);
    }    
    
}
