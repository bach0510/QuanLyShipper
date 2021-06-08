package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.AreaService;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Chinhsuadonhang implements Initializable {

    
    @FXML
    private TextField titleTxt;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private Orders order;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField cusTelTxt;

    @FXML
    private TextField cusNameTxt;

    @FXML
    private TextField shipperNameTxt;

    @FXML
    private TextField shipperCodeTxt;

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
    private TextField deliveryAddTxt;

    
    @FXML
    private Consumer<Orders> saveHandler;

    private SimpleDateFormat dateFormat;
    
    private LocalDate currentDate;
    
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
//            ZoneId defaultZoneId = ZoneId.systemDefault();
//            currentDate = new Date().toInstant().atZone(defaultZoneId).toLocalDate();
            currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
//            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");            
//            SimpleDateFormat dateFormat2 = new SimpleDateFormat("ddMMyy");

            order.setCusName(cusNameTxt.getText());
            order.setCusTel(cusTelTxt.getText());
            order.setDeliveryAdd(deliveryAddTxt.getText());
            order.setDeliveryDate(deliveryDatepicker.getValue());
            order.setCreateDate(currentDate);
            order.setStatus(orderStatusCbb.getValue().toString());
            order.setArea(areaCbb.getValue());
            
            saveHandler.accept(order);
            
            String orderCode = "HN." 
                    + areaCbb.getValue().getAreaCode() + "."
                    + order.getId() +"."
                    + currentDate.format(formatter);
            order.setOrderCode(orderCode);
            saveHandler.accept(order);
            
            cancel();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("Mới tạo","Đang giao","Đã giao","Hoàn trả");
        orderStatusCbb.getItems().clear();
        orderStatusCbb.setItems(listCbb);
    }    
    
    public static void editOrder(Orders order , Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuadonhang.class.getClassLoader().getResource("view/chinhsuadonhang.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuadonhang controller = loader.getController();
            controller.init(order , saveHandler,areaList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList){
        editOrder(null,saveHandler,areaList);
    }
    
    @FXML
    void searchShipperInfo(InputMethodEvent event) {
        
        
    }
    
    private void init(Orders order , Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList) throws FileNotFoundException, IOException{
        this.order = order;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaList.get());
        this.saveHandler = saveHandler;
        
        if(order == null){
            titleTxt.setText("Thêm mới đơn hàng");
            this.order = new Orders();
            //set value cho combobox khu vuc
            Areas area = new Areas();
            area.setAreaName("Thanh Xuân");
            area.setAreaCode("TX");
            area.setId(1);
            areaCbb.setValue(area);
            //set value cho combobox trang thai
            orderStatusCbb.setValue("Mới tạo");
        }
        else {
            titleTxt.setText("Chỉnh sửa đơn hàng");
            this.order = order;
            cusNameTxt.setText(order.getCusName());     
            cusTelTxt.setText(order.getCusTel()); 
            deliveryAddTxt.setText(order.getDeliveryAdd()); 
            //((TextField)deliveryDatepicker.getEditor()).setText(order.getDeliveryDate()); 
            deliveryDatepicker.setValue(order.getDeliveryDate());
            areaCbb.setValue(order.getArea());
            orderStatusCbb.setValue(order.getStatus().toString());
        }
        
        
    }
    
    
}
