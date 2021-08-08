package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Bonus;
import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Punish;
import com.company.quanlyshipper.model.UserBonus;
import com.company.quanlyshipper.model.UserPunish;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.LoginService;
import com.company.quanlyshipper.service.PayOffService;
import com.company.quanlyshipper.service.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Thuongphatnv implements Initializable {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PayOffService payOffService;
    
    @FXML
    private TableView<UserBonus> bonusTable;
    @FXML
    private TableView<UserPunish> punishTable;
    
    @FXML
    private Button deleteBtnBonus;
    @FXML
    private Button deleteBtnPunish;
    @FXML
    private Button addNewBtnBonus;
    @FXML
    private Button addNewBtnPunish;
    
    @FXML
    private Button refreshBtn;
   

    @FXML
    private TableColumn<User, String> shipperName;
    @FXML
    private TableColumn<Bonus, String> bonusName;
    @FXML
    private TableColumn<UserBonus, String> bonusDate;

    @FXML
    private TableColumn<User, String> shipperName2;
    @FXML
    private TableColumn<Punish, String> punishName;
    @FXML
    private TableColumn<UserPunish, String> punishDate;

    
    private UserBonus bonus;
    
    private UserPunish punish;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bonusName.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        punishName.setCellValueFactory(new PropertyValueFactory<>("punish"));
        shipperName.setCellValueFactory(new PropertyValueFactory<>("user"));
        shipperName2.setCellValueFactory(new PropertyValueFactory<>("user"));
        bonusDate.setCellValueFactory(new PropertyValueFactory<>("bonusTime"));
        punishDate.setCellValueFactory(new PropertyValueFactory<>("punishTime"));
        
        if(Main.currentUser.getRoleId() == 2){
            addNewBtnBonus.setVisible(false);
            addNewBtnBonus.setManaged(false);
            addNewBtnPunish.setVisible(false);
            addNewBtnPunish.setManaged(false);
            deleteBtnBonus.setVisible(false);
            deleteBtnBonus.setManaged(false);
            deleteBtnPunish.setVisible(false);
            deleteBtnPunish.setManaged(false);
        }
        search();
        
        bonusTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                UserBonus b = bonusTable.getSelectionModel().getSelectedItem();
                this.bonus = b;
                if (b != null && Main.currentUser.getRoleId() == 1){
                    Chinhsuathuongphatnv.editPayoff(1, b ,this::saveBonus,null, this::savePunish,userService::getAllShipper,payOffService::getAllBonus,payOffService::getAllPunish);
                }
            }
            if (e.getClickCount() >= 1)
            {
                UserBonus b = bonusTable.getSelectionModel().getSelectedItem();
                this.bonus = b;
            }
        });
        punishTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                UserPunish p = punishTable.getSelectionModel().getSelectedItem();
                this.punish = p;
                if (p != null && Main.currentUser.getRoleId() == 1){
                    Chinhsuathuongphatnv.editPayoff(2, null ,this::saveBonus,p, this::savePunish,userService::getAllShipper,payOffService::getAllBonus,payOffService::getAllPunish);
                }
            }
            if (e.getClickCount() >= 1)
            {
                UserPunish p = punishTable.getSelectionModel().getSelectedItem();
                this.punish = p;
            }
        });
    }    
    
    @FXML
    void search(){
        bonusTable.getItems().clear();
        List<UserBonus> bonusList = payOffService.getAllUserBonus();
        bonusTable.getItems().addAll(bonusList);
        punishTable.getItems().clear();
        List<UserPunish> punishList = payOffService.getAllUserPunish();
        punishTable.getItems().addAll(punishList);
        this.bonus = null;        
        this.punish = null;

    }
    
    @FXML
    void reloadSearchInput(){
        search();
    }
    
    @FXML
    void addNewBonus(){
        Chinhsuathuongphatnv.addNewPayoff(1, this::saveBonus, this::savePunish,userService::getAllShipper,payOffService::getAllBonus,payOffService::getAllPunish);
    }
    
    @FXML
    void addNewPunish(){
        Chinhsuathuongphatnv.addNewPayoff(2, this::saveBonus, this::savePunish,userService::getAllShipper,payOffService::getAllBonus,payOffService::getAllPunish);
    }
    
//    @FXML
//    void addNewUser(){
//        Chinhsuattkh.addNew(this::save,areaService::getAllArea);
//    }
    
    private void saveBonus(UserBonus b){
        payOffService.saveUserBonus(b);
        search();
    }
   private void savePunish(UserPunish p){
        payOffService.saveUserPunish(p);
        search();
    }
    @FXML
    private void deletePunish(){
        if (this.punish == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Không thể tìm thấy thông tin")
                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Thông tin này sẽ bị xóa ra khỏi hệ thống")
                .message("Việc làm này sẽ không thể hoàn tác , Bạn có chắc chắn muốn xóa thông tin này không ?")
                .okAction(()-> {
                    payOffService.deleteUserPunish(this.punish);
                    search();
                }).build().show(); 
        }
        
    }
    @FXML
    private void deleteBonus(){
        if (this.bonus == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Không thể tìm thấy thông tin")
                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Thông tin sẽ bị xóa ra khỏi hệ thống")
                .message("Việc làm này sẽ không thể hoàn tác , Bạn có chắc chắn muốn xóa thông tin này không ?")
                .okAction(()-> {
                    payOffService.deleteUserBonus(this.bonus);
                    search();
                }).build().show(); 
        }
        
    }
    
}
