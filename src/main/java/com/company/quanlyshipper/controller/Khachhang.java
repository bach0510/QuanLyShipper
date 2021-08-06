package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.AreasRepo;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.CustomerService;
import com.company.quanlyshipper.service.LoginService;
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
public class Khachhang implements Initializable {

    @Autowired
    private CustomerService service;
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private TableView<Customer> tableView;
    
    @FXML
    private Button addNewBtn;
    
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
    private TableColumn<Customer, Integer> id;

    @FXML
    private TableColumn<Customer, String> cusName;

    @FXML
    private TableColumn<Customer, String> cusCmnd;

    @FXML
    private TableColumn<Customer, String> cusTel;

    @FXML
    private TableColumn<Customer, String> cusEmail;

    @FXML
    private TableColumn<Customer, String> cusAdd;
    
    private Customer cus;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        cusTel.setCellValueFactory(new PropertyValueFactory<>("cusTel"));
        cusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        cusEmail.setCellValueFactory(new PropertyValueFactory<>("cusEmail"));
        cusCmnd.setCellValueFactory(new PropertyValueFactory<>("cusCmnd"));
        cusAdd.setCellValueFactory(new PropertyValueFactory<>("cusAdd"));
        search();
        
        tableView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Customer cus = tableView.getSelectionModel().getSelectedItem();
                this.cus = cus;
                if (cus != null){
                    Chinhsuattkh.editUser(cus, this::save,areaService::getAllArea,service::getAllCus);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Customer cus = tableView.getSelectionModel().getSelectedItem();
                this.cus = cus;
            }
        });
    }    
    
    @FXML
    void search(){
        tableView.getItems().clear();
        List<Customer> cusList = service.getAllCustomerInfo(
                fullNameTxt.getText().toString(), 
                cmndTxt.getText().toString(), 
                telTxt.getText().toString(), 
                emailTxt.getText().toString(),
                areaCbb.getValue());
        tableView.getItems().addAll(cusList);
        this.cus = null;
    }
    
    @FXML
    void reloadSearchInput(){
        fullNameTxt.setText("");  
        cmndTxt.setText("");
        codeTxt.setText("");
        emailTxt.setText("");
        telTxt.setText("");
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaService.getAllArea());
        search();
    }
    
    @FXML
    void addNewUser(){
        Chinhsuattkh.addNew(this::save,areaService::getAllArea,service::getAllCus);
    }
    
    private void save(Customer cus){
        service.save(cus);
        search();
    }
    @FXML
    private void deleteUser(){
        if (this.cus == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Không thể tìm thấy thông tin")
                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
                .build().show();
        } else {
           boolean isHaveOrder = false;
           for(Orders x : orderService.getAllOrder("", "")){
               if(x.getCustomer().getId() == this.cus.getId()){
                   isHaveOrder = true;
               }
           }
           if(isHaveOrder == true){
               Thongbao.ThongbaoBuilder.builder()
                .message("Bạn không thể xóa khách hàng đang có đơn hàng")
                .build().show(); 
           }
           if(isHaveOrder == false){
               Thongbao.ThongbaoBuilder.builder()
                .title("Thông tin của khách này sẽ bị xóa ra khỏi hệ thống")
                .message("Việc làm này sẽ không thể hoàn tác , Bạn có chắc chắn muốn xóa thông tin này không ?")
                .okAction(()-> {
                    service.deleteCus(this.cus);
                    search();
                }).build().show(); 
           }
        }
        
    }
    
}
