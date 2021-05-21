package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Users;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Chinhsuattnv implements Initializable {

      @FXML
    private TextField titleTxt;

    @FXML
    private Button saveBtn;
    
    @FXML
    private Users user;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private TextField cmndTxt;

    @FXML
    private TextField codeTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button saveBtn1;
    
    @FXML
    private Consumer<Users> saveHandler;

    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
            user.setFullName(fullNameTxt.getText());            
            user.setCode(codeTxt.getText());
            user.setTel(telTxt.getText());
            user.setCmnd(cmndTxt.getText());
            user.setEmail(emailTxt.getText());
            
            saveHandler.accept(user);
            
            cancel();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public static void editUser(Users user , Consumer<Users> saveHandler){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuattnv.class.getClassLoader().getResource("view/chinhsuattnv.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuattnv controller = loader.getController();
            controller.init(user , saveHandler);
            
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Users> saveHandler){
        editUser(null,saveHandler);
    }
    
    private void init(Users user , Consumer<Users> saveHandler){
        this.user = user;
        this.saveHandler = saveHandler;
        if(user == null){
            titleTxt.setText("Thêm mới nhân viên");
            this.user = new Users();
            this.user.setRoleId(2);
        }
        else {
            titleTxt.setText("Chỉnh sửa nhân viên");
            this.user = user;
            fullNameTxt.setText(user.getFullName());     
            codeTxt.setText(user.getCode()); 
            telTxt.setText(user.getTel()); 
            cmndTxt.setText(user.getCmnd()); 
            emailTxt.setText(user.getEmail()); 
            
        }
        
        
    }
    
}
