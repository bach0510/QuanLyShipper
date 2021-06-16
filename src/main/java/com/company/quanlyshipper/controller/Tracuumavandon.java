package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.OrderDetail;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.OrderService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    
    @FXML
    private TextField orderStatusTxt;

    @FXML
    private TextField areaTxt;

    @FXML
    private TextField sumPriceTxt;
    
    @FXML
    private double orderSumPrice;
    
    @Autowired
    private OrderService service;
    

    @FXML
    void searchOrderInfo() {
        Orders order = service.searchOrderByCode(orderCodeTxt.getText());
        orderDetailTable.getItems().clear();
        List<OrderDetail> orderDetailList = service.getAllOrderDetail(order.getId());
        orderDetailTable.getItems().addAll(orderDetailList);
        
        deliveryAddTxt.setText(order.getDeliveryAdd()); 

        deliveryDatepicker.setValue(order.getReceiveDate());
        areaTxt.setText(order.getArea().getAreaCode() + "-" + order.getArea().getAreaName());
        orderStatusTxt.setText(order.getStatus().toString());

        shipperCodeTxt.setText(order.getUser().getCode());
        shipperNameTxt.setText(order.getUser().getFullName());
        shipperTelTxt.setText(order.getUser().getTel());
        shipperEmailTxt.setText(order.getUser().getEmail());
        
        orderSumPrice = 0;
        orderDetailList.forEach(e ->{
            orderSumPrice = orderSumPrice + e.getSumPrice();
        });
        
        sumPriceTxt.setText(String.valueOf(orderSumPrice));
    }
    
    //search khi có thông tin order đc chọn từ màn khác
    void searchFromOtherView(String orderCode) {
        Orders order = service.searchOrderByCode(orderCode);
        orderDetailTable.getItems().clear();
        List<OrderDetail> orderDetailList = service.getAllOrderDetail(order.getId());
        orderDetailTable.getItems().addAll(orderDetailList);
        
        deliveryAddTxt.setText(order.getDeliveryAdd()); 

        deliveryDatepicker.setValue(order.getReceiveDate());
        areaTxt.setText(order.getArea().getAreaCode() + "-" + order.getArea().getAreaName());
        orderStatusTxt.setText(order.getStatus().toString());

        shipperCodeTxt.setText(order.getUser().getCode());
        shipperNameTxt.setText(order.getUser().getFullName());
        shipperTelTxt.setText(order.getUser().getTel());
        shipperEmailTxt.setText(order.getUser().getEmail());
        
        orderSumPrice = 0;
        orderDetailList.forEach(e ->{
            orderSumPrice = orderSumPrice + e.getSumPrice();
        });
        
        sumPriceTxt.setText(String.valueOf(orderSumPrice));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderDetailName.setCellValueFactory(new PropertyValueFactory<>("name"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        sumPrice.setCellValueFactory(new PropertyValueFactory<>("sumPrice"));
    }    
    
}
