package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.service.OrderService;
import com.company.quanlyshipper.service.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class Quanlydonhang implements Initializable {

    @FXML
    private Button addOrderBtn;    
    
    private Orders order;

    @Autowired
    private OrderService service;
    @Autowired
    private UserService userService;
    
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableColumn<Orders, String> orderCode;
    @FXML
    private TableColumn<Orders, String> createDate;
    @FXML
    private TableColumn<Orders, String> status;
    /**
     * Initializes the controller class.
     */
    
     @FXML 
    void addOrder() {
        Chinhsuadonhang.addNew(this::save);
    }
    private void save(Orders order){
        service.save(order);
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
        List<Orders> orderList = service.getAllOrder();
        orderTable.getItems().addAll(orderList);
        this.order = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        orderTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Orders user = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
                if (user != null){
                    Chinhsuadonhang.editOrder(user, this::save);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
            }
        });
    }    
    
}
