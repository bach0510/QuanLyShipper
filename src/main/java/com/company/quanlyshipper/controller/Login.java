/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;


import com.company.quanlyshipper.AppException;
import static com.company.quanlyshipper.MsSqlConnection.ConnectDb;
import com.company.quanlyshipper.QuanlyshipperApplication;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.LoginService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;

/**
 *
 * @author Admin
 */
@Controller
public class Login implements Initializable{

    private JdbcTemplate jdbcTemplate;
    
    private static Users user; 
    @Autowired
    private LoginService service;
    
    @FXML
    private Label error;
    
    @FXML
    private TextField userNameTxt;
    
    @FXML
    private PasswordField passwordTxt;
    
    @FXML
    private Button loginBtn;
    
    @FXML
    private Button signUpBtn;
    
    @FXML
    void login(){
        //Connection conn = ConnectDb();
        try{
            user = service.login(userNameTxt.getText(), passwordTxt.getText());
            Main.loadView();
            userNameTxt.getScene().getWindow().hide();
    //            PreparedStatement ps = conn.prepareStatement("select * from users where UserName = ? and Password = ?" );
    //            ps.setString(1, userNameTxt.getText().toString());           
    //            ps.setString(2, passwordTxt.getText().toString());
    //            ResultSet rs = ps.executeQuery();
    //            if(!rs.next()) error.setText("kiểm tra lại thông tin đăng nhập");
    //            else {
    //                Main.loadView();
    //                userNameTxt.getScene().getWindow().hide();
    //            }

                
        } catch (AppException e){
            error.setText(e.getMessage());
        } catch (Exception e){
            error.setText("kiểm tra lại thông tin đăng nhập");
            e.printStackTrace();
        }
    }
    
    @FXML
    void loginByEnter(){
//        if (event.)
    }
    
    @FXML
    void signUp(){
        SignUp.loadView();
    }
    
    public static void loadView() {
        try {
            //Parent view = FXMLLoader.load(QuanlyshipperApplication.class.getClassLoader().getResource("view/Login.fxml"));
            
            FXMLLoader loader = new FXMLLoader(Login.class.getClassLoader().getResource("view/Login.fxml"));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(view));
            
            Image img = new Image("pictures/logo.png"); // set logo cho man hinh chinh
            stage.getIcons().add(img);
            
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }
    
}
