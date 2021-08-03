package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.OrderService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Donhangduocgiao implements Initializable {

    @FXML
    private Button addOrderBtn;    
    
    @FXML
    private Button addOrderDetailBtn;  
    
    private Users currentUser;  
    
    private Orders order;
    

    @Autowired
    private OrderService service;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableColumn<Orders, String> orderCode;
    @FXML
    private TableColumn<Orders, String> createDate;
    @FXML
    private TableColumn<Orders, String> status;
    
    @FXML
    private ComboBox statusCbb;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    void search(){
        currentUser = Main.currentUser;
        orderTable.getItems().clear();
        String statusValue = statusCbb.getValue().toString() == "Tất cả" ? "" : statusCbb.getValue().toString();
        List<Orders> orderList = service.getAllAssignedOrder(statusValue,currentUser);
        orderTable.getItems().addAll(orderList);
        this.order = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("Tất cả","Mới tạo","Đang giao","Đã giao","Hoàn trả");
        statusCbb.getItems().clear();
        statusCbb.setItems(listCbb);
        statusCbb.setValue("Tất cả");
        
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        orderTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
                if (order != null){
//                    /Tracuumavandon.loadView(order);
                }
            }
        });
        search();
    }    
    
}
