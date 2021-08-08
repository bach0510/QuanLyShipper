package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Bonus;
import com.company.quanlyshipper.model.Punish;
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
public class Chinhsuathuongphat implements Initializable {

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
    private TextField payoffNameTxt;
    
    @FXML
    private TextField titleTxt;
    
    @FXML
    private TextField priceTxt;
    
    @FXML
    private Button saveBtn;  
    @FXML
    private Button cancelBtn; 
    
    private int type; 
    private Bonus bonus; 
    
    private Punish punish; 
    
    
    private static Stage stage;

    
    @FXML
    private Consumer<Bonus> saveHandlerBonus;
    @FXML
    private Consumer<Punish> saveHandlerPunish;

    private Supplier<List<Areas>> areaList;
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
//            Users user = service.getShipperInfoByCode(code)
            if(validate()==true){
                if (type == 1){
                    bonus.setBonusName(payoffNameTxt.getText());   
                    double payoff = Double.parseDouble(priceTxt.getText() != null ? priceTxt.getText() : "0");
                    bonus.setPrice(payoff); 

                    saveHandlerBonus.accept(bonus);
                }
                if (type == 2){
                    String punishName = payoffNameTxt.getText();
                    punish.setPunishName(punishName); 
                    double payoff = Double.parseDouble(priceTxt.getText() != null ? priceTxt.getText() : "0");
                    punish.setPrice(payoff);   

                    saveHandlerPunish.accept(punish);
                }
                cancel();
            }
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean validate(){
        
        if (payoffNameTxt.getText().toString() == null || payoffNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Nội dung không được để trống")
                .build().show();
            payoffNameTxt.requestFocus();
            return false;
        }
        if (priceTxt.getText().toString() == null || priceTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Số tiền không được để trống")
                .build().show();
            priceTxt.requestFocus();
            return false;
        }
        if (Double.parseDouble(priceTxt.getText()) < 0){
            Thongbao.ThongbaoBuilder.builder()
                .message("Số tiền không được nhỏ hơn 0")
                .build().show();
            priceTxt.requestFocus();
            return false;
        }
        
        return true;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public static void editPayoff(int type , Bonus bonus , Consumer<Bonus> saveHandlerBonus,Punish punish , Consumer<Punish> saveHandlerPunish){
        
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuathuongphat.class.getClassLoader().getResource("view/chinhsuathuongphat.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuathuongphat controller = loader.getController();
            if (type == 1){
                controller.initBonus(bonus , saveHandlerBonus);
            }
            if (type == 2){
                controller.initPunish(punish , saveHandlerPunish);
            }
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNewPayoff(int type ,Consumer<Bonus> saveHandlerBonus,Consumer<Punish> saveHandlerPunish){
        editPayoff(type,null,saveHandlerBonus,null,saveHandlerPunish);
    }
    
    private void initBonus(Bonus bonus , Consumer<Bonus> saveHandler) throws FileNotFoundException, IOException{
        this.bonus = bonus;
        this.type = 1;
        this.saveHandlerBonus = saveHandler;
        if(bonus == null){
            titleTxt.setText("Thêm mới thưởng");
            this.bonus = new Bonus();
        }
        else {
            titleTxt.setText("Chỉnh sửa thưởng");
            this.bonus = bonus;
            payoffNameTxt.setText(bonus.getBonusName());     
            priceTxt.setText(String.valueOf(bonus.getPrice()));
        }
        
        
    }
    private void initPunish(Punish punish , Consumer<Punish> saveHandler) throws FileNotFoundException, IOException{
        this.punish = punish;
        this.saveHandlerPunish = saveHandler;
        this.type = 2;
        if(punish == null){
            titleTxt.setText("Thêm mới phạt");
            this.punish = new Punish();
        }
        else {
            titleTxt.setText("Chỉnh sửa phạt");
            this.punish = punish;
            payoffNameTxt.setText(punish.getPunishName());     
            priceTxt.setText(String.valueOf(punish.getPrice()));
        }
        
        
    }
    
    
}
