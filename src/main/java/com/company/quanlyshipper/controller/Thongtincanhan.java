package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.LoginService;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Thongtincanhan implements Initializable {

    @Autowired
    private CustomerService service;
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AreaService areaService;
    
    private File file;
    private FileInputStream fs;  
    
    private Users user;
    @FXML
    private Button saveBtn;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private TextField cmndTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private ComboBox<Areas> areaCbb;

    @FXML
    private ImageView imageView;

    @FXML
    private Button browse;

    @FXML
    private TextField registerNoTxt;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    void choosePicture() {

    }


    
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = userService.findById(Main.currentUser.getId());;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        fullNameTxt.setText(user.getFullName());     
            telTxt.setText(user.getTel()); 
            cmndTxt.setText(user.getCmnd());  
            emailTxt.setText(user.getEmail()); 
            userNameTxt.setText(user.getUserName());
            passwordTxt.setText(user.getPassword());
            registerNoTxt.setText(user.getRegisterNo());
            areaCbb.setValue(user.getArea());
        try {
            init(user);
        } catch (IOException ex) {
            Logger.getLogger(Thongtincanhan.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
            
    }    
    private void init(Users user) throws FileNotFoundException, IOException{
        if(user.getImage() != null){
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                os.write(user.getImage());
                os.close();
                Image image = new Image("file:photo.jpg",151,142,true,true);
                imageView.setImage(image);
                imageView.setFitWidth(151);
                imageView.setFitHeight(142);

                imageView.setPreserveRatio(true);
            }
    }
    @FXML
    private void saveCus(){
//        Users user = userService.findById(Main.currentUser.getId());
        user.setFullName(fullNameTxt.getText());            
        user.setTel(telTxt.getText());
        user.setCmnd(cmndTxt.getText());
        user.setEmail(emailTxt.getText());
        user.setArea(areaCbb.getValue());
        user.setRegisterNo(registerNoTxt.getText());
        user.setUserName(userNameTxt.getText());
        user.setPassword(passwordTxt.getText());        
//        user.setImage(this.user.getImage());

        if (validate()== true){
            save(user);
        }
    }
    private boolean validate(){
        
        if (fullNameTxt.getText().toString() == null || fullNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Tên không được để trống")
                .build().show();
            fullNameTxt.requestFocus();
            return false;
        }
        if (telTxt.getText().toString() == null || telTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Số điện thoại không được để trống")
                .build().show();
            telTxt.requestFocus();
            return false;
        }
        if (userNameTxt.getText().toString() == null || userNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Tên đăng nhập không được để trống")
                .build().show();
            telTxt.requestFocus();
            return false;
        }
        if (passwordTxt.getText().toString() == null || passwordTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Mật khẩu không được để trống")
                .build().show();
            passwordTxt.requestFocus();
            return false;
        }
        
        return true;
    }
    private void save(Users u){
        userService.save(u);
    }
    
     @FXML
    void choosePicture(ActionEvent event) {
        Stage stage = (Stage)fullNameTxt.getScene().getWindow();
        user = userService.findById(Main.currentUser.getId());
        FileChooser fc = new FileChooser();
        fc.setTitle("chọn ảnh");
        this.file = fc.showOpenDialog(stage);
        if(this.file != null){
            Image image = new Image(this.file.toURI().toString(),151,142,true,true);
            imageView.setImage(image);
            imageView.setFitWidth(151);
            imageView.setFitHeight(142);
            
            imageView.setPreserveRatio(true);
            try {
                byte[]img = Files.readAllBytes(file.toPath());
                
                Main.currentUser.setImage(img);                
                this.user.setImage(img);

            } catch (Exception e){
                
            }
        }
    }
    
}
