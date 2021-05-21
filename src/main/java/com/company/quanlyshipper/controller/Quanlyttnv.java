package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.LoginService;
import com.company.quanlyshipper.service.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class Quanlyttnv implements Initializable {

    @Autowired
    private UserService service;
    
    @FXML
    private TableView<Users> tableView;
    
    @FXML
    private Button addNewBtn;
    
    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button refreshBtn;

    @FXML
    private Button searchBtn;
    
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
    private TableColumn<Users, Integer> id;

    @FXML
    private TableColumn<Users, String> code;

    @FXML
    private TableColumn<Users, String> fullname;

    @FXML
    private TableColumn<Users, String> cmnd;

    @FXML
    private TableColumn<Users, String> tel;

    @FXML
    private TableColumn<Users, String> email;

    @FXML
    private TableColumn<Users, String> area;
    
    private Users user;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
        search();
        
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Users user = tableView.getSelectionModel().getSelectedItem();
                this.user = user;
                if (user != null){
                    Chinhsuattnv.editUser(user, this::save);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Users user = tableView.getSelectionModel().getSelectedItem();
                this.user = user;
            }
        });
    }    
    
    @FXML
    void search(){
        tableView.getItems().clear();
        List<Users> userList = service.getAllShipperInfo(fullNameTxt.getText().toString(), cmndTxt.getText().toString(), telTxt.getText().toString(), codeTxt.getText().toString(), emailTxt.getText().toString());
        tableView.getItems().addAll(userList);
        this.user = null;
    }
    
    @FXML
    void reloadSearchInput(){
        fullNameTxt.setText("");  
        cmndTxt.setText("");
        codeTxt.setText("");
        emailTxt.setText("");
        telTxt.setText("");
        search();
    }
    
    @FXML
    void addNewUser(){
        Chinhsuattnv.addNew(this::save);
    }
    
    private void save(Users user){
        service.save(user);
        search();
    }
    @FXML
    private void deleteUser(){
        if (this.user == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Không thể tìm thấy thông tin")
                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Thông tin của nhân viên này sẽ bị xóa ra khỏi hệ thống")
                .message("Việc làm này sẽ không thể hoàn tác , Bạn có chắc chắn muốn xóa thông tin này không ?")
                .okAction(()-> {
                    service.deleteUser(this.user);
                    search();
                }).build().show(); 
        }
        
    }
    
}
