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
import com.company.quanlyshipper.service.CustomerService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
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
    private CustomerService cusService;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private TextField orderCodeLabel;
    
    @FXML
    private Orders order;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField cusTelTxt;

    @FXML
    private TextField cusNameTxt;
    
    @FXML
    private TextField cusAddTxt;
    
    @FXML
    private TextField cusEmailTxt;

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
    private ComboBox<Users> userCbb;
    
    @FXML
    private TextField deliveryAddTxt;

    
    @FXML
    private Consumer<Orders> saveHandler;

    private SimpleDateFormat dateFormat;
    
    private LocalDate currentDate;
    
    private List<Users> shipperList;
    
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

            order.setDeliveryAdd(deliveryAddTxt.getText());
            order.setReceiveDate(deliveryDatepicker.getValue());
            order.setCreateDate(currentDate);
            order.setStatus(orderStatusCbb.getValue().toString());
            order.setArea(areaCbb.getValue());
            order.setUser(userCbb.getValue());
            
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
        
        areaCbb.setOnAction(e -> {
            Areas area = areaCbb.getSelectionModel().getSelectedItem();
            userCbb.getItems().clear();
            shipperNameTxt.clear();
            shipperTelTxt.clear();
            shipperEmailTxt.clear();
            for (Users u : this.shipperList){
                if (u.getArea().getId() == area.getId()){
                    userCbb.getItems().add(u);
                }
            }
        });
        
        userCbb.setOnAction(e -> {
            Users user = userCbb.getSelectionModel().getSelectedItem();
            shipperNameTxt.setText(user.getFullName());
            shipperTelTxt.setText(user.getTel());
            shipperEmailTxt.setText(user.getEmail());
        });
        
    }    
    
    public static void editOrder(Orders order , Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Users>> userList){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuadonhang.class.getClassLoader().getResource("view/chinhsuadonhang.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuadonhang controller = loader.getController();
            controller.init(order , saveHandler,areaList,userList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Users>> userList ){
        editOrder(null,saveHandler,areaList,userList);
    }
    
    private void init(Orders order , Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList, Supplier<List<Users>> userList) throws FileNotFoundException, IOException{
        this.order = order;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaList.get());
        userCbb.getItems().clear();
        userCbb.getItems().addAll(userList.get());
        this.shipperList = userList.get();
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
            
            userCbb.getItems().clear();
            shipperNameTxt.clear();
            shipperTelTxt.clear();
            shipperEmailTxt.clear();
            orderCodeLabel.setVisible(false);
            for (Users u : this.shipperList){
                if (u.getArea().getId() == area.getId()){
                    userCbb.getItems().add(u);
                }
            }
        }
        else {
            titleTxt.setText("Chỉnh sửa đơn hàng");
            this.order = order;
            deliveryAddTxt.setText(order.getDeliveryAdd()); 
            
            deliveryDatepicker.setValue(order.getReceiveDate());
            areaCbb.setValue(order.getArea());
            orderStatusCbb.setValue(order.getStatus().toString());
            
            userCbb.setValue(order.getUser());
            shipperNameTxt.setText(order.getUser().getFullName());
            shipperTelTxt.setText(order.getUser().getTel());
            shipperEmailTxt.setText(order.getUser().getEmail());
            
            orderCodeLabel.setText(order.getOrderCode());
        }
        
        
    }
    
    
}
