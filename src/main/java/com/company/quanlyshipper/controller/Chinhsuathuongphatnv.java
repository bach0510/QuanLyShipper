package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Bonus;
import com.company.quanlyshipper.model.Punish;
import com.company.quanlyshipper.model.UserBonus;
import com.company.quanlyshipper.model.UserPunish;
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
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
public class Chinhsuathuongphatnv implements Initializable {

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
    private TextField shipperNameTxt;
    
    @FXML
    private Button saveBtn;  
    @FXML
    private Button cancelBtn; 
    
    @FXML
    private ComboBox<Bonus> bonusCbb;
    
    @FXML
    private ComboBox<Punish> punishCbb;
    
    @FXML
    private ComboBox<Users> userCbb;

    @FXML
    private DatePicker payoffDate;
    
    
    private int type; 
    private UserBonus bonus; 
    
    private UserPunish punish; 
    
    private Bonus bonusInfo; 
    
    private Punish punishInfo; 
    private Users user; 
    
    
    private static Stage stage;

    
    @FXML
    private Consumer<UserBonus> saveHandlerBonus;
    @FXML
    private Consumer<UserPunish> saveHandlerPunish;

    private Supplier<List<Users>> userList;
    private Supplier<List<Bonus>> bonusList;
    private Supplier<List<Punish>> punishList;
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
            LocalDate currentDate = LocalDate.now();
//            Users user = service.getShipperInfoByCode(code)
            if (type == 1){
                Bonus b = (bonusCbb.getValue());
                bonus.setBonus(b);           
                bonus.setUser(userCbb.getValue());
                bonus.setBonusTime(currentDate);
                saveHandlerBonus.accept(bonus);
            }
            if (type == 2){
                String punishName = payoffNameTxt.getText();
                punish.setPunish(punishCbb.getValue());  
                punish.setUser(userCbb.getValue());               
                punish.setPunishTime(currentDate);

        
                saveHandlerPunish.accept(punish);
            }
            
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
        
        userCbb.setOnAction(e -> {
            Users user = userCbb.getSelectionModel().getSelectedItem();
            shipperNameTxt.setText(user.getFullName());
        });
    }    
    
    public static void editPayoff(int type ,
            UserBonus bonus , 
            Consumer<UserBonus> saveHandlerBonus,
            UserPunish punish , 
            Consumer<UserPunish> saveHandlerPunish,
            Supplier<List<Users>> userList,
            Supplier<List<Bonus>> bonusList,
            Supplier<List<Punish>> punishList){
        
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuathuongphatnv.class.getClassLoader().getResource("view/chinhsuathuongphatnv.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuathuongphatnv controller = loader.getController();
            if (type == 1){
                controller.initBonus(bonus , saveHandlerBonus,userList,bonusList);
            }
            if (type == 2){
                controller.initPunish(punish , saveHandlerPunish,userList,punishList);
            }
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNewPayoff(int type ,Consumer<UserBonus> saveHandlerBonus,Consumer<UserPunish> saveHandlerPunish,
            Supplier<List<Users>> userList,
            Supplier<List<Bonus>> bonusList,
            Supplier<List<Punish>> punishList){
        editPayoff(type,null,saveHandlerBonus,null,saveHandlerPunish,userList,bonusList,punishList);
    }
    
    private void initBonus(UserBonus bonus , Consumer<UserBonus> saveHandler,Supplier<List<Users>> userList,
            Supplier<List<Bonus>> bonusList) throws FileNotFoundException, IOException{
        punishCbb.setVisible(false);
            punishCbb.setManaged(false);
            userCbb.getItems().clear();
        userCbb.getItems().addAll(userList.get());
        bonusCbb.getItems().clear();
        bonusCbb.getItems().addAll(bonusList.get());
        this.bonus = bonus;
        this.type = 1;
        this.saveHandlerBonus = saveHandler;
        if(bonus == null){
            titleTxt.setText("Thêm mới thưởng");
            this.bonus = new UserBonus();
            LocalDate currentDate = LocalDate.now();
            payoffDate.setValue(currentDate);
        }
        else {
            titleTxt.setText("Chỉnh sửa thưởng");
            this.bonus = bonus;
            payoffDate.setValue(bonus.getBonusTime());     
            userCbb.setValue(bonus.getUser());
            bonusCbb.setValue(bonus.getBonus());
            shipperNameTxt.setText(bonus.getUser().getFullName());
        }
        
        
    }
    private void initPunish(UserPunish punish , Consumer<UserPunish> saveHandler,Supplier<List<Users>> userList,
            Supplier<List<Punish>> punishList) throws FileNotFoundException, IOException{
        this.punish = punish;
        userCbb.getItems().clear();
        userCbb.getItems().addAll(userList.get());
        punishCbb.getItems().clear();
        punishCbb.getItems().addAll(punishList.get());
        bonusCbb.setVisible(false);
            bonusCbb.setManaged(false);
        this.saveHandlerPunish = saveHandler;
        this.type = 2;
        if(punish == null){
            titleTxt.setText("Thêm mới phạt");
            this.punish = new UserPunish();
            LocalDate currentDate = LocalDate.now();
            payoffDate.setValue(currentDate); 
        }
        else {
            titleTxt.setText("Chỉnh sửa phạt");
            this.punish = punish;
            payoffDate.setValue(punish.getPunishTime());     
            userCbb.setValue(punish.getUser());
            punishCbb.setValue(punish.getPunish());
            shipperNameTxt.setText(punish.getUser().getFullName());
        }
        
        
    }
    
    
}
