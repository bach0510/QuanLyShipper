package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.LoginService;
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
public class Quanlyttnv implements Initializable {

    @Autowired
    private UserService service;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private TableView<Users> tableView;
    
    @FXML
    private Button addNewBtn;
    
     @FXML
    private ComboBox typeCbb;
    @FXML
    private ComboBox<Areas> areaCbb;
    
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
    @FXML
    private TableColumn<Users, String> type;
    
    private Users user;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        ObservableList<String> listCbb = FXCollections.observableArrayList("T???t c???","Ship l???y","Ship giao");
        typeCbb.getItems().clear();
        typeCbb.setItems(listCbb);
        typeCbb.setValue("T???t c???");
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
        cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        area.setCellValueFactory(new PropertyValueFactory<>("area"));
        search();
        
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Users user = tableView.getSelectionModel().getSelectedItem();
                this.user = user;
                if (user != null){
                    Chinhsuattnv.editUser(user, this::save,areaService::getAllArea);
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
        String typeValue = typeCbb.getValue().toString() == "T???t c???" ?  "" : typeCbb.getValue().toString();
        List<Users> userList = service.getAllShipperInfo(
                fullNameTxt.getText().toString(), 
                cmndTxt.getText().toString(), 
                telTxt.getText().toString(), 
                codeTxt.getText().toString(), 
                emailTxt.getText().toString(),
                typeValue,
                areaCbb.getValue());
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
        typeCbb.setValue("T???t c???");
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        search();
    }
    
    @FXML
    void addNewUser(){
        Chinhsuattnv.addNew(this::save,areaService::getAllArea);
    }
    
    private void save(Users user){
        service.save(user);
        search();
    }
    @FXML
    private void deleteUser(){
        if (this.user == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Kh??ng th??? t??m th???y th??ng tin")
                .message("Vui l??ng ch???n 1 th??ng tin trong b???ng ????? th???c hi???n thao t??c")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Th??ng tin c???a nh??n vi??n n??y s??? b??? x??a ra kh???i h??? th???ng")
                .message("Vi???c l??m n??y s??? kh??ng th??? ho??n t??c , B???n c?? ch???c ch???n mu???n x??a th??ng tin n??y kh??ng ?")
                .okAction(()-> {
                    service.deleteUser(this.user);
                    search();
                }).build().show(); 
        }
        
    }
    
}
