package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.OrderDetail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> areaCbb;

    @FXML
    private TextField shipperCodeTxt;

    @FXML
    private TextField deliveryAddTxt;

    @FXML
    void search(MouseEvent event) {

    }

    @FXML
    void searchShipperInfo(InputMethodEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
