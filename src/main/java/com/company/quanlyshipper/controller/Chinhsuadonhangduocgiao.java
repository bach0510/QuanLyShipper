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
import com.company.quanlyshipper.repo.CusRepo;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class Chinhsuadonhangduocgiao implements Initializable {

    
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
    private TextField orderNameTxt;
    
    @FXML
    private TextField weightTxt;
    
    @FXML
    private TextField priceTxt;
    
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
    private DatePicker createDatepicker;

    @FXML
    private ComboBox<Areas> areaCbb;
    
    @FXML
    private ComboBox<Users> userCbb;
    
    @FXML
    private TextField deliveryAddTxt;

    
    @FXML
    private Consumer<Orders> saveHandler;
    @FXML
    private Consumer<Customer> saveCusHandler;

    private SimpleDateFormat dateFormat;
    
    private LocalDate currentDate;
    
    private List<Users> shipperList;
    
    private List<Customer> cusList;
    private Customer cus;
    
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    void getCusInfo(String tel) {
        
//        Customer cus = new Customer();
//        cus = cusService.getCusByTel(cusTelTxt.getText().toString());
//        Thongbao.ThongbaoBuilder.builder()
//                .title("Không thể tìm thấy thông tin")
//                .message("Vui lòng chọn 1 thông tin trong bảng để thực hiện thao tác")
//                .build().show();
//        cusTelTxt.setText(cus.getCusTel());            
//        cusNameTxt.setText(cus.getCusName());
//        cusAddTxt.setText(cus.getCusAdd());
//        cusEmailTxt.setText(cus.getCusEmail());
    }
    
    @FXML
    private void save() {
        try{
            if(validate() == true){

                currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

                String address = cusAddTxt.getText() != null ? cusAddTxt.getText() : "";
                order.setDeliveryAdd(address);
                order.setOrderName(orderNameTxt.getText());
                order.setReceiveDate(deliveryDatepicker.getValue());
                order.setCreateDate(currentDate);
                order.setPrice(Double.parseDouble(priceTxt.getText()));
                order.setWeight(Double.parseDouble(weightTxt.getText()));
                order.setStatus(orderStatusCbb.getValue().toString());

                order.setArea(areaCbb.getValue());
                order.setUser(userCbb.getValue());

                if (userCbb.getValue() !=  null){
                    order.setStatus("Đang giao");
                }
                if (deliveryDatepicker.getValue() != null){
                    order.setStatus("Thành công");
                }
                cusList.forEach(customer -> {
                    if ( customer.getCusTel().toString().equals(cusTelTxt.getText().toString()))
                    {
                        cus = customer;
                    }
                });
                if (cus != null){
                    order.setCustomer(cus);
                }
                else {
                    cus = new Customer();
                    cus.setCusName(cusNameTxt.getText());
                    cus.setCusTel(cusTelTxt.getText());
                    cus.setCusAdd(cusAddTxt.getText());
                    cus.setCusEmail(cusEmailTxt.getText());
                    saveCusHandler.accept(cus);

                    order.setCustomer(cus);
                }
                saveHandler.accept(order);
                cancel();
                    String orderCode = "HN." 
                        + areaCbb.getValue().getAreaCode() + "."
                        + order.getId() +"."
                        + currentDate.format(formatter);
                order.setOrderCode(orderCode);
                saveHandler.accept(order);
            
            }
            
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private boolean validate(){
        
        if (deliveryAddTxt.getText().toString() == null || deliveryAddTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Địa chỉ giao không được để trống")
                .build().show();
            deliveryAddTxt.requestFocus();
            return false;
        }   
        
        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("Thành công","Không thành công");
        orderStatusCbb.getItems().clear();
        orderStatusCbb.setItems(listCbb);
        
    }    
    
    public static void editOrder(Orders order ,Consumer<Customer> saveCusHandler, Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Users>> userList, Supplier<List<Customer>> cusList){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuadonhangduocgiao.class.getClassLoader().getResource("view/chinhsuadonhangduocgiao.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuadonhangduocgiao controller = loader.getController();
            controller.init(order , saveCusHandler,saveHandler,areaList,userList,cusList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void init(Orders order ,Consumer<Customer> saveCusHandler, Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList, Supplier<List<Users>> userList,Supplier<List<Customer>> cusList) throws FileNotFoundException, IOException{
        this.order = order;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaList.get());
        userCbb.getItems().clear();
        userCbb.getItems().addAll(userList.get());
        this.shipperList = userList.get();
        this.cusList = cusList.get();
        this.saveHandler = saveHandler;
        this.saveCusHandler = saveCusHandler;
        
        
            titleTxt.setText("Thông tin đơn hàng");
            this.order = order;
            //deliveryAddTxt.setText(order.getDeliveryAdd()); 
            
            cusTelTxt.setText(order.getCustomer().getCusTel());            
            cusNameTxt.setText(order.getCustomer().getCusName());
            cusAddTxt.setText(order.getCustomer().getCusAdd());
            cusEmailTxt.setText(order.getCustomer().getCusEmail());
            deliveryDatepicker.disableProperty().setValue(false);            
            deliveryAddTxt.setText(order.getDeliveryAdd());

            
            deliveryDatepicker.setValue(order.getReceiveDate());
            createDatepicker.setValue(order.getCreateDate());
            areaCbb.setValue(order.getArea());
            orderStatusCbb.setValue(order.getStatus().toString());
            orderNameTxt.setText(order.getOrderName().toString());
            priceTxt.setText(String.valueOf(order.getPrice()));
            weightTxt.setText(String.valueOf(order.getWeight()));
            if (order.getUser() != null){
                userCbb.setValue(order.getUser());
                shipperNameTxt.setText(order.getUser().getFullName());
                shipperTelTxt.setText(order.getUser().getTel());
                shipperEmailTxt.setText(order.getUser().getEmail());
            }
            if (areaCbb.getValue()==null){
                Areas area = new Areas();
                area.setAreaName("Thanh Xuân");
                area.setAreaCode("TX");
                area.setId(1);
                areaCbb.setValue(area);
            }
            
            orderCodeLabel.setText(order.getOrderCode());
        
        
        
    }
    
    
}
