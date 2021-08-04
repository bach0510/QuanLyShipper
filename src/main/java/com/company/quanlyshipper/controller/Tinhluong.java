package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.SalaryDto;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.PayOffService;
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

    @Autowired
    private PayOffService payOffService;
    @FXML
    private TableView<SalaryDto> salaryTable;

    @FXML
    private TableColumn<SalaryDto, String> shipperName;

    @FXML
    private TableColumn<SalaryDto, String> orderCount;

    @FXML
    private TableColumn<SalaryDto, String> bonus;

    @FXML
    private TableColumn<SalaryDto, String> punish;

    @FXML
    private TableColumn<SalaryDto, String> salary;

    @FXML
    private ComboBox<Integer> monthCbb;

    @FXML
    private Button searchBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private ComboBox<Integer> yearCbb;
    
    private List<SalaryDto> salaryList;
    
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
        salaryTable.getItems().clear();
        //String statusValue = statusCbb.getValue().toString() == "Tất cả" ? "" : statusCbb.getValue().toString();
        salaryList = payOffService.getAllSalary(monthCbb.getValue(),yearCbb.getValue());
        salaryTable.getItems().addAll(salaryList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> monthList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        monthCbb.getItems().clear();
        monthCbb.setItems(monthList);
        monthCbb.setValue(1);
        ObservableList<Integer> yearList = FXCollections.observableArrayList(2021,2020);
        yearCbb.getItems().clear();
        yearCbb.setItems(yearList);
        yearCbb.setValue(2021);
        shipperName.setCellValueFactory(new PropertyValueFactory<>("fullName"));        
        orderCount.setCellValueFactory(new PropertyValueFactory<>("soDon"));
        bonus.setCellValueFactory(new PropertyValueFactory<>("tienThuong"));
        punish.setCellValueFactory(new PropertyValueFactory<>("tienPhat"));
        salary.setCellValueFactory(new PropertyValueFactory<>("tienLuong"));
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
