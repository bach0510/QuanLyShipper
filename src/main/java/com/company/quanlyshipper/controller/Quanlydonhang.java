package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
public class Quanlydonhang implements Initializable {

    @FXML
    private Button addOrderBtn;    
    
    @FXML
    private Button addOrderDetailBtn;  
    
    private Orders order;
    

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
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableColumn<Orders, String> orderCode;
    @FXML
    private TableColumn<Orders, String> orderName;
    @FXML
    private TableColumn<Orders, String> createDate;
    @FXML
    private TableColumn<Orders, String> status;
    @FXML
    private TableColumn<Orders, String> shipperCode;
    @FXML
    private TableColumn<Orders, String> cusName;
    @FXML
    private TableColumn<Orders, String> errStatus;
    
    @FXML
    private ComboBox statusCbb;
    
    @FXML
    private Button exportBtn;
    
    private List<Orders> orderList;
    /**
     * Initializes the controller class.
     */
    @FXML 
    void exportExcel() throws FileNotFoundException, IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("donhang");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < orderTable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(orderTable.getColumns().get(j).getText());
        }

        for (int i = 0; i < orderTable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < orderTable.getColumns().size(); j++) {
                if(orderTable.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(orderTable.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
        
        FileChooser fc = new  FileChooser();
        Stage stage = (Stage)exportBtn.getScene().getWindow();
        fc.setInitialFileName("DANH SACH DON HANG.xls");
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("Excel File", "*.xls"));
        File file = fc.showSaveDialog(stage);
        
        FileOutputStream fileOut = new FileOutputStream(file.getPath());
        
        workbook.write(fileOut);
        fileOut.close();
        Thongbao.ThongbaoBuilder.builder()
                .message("Đã Xuất thành công!")
                .build().show();
    }
    
    
     @FXML 
    void addOrder() {
        Chinhsuadonhang.addNew(this::saveCus,this::save,areaService::getAllArea,userService::getAllShipper,cusService::getAllCus);
    }
    
    private void save(Orders order){
        service.save(order);
        search();
    }
    private void saveCus(Customer cus){
        cusService.save(cus);
        search();
    }
    
    @FXML
    private void deleteOrder(){
        if (this.order == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Không thể tìm thấy thông tin")
                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Thông tin của đơn hàng này sẽ bị xóa ra khỏi hệ thống")
                .message("Việc làm này sẽ không thể hoàn tác , Bạn có chắc chắn muốn xóa thông tin này không ?")
                .okAction(()-> {
                    service.deleteOrder(this.order);
                    search();
                }).build().show(); 
        }
        
    }
    
    
    @FXML
    void search(){
        orderTable.getItems().clear();
        String statusValue = statusCbb.getValue().toString() == "Tất cả" ? "" : statusCbb.getValue().toString();
        orderList = service.getAllOrder(statusValue,orderCodeTxt.getText().toString() );
        orderTable.getItems().addAll(orderList);
        this.order = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("Tất cả","Mới tạo","Đang giao","Thành công","Không thành công");
        statusCbb.getItems().clear();
        statusCbb.setItems(listCbb);
        statusCbb.setValue("Tất cả");
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));        
        orderName.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        shipperCode.setCellValueFactory(new PropertyValueFactory<>("user"));
        cusName.setCellValueFactory(new PropertyValueFactory<>("customer"));
        errStatus.setCellValueFactory(new PropertyValueFactory<>("errStatus"));
        
        orderTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
                if (order != null){
                    Chinhsuadonhang.editOrder(order,this::saveCus, this::save,areaService::getAllArea,userService::getAllShipper,cusService::getAllCus);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
            }
        });
        search();
    }    
    
}
