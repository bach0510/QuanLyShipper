package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.OrderDetail;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.service.OrderService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Tracuumavandon implements Initializable {

    @FXML
    private TextField orderCodeTxt;

    @FXML
    private TableView<OrderDetail> orderDetailTable;

    @FXML
    private TableColumn<OrderDetail, String> orderDetailName;

    @FXML
    private TableColumn<OrderDetail, String> qty;

    @FXML
    private TableColumn<OrderDetail, String> price;

    @FXML
    private TableColumn<OrderDetail, String> sumPrice;

    @FXML
    private TextField cusTelTxt;

    @FXML
    private TextField cusNameTxt;

    @FXML
    private TextField shipperNameTxt;

    @FXML
    private ComboBox orderStatusCbb;

    @FXML
    private TextField shipperTelTxt;

    @FXML
    private TextField shipperEmailTxt;

    @FXML
    private DatePicker deliveryDatepicker;

    @FXML
    private ComboBox<Areas> areaCbb;

    @FXML
    private TextField shipperCodeTxt;

    @FXML
    private TextField deliveryAddTxt;
    
    @Autowired
    private OrderService service;

    @FXML
    void search(MouseEvent event) {

    }

    @FXML
    void searchOrderInfo() {
        Orders order = service.searchOrderByCode(orderCodeTxt.getText());
        orderDetailTable.getItems().clear();
        List<OrderDetail> orderDetailList = service.getAllOrderDetail(order.getId());
        orderDetailTable.getItems().addAll(orderDetailList);
        
        cusNameTxt.setText(order.getCusName());     
        cusTelTxt.setText(order.getCusTel()); 
        deliveryAddTxt.setText(order.getDeliveryAdd()); 

        deliveryDatepicker.setValue(order.getDeliveryDate());
        areaCbb.setValue(order.getArea());
        orderStatusCbb.setValue(order.getStatus().toString());

        shipperCodeTxt.setText(order.getUser().getCode());
        shipperNameTxt.setText(order.getUser().getFullName());
        shipperTelTxt.setText(order.getUser().getTel());
        shipperEmailTxt.setText(order.getUser().getEmail());
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderDetailName.setCellValueFactory(new PropertyValueFactory<>("name"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        sumPrice.setCellValueFactory(new PropertyValueFactory<>("sumPrice"));
    }    
    
}
