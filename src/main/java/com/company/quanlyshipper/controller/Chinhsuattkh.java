package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.AreaService;
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
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Chinhsuattkh implements Initializable {

    @FXML
    private ImageView imageView;    
    
    private File file;
    private FileInputStream fs;    

    @Autowired
    private UserService service;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private ComboBox typeCbb;
    
    @FXML
    private  ComboBox<Areas> areaCbb;
    
    @FXML
    private Button browse;
    
    @FXML
    private TextField titleTxt;

    @FXML
    private Button saveBtn;
    
    @FXML
    private Customer cus;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField fullNameTxt;
    
    @FXML
    private TextField addressTxt;

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
    
    private static Stage stage;

    
    @FXML
    private Consumer<Customer> saveHandler;

    private Supplier<List<Areas>> areaList;
    
    private Supplier<List<Customer>> cusList;
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
            if(validate()==true){
                cus.setCusName(fullNameTxt.getText());            
                cus.setCusTel(telTxt.getText());
                cus.setCusCmnd(cmndTxt.getText());
                cus.setCusEmail(emailTxt.getText());
                cus.setArea(areaCbb.getValue());
                cus.setCusAdd(addressTxt.getText());

                saveHandler.accept(cus);

                cancel();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
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
        for(Customer x : cusList.get()){
            if (cus == null){
                if (telTxt.getText().toString().toLowerCase().equals(x.getCusTel().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Số điện thoại đã tồn tại")
                    .build().show();
                telTxt.requestFocus();
                return false;
                }
                if (emailTxt.getText().toString().toLowerCase().equals(x.getCusEmail().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Email đã tồn tại")
                    .build().show();
                emailTxt.requestFocus();
                return false;
                }
            }
            else{
                if (cus.getId() != x.getId() && telTxt.getText().toString().toLowerCase().equals(x.getCusTel().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Số điện thoại đã tồn tại")
                    .build().show();
                telTxt.requestFocus();
                return false;
                }
                if (cus.getId() != x.getId() && emailTxt.getText().toString().toLowerCase().equals(x.getCusEmail().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Email đã tồn tại")
                    .build().show();
                emailTxt.requestFocus();
                return false;
                }
            }
        }
        if (addressTxt.getText().toString() == null || addressTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Địa chỉ không được để trống")
                .build().show();
            addressTxt.requestFocus();
            return false;
        } 
        if ((emailTxt.getText().toString() != null && !emailTxt.getText().toString().equals("")) && !isValid(emailTxt.getText())){
            Thongbao.ThongbaoBuilder.builder()
                .message("Email không hợp lệ")
                .build().show();
            emailTxt.requestFocus();
            return false;
        }   
        return true;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ObservableList<String> listCbb = FXCollections.observableArrayList("Ship lấy","Ship giao");
//        typeCbb.getItems().clear();
//        typeCbb.setItems(listCbb);
    }    
    
    public static void editUser(Customer cus , Consumer<Customer> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Customer>> cusList){
        
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuattkh.class.getClassLoader().getResource("view/chinhsuattkh.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuattkh controller = loader.getController();
            controller.init(cus , saveHandler,areaList,cusList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Customer> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Customer>> cusList){
        editUser(null,saveHandler,areaList,cusList);
    }
    
    private void init(Customer cus , Consumer<Customer> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Customer>> cusList) throws FileNotFoundException, IOException{
        this.cus = cus;
        this.cusList = cusList;
        this.saveHandler = saveHandler;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaList.get());
        if(cus == null){
            this.cus = new Customer();
            Areas area = new Areas();
            area.setAreaName("Thanh Xuân");
            area.setAreaCode("TX");
            area.setId(1);
            areaCbb.setValue(area);
        }
        else {
            this.cus = cus;
            fullNameTxt.setText(cus.getCusName());     
            telTxt.setText(cus.getCusTel()); 
            cmndTxt.setText(cus.getCusCmnd());  
            emailTxt.setText(cus.getCusEmail());            
            addressTxt.setText(cus.getCusAdd()); 
            areaCbb.setValue(cus.getArea());
        }
        
        
    }

    
    
}
