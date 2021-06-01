package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Chinhsuadonhang implements Initializable {

    
    @FXML
    private TextField titleTxt;

    @Autowired
    private UserService userService;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Orders order;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField cusTelTxt;

    @FXML
    private TextField cusNameTxt;

    @FXML
    private TextField shipperNameTxt;

    @FXML
    private TextField shipperCodeTxt;

    @FXML
    private ComboBox<?> orderStatusCbb;

    @FXML
    private TextField shipperTelTxt;

    @FXML
    private TextField shipperEmailTxt;

    @FXML
    private DatePicker deliveryDatepicker;

    @FXML
    private ComboBox<?> areaCbb;

    
    @FXML
    private Consumer<Orders> saveHandler;

    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
//            user.setFullName(fullNameTxt.getText());            
//            user.setCode(codeTxt.getText().toUpperCase());
//            user.setTel(telTxt.getText());
//            user.setCmnd(cmndTxt.getText());
//            user.setEmail(emailTxt.getText());
//            user.setType(typeCbb.getValue().toString());
        
            saveHandler.accept(order);
            
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
//        typeCbb.getItems().clear();
//        typeCbb.setItems(listCbb);
//        shipperCodeTxt.setOnInputMethodTextChanged(eh -> {
//            Users user = userService.getShipperInfoByCode(shipperCodeTxt.getText().toString());
//        
//            shipperNameTxt.setText("1");
//        });
    }    
    
    public static void editOrder(Orders order , Consumer<Orders> saveHandler){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuadonhang.class.getClassLoader().getResource("view/chinhsuadonhang.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuadonhang controller = loader.getController();
            controller.init(order , saveHandler);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Orders> saveHandler){
        editOrder(null,saveHandler);
    }
    
    @FXML
    void searchShipperInfo(InputMethodEvent event) {
        
        
    }
    
    private void init(Orders order , Consumer<Orders> saveHandler) throws FileNotFoundException, IOException{
        this.order = order;
        this.saveHandler = saveHandler;
        if(order == null){
            titleTxt.setText("Thêm mới đơn hàng");
//            this.order = new Orders();
//            this.user.setRoleId(2);
//            this.user.setType("Ship lấy");
//            typeCbb.setValue(this.user.getType());
        }
        else {
            titleTxt.setText("Chỉnh sửa đơn hàng");
            this.order = order;
//            fullNameTxt.setText(user.getFullName());     
//            codeTxt.setText(user.getCode()); 
//            telTxt.setText(user.getTel()); 
//            cmndTxt.setText(user.getCmnd()); 
//            emailTxt.setText(user.getEmail()); 
//            typeCbb.setValue(user.getType());
            
        }
        
        
    }
    
    
}
