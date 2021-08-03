package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.UserService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Tinhluong implements Initializable {

        @FXML
    private TableView<?> salaryTable;

    @FXML
    private TableColumn<?, ?> shipperName;

    @FXML
    private TableColumn<?, ?> orderCount;

    @FXML
    private TableColumn<?, ?> bonus;

    @FXML
    private TableColumn<?, ?> punish;

    @FXML
    private TableColumn<?, ?> salary;

    @FXML
    private ComboBox<?> monthCbb;

    @FXML
    private Button searchBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private ComboBox<?> yearCbb;
    

    @Autowired
    private OrderService service;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private CustomerService cusService;
    @FXML
    private TextField orderCodeTxt;

    /**
     * Initializes the controller class.
     */
    @FXML 
    void exportExcel() throws FileNotFoundException, IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("donhang");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < salaryTable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(salaryTable.getColumns().get(j).getText());
        }

        for (int i = 0; i < salaryTable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < salaryTable.getColumns().size(); j++) {
                if(salaryTable.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(salaryTable.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileOutputStream fileOut = new FileOutputStream("Luong thang "+ monthCbb.getValue().toString() + "năm " + yearCbb.getValue().toString() + ".xls");
        workbook.write(fileOut);
        fileOut.close();

    }
    
    
    @FXML
    void search(){
//        orderTable.getItems().clear();
//        String statusValue = statusCbb.getValue().toString() == "Tất cả" ? "" : statusCbb.getValue().toString();
//        orderList = service.getAllOrder(statusValue,orderCodeTxt.getText().toString() );
//        orderTable.getItems().addAll(orderList);
//        this.order = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ObservableList<String> listCbb = FXCollections.observableArrayList("Tất cả","Mới tạo","Đang giao","Thành công","Không thành công");
//        statusCbb.getItems().clear();
//        statusCbb.setItems(listCbb);
//        statusCbb.setValue("Tất cả");
//        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));        
//        orderName.setCellValueFactory(new PropertyValueFactory<>("orderName"));
//        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
//        status.setCellValueFactory(new PropertyValueFactory<>("status"));
//        shipperCode.setCellValueFactory(new PropertyValueFactory<>("user"));
//        cusName.setCellValueFactory(new PropertyValueFactory<>("customer"));
        
//        orderTable.setOnMouseClicked(e -> {
//            if (e.getClickCount() == 2)
//            {
//                Orders order = orderTable.getSelectionModel().getSelectedItem();
//                this.order = order;
//                if (order != null){
//                    Chinhsuadonhang.editOrder(order, this::save,areaService::getAllArea,userService::getAllShipper,cusService::getAllCus);
//                }
//            }
//            if (e.getClickCount() >= 1)
//            {
//                Orders order = orderTable.getSelectionModel().getSelectedItem();
//                this.order = order;
//            }
//        });
        search();
    }    
    
}