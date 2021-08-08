package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.LoginService;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.stage.FileChooser;
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
public class Quanlyttnv implements Initializable {

    @Autowired
    private UserService service;
    @Autowired
    private OrderService orderService;
    
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
        ObservableList<String> listCbb = FXCollections.observableArrayList("Tất cả","Ship lấy","Ship giao");
        typeCbb.getItems().clear();
        typeCbb.setItems(listCbb);
        typeCbb.setValue("Tất cả");
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
                    Chinhsuattnv.editUser(service::getAllShipper,user, this::save,areaService::getAllArea);
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
        String typeValue = typeCbb.getValue().toString() == "Tất cả" ?  "" : typeCbb.getValue().toString();
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
    void exportExcel() throws FileNotFoundException, IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("donhang");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableView.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableView.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableView.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableView.getColumns().size(); j++) {
                if(tableView.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileChooser fc = new  FileChooser();
        Stage stage = (Stage)tableView.getScene().getWindow();
        fc.setInitialFileName("DANH SACH NHAN VIEN.xls");
        fc.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel File", "*.xls"));
        File file = fc.showSaveDialog(stage);
        
        FileOutputStream fileOut = new FileOutputStream(file.getPath());
        
        workbook.write(fileOut);
        fileOut.close();
        Thongbao.ThongbaoBuilder.builder()
                .message("Đã Xuất thành công!")
                .build().show();

    }
    
    @FXML
    void reloadSearchInput(){
        fullNameTxt.setText("");  
        cmndTxt.setText("");
        codeTxt.setText("");
        emailTxt.setText("");
        telTxt.setText("");
        typeCbb.setValue("Tất cả");
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        search();
    }
    
    @FXML
    void addNewUser(){
        Chinhsuattnv.addNew(service::getAllShipper,this::save,areaService::getAllArea);
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
           boolean isHaveOrder = false;
           for(Orders x : orderService.getAllOrder("", "")){
               if(x.getUser()!= null){
                   if(x.getUser().getId() == this.user.getId()){
                        isHaveOrder = true;
                    }
               }
               
           }
           if(isHaveOrder == true){
               Thongbao.ThongbaoBuilder.builder()
                .message("Bạn không thể xóa nhân viên đang có đơn hàng")
                .build().show(); 
           }
           if(isHaveOrder == false){
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
    
}
