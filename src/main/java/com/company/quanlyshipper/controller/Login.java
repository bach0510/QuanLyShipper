/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;


import com.company.quanlyshipper.AppException;
import static com.company.quanlyshipper.MsSqlConnection.ConnectDb;
import com.company.quanlyshipper.QuanlyshipperApplication;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Type;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.repo.TypeRepo;
import com.company.quanlyshipper.repo.UsersRepo;
import com.company.quanlyshipper.service.LoginService;
import java.io.IOException;
import java.net.URL;
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
    
    @Autowired
    private UsersRepo userRepo;
    
    @Autowired
    private AreasRepo areaRepo;
    
    @Autowired
    private TypeRepo typeRepo;
    
    @FXML
    private Label error;
    
    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField passwordTxt;
    
//    @FXML
//    private PasswordField passwordTxt;
    
    @FXML
    private Button loginBtn;
    
    @FXML
    private Button signUpBtn;
    
    @FXML
    void login(){
        //Connection conn = ConnectDb();
        try{
            user = service.login(userNameTxt.getText(), passwordTxt.getText());
            if (user != null){
                Main.loadView();
                userNameTxt.getScene().getWindow().hide();
            }
            else{
                error.setText("kiểm tra lại thông tin đăng nhập");
            } 
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
            
            FXMLLoader loader = new FXMLLoader(Login.class.getClassLoader().getResource("view/login.fxml"));
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
        autoCreateLoginData();
    }
    
    void autoCreateLoginData() {
        Users user = new Users();
        user.setRoleId(1);
        user.setUserName("admin");
        user.setPassword("admin");
        userRepo.save(user);
        
        Type type = new Type();
        type.setTypeName("Ship lấy");
        typeRepo.save(type);
        type.setTypeName("Ship giao");
        typeRepo.save(type);
        
        Areas area = new Areas();
        area.setAreaName("Thanh Xuân");
        areaRepo.save(area);
        Areas area2 = new Areas();
        area2.setAreaName("Hoàng Mai");
        areaRepo.save(area2);
        Areas area3 = new Areas();
        area3.setAreaName("Ba Đình");
        areaRepo.save(area3);
        Areas area4 = new Areas();
        area4.setAreaName("Hà Đông");
        areaRepo.save(area4);
        Areas area5 = new Areas();
        area5.setAreaName("Hai Bà Trưng");
        areaRepo.save(area5);
        
        
        
        
    }
    
}
