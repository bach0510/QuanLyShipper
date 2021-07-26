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
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Thuongphat implements Initializable {

    @Autowired
    private CustomerService service;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private PayOffService payOffService;
    
    @FXML
    private TableView<Bonus> bonusTable;
    @FXML
    private TableView<Punish> punishTable;
    
    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button refreshBtn;
   

    @FXML
    private TableColumn<Bonus, String> bonusName;

    @FXML
    private TableColumn<Punish, String> punishName;

    
    private Bonus bonus;
    
    private Punish punish;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bonusName.setCellValueFactory(new PropertyValueFactory<>("bonusName"));
        punishName.setCellValueFactory(new PropertyValueFactory<>("punishName"));
        search();
        
        bonusTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Bonus b = bonusTable.getSelectionModel().getSelectedItem();
                this.bonus = b;
                if (b != null){
                    Chinhsuathuongphat.editPayoff(1, b ,this::saveBonus,null, this::savePunish);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Bonus b = bonusTable.getSelectionModel().getSelectedItem();
                this.bonus = b;
            }
        });
        punishTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Punish p = punishTable.getSelectionModel().getSelectedItem();
                this.punish = p;
                if (p != null){
                    Chinhsuathuongphat.editPayoff(2, null ,this::saveBonus,p, this::savePunish);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Punish p = punishTable.getSelectionModel().getSelectedItem();
                this.punish = p;
            }
        });
    }    
    
    @FXML
    void search(){
        bonusTable.getItems().clear();
        List<Bonus> bonusList = payOffService.getAllBonus();
        bonusTable.getItems().addAll(bonusList);
        punishTable.getItems().clear();
        List<Punish> punishList = payOffService.getAllPunish();
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
        Chinhsuathuongphat.addNewPayoff(1, this::saveBonus, this::savePunish);
    }
    
    @FXML
    void addNewPunish(){
        Chinhsuathuongphat.addNewPayoff(2, this::saveBonus, this::savePunish);
    }
    
//    @FXML
//    void addNewUser(){
//        Chinhsuattkh.addNew(this::save,areaService::getAllArea);
//    }
    
    private void saveBonus(Bonus b){
        payOffService.saveBonus(b);
        search();
    }
   private void savePunish(Punish p){
        payOffService.savePunish(p);
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
                    payOffService.deletePunish(this.punish);
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
                    payOffService.deleteBonus(this.bonus);
                    search();
                }).build().show(); 
        }
        
    }
    
}
