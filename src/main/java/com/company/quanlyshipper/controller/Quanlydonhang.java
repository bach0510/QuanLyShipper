package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.company.quanlyshipper.controller.Chinhsuachitietdonhang.editOrderDetail;
import com.company.quanlyshipper.model.OrderDetail;
import com.company.quanlyshipper.model.Orders;
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
public class Quanlydonhang implements Initializable {

    @FXML
    private Button addOrderBtn;    
    
    @FXML
    private Button addOrderDetailBtn;  
    
    private Orders order;
    
    private OrderDetail orderDetail;

    @Autowired
    private OrderService service;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    
    @FXML
    private TableView<Orders> orderTable;
    @FXML
    private TableView<OrderDetail> orderDetailTable;
    @FXML
    private TableColumn<Orders, String> orderCode;
    @FXML
    private TableColumn<Orders, String> createDate;
    @FXML
    private TableColumn<Orders, String> status;
    
    @FXML
    private TableColumn<OrderDetail, String> orderDetailName;

    @FXML
    private TableColumn<OrderDetail, String> qty;

    @FXML
    private TableColumn<OrderDetail, String> price;

    @FXML
    private TableColumn<OrderDetail, String> sumPrice;
    @FXML
    private ComboBox statusCbb;
    /**
     * Initializes the controller class.
     */
    
    @FXML 
    void addOrderDetail() {
        if (this.order == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Kh??ng th??? t??m th???y th??ng tin")
                .message("Vui l??ng ch???n 1 ????n h??ng b??n tr??n ????? th??m m???t h??ng")
                .build().show();
        }
        else {
           Chinhsuachitietdonhang.addNew(this.order.getId(),this::saveOrderDetail);
        }
        
    }
    
    private void saveOrderDetail(OrderDetail orderDetail){
        service.saveOrderDetail(orderDetail);
        orderDetailTable.getItems().clear();
        List<OrderDetail> orderDetailList = service.getAllOrderDetail(this.order.getId());
        orderDetailTable.getItems().addAll(orderDetailList);
    }
    
     @FXML 
    void addOrder() {
        Chinhsuadonhang.addNew(this::save,areaService::getAllArea,userService::getAllShipper);
    }
    
    private void save(Orders order){
        service.save(order);
        search();
    }
    
    @FXML
    private void deleteOrder(){
        if (this.order == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Kh??ng th??? t??m th???y th??ng tin")
                .message("Vui l??ng ch???n 1 th??ng tin trong b???ng ????? th???c hi???n thao t??c")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Th??ng tin c???a ????n h??ng n??y s??? b??? x??a ra kh???i h??? th???ng")
                .message("Vi???c l??m n??y s??? kh??ng th??? ho??n t??c , B???n c?? ch???c ch???n mu???n x??a th??ng tin n??y kh??ng ?")
                .okAction(()-> {
                    service.deleteOrder(this.order);
                    search();
                }).build().show(); 
        }
        
    }
    
    @FXML
    private void deleteOrderDetail(){
        if (this.orderDetail == null){
            Thongbao.ThongbaoBuilder.builder()
                .title("Kh??ng th??? t??m th???y th??ng tin")
                .message("Vui l??ng ch???n 1 th??ng tin trong b???ng ????? th???c hi???n thao t??c")
                .build().show();
        } else {
           Thongbao.ThongbaoBuilder.builder()
                .title("Th??ng tin c???a m???t h??ng n??y s??? b??? x??a ra kh???i h??? th???ng")
                .message("Vi???c l??m n??y s??? kh??ng th??? ho??n t??c , B???n c?? ch???c ch???n mu???n x??a th??ng tin n??y kh??ng ?")
                .okAction(()-> {
                    service.deleteOrderDetail(this.orderDetail);
                    search();
                }).build().show(); 
        }
        
    }
    
    @FXML
    void search(){
        orderTable.getItems().clear();
        String statusValue = statusCbb.getValue().toString() == "T???t c???" ? "" : statusCbb.getValue().toString();
        List<Orders> orderList = service.getAllOrder(statusValue);
        orderTable.getItems().addAll(orderList);
        this.order = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("T???t c???","M???i t???o","??ang giao","???? giao","Ho??n tr???");
        statusCbb.getItems().clear();
        statusCbb.setItems(listCbb);
        statusCbb.setValue("T???t c???");
        orderCode.setCellValueFactory(new PropertyValueFactory<>("orderCode"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        orderDetailName.setCellValueFactory(new PropertyValueFactory<>("name"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        sumPrice.setCellValueFactory(new PropertyValueFactory<>("sumPrice"));
        
        orderTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
                if (order != null){
                    Chinhsuadonhang.editOrder(order, this::save,areaService::getAllArea,userService::getAllShipper);
                }
            }
            if (e.getClickCount() >= 1)
            {
                Orders order = orderTable.getSelectionModel().getSelectedItem();
                this.order = order;
                orderDetailTable.getItems().clear();
                List<OrderDetail> orderDetailList = service.getAllOrderDetail(this.order.getId());
                orderDetailTable.getItems().addAll(orderDetailList);
            }
        });
        orderDetailTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2)
            {
                OrderDetail orderDetail = orderDetailTable.getSelectionModel().getSelectedItem();
                this.orderDetail = orderDetail;
                if (orderDetail != null){
                   Chinhsuachitietdonhang.editOrderDetail(this.order.getId(),this.orderDetail,this::saveOrderDetail);
                }
            }
            if (e.getClickCount() >= 1)
            {
                OrderDetail orderDetail = orderDetailTable.getSelectionModel().getSelectedItem();
                this.orderDetail = orderDetail;
            }
        });
        search();
    }    
    
}
