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
    @FXML
    private ComboBox errStatusCbb;
    @FXML
    private Label reason;

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
                order.setErrStatus(errStatusCbb.getValue().toString());

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
        if (orderNameTxt.getText().toString() == null || orderNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Tên đơn không được để trống")
                .build().show();
            orderNameTxt.requestFocus();
            return false;
        }
        if (priceTxt.getText().toString() == null || priceTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Đơn giá không được để trống")
                .build().show();
            priceTxt.requestFocus();
            return false;
        }
        if (Double.parseDouble(priceTxt.getText()) < 0){
            Thongbao.ThongbaoBuilder.builder()
                .message("Đơn giá không được nhỏ hơn 0")
                .build().show();
            priceTxt.requestFocus();
            return false;
        }
        if ((weightTxt.getText().toString() != null && !weightTxt.getText().toString().equals("")) && Double.parseDouble(weightTxt.getText()) < 0){
            Thongbao.ThongbaoBuilder.builder()
                .message("Cân nặng không được nhỏ hơn 0")
                .build().show();
            weightTxt.requestFocus();
            return false;
        }
        if ((deliveryDatepicker.getValue() != null && !deliveryDatepicker.getValue().toString().equals("")) && deliveryDatepicker.getValue().isBefore(createDatepicker.getValue())){
            Thongbao.ThongbaoBuilder.builder()
                .message("Ngày nhận không được phép nhỏ hơn ngày tạo đơn")
                .build().show();
            weightTxt.requestFocus();
            return false;
        }
        if (cusTelTxt.getText().toString() == null || cusTelTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Số điện thoại người nhận không được để trống")
                .build().show();
            cusTelTxt.requestFocus();
            return false;
        }      
        if (cusNameTxt.getText().toString() == null || cusNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Tên người nhận không được để trống")
                .build().show();
            cusNameTxt.requestFocus();
            return false;
        }   
        if (cusAddTxt.getText().toString() == null || cusAddTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Địa chỉ nhận không được để trống")
                .build().show();
            cusAddTxt.requestFocus();
            return false;
        }   
        
        return true;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listCbb = FXCollections.observableArrayList("Mới tạo","Đang giao","Thành công","Không thành công");
        orderStatusCbb.getItems().clear();
        orderStatusCbb.setItems(listCbb);
        ObservableList<String> listErrCbb = FXCollections.observableArrayList("ko liên lạc được vs khách","Khách ko nghe máy","Khách hẹn lại giao trong ngày","Khách đổi đc giao hàng");
        errStatusCbb.getItems().clear();
        errStatusCbb.setItems(listErrCbb);
        errStatusCbb.setVisible(false);
        errStatusCbb.setManaged(false);
        errStatusCbb.setValue("");
        reason.setVisible(false);
        reason.setManaged(false);
                
        orderStatusCbb.setOnAction(e -> {
            String value = orderStatusCbb.getSelectionModel().getSelectedItem().toString();
            if(value.equals("Không thành công")){
                errStatusCbb.setVisible(true);
                errStatusCbb.setManaged(true);
                reason.setVisible(true);
                reason.setManaged(true);
            }
            else{
                errStatusCbb.setVisible(false);
                errStatusCbb.setManaged(false);
                errStatusCbb.setValue("");
                reason.setVisible(false);
                reason.setManaged(false);
                errStatusCbb.setValue("");
            }
        });
        
        cusTelTxt.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    System.out.println("Textfield on focus");
                }
                else
                {
                    cusList.forEach(customer -> {
                            if ( customer.getCusTel().toString().equals(cusTelTxt.getText().toString()))
                            {
                                cus = customer;
                            }
                            else
                            {
//                                cus = null;
                                cusNameTxt.setText("");
                                cusAddTxt.setText("");
                                cusEmailTxt.setText("");
                            }
                        });
                        if (cus != null){
                            cusNameTxt.setText(cus.getCusName());
                            cusAddTxt.setText(cus.getCusAdd());
                            cusEmailTxt.setText(cus.getCusEmail());
                                    Thongbao.ThongbaoBuilder.builder()
                                        .title("Thông tin khách hàng đã có trong hệ thống")
                                        .message("Thông tin của khách hàng này sẽ tự động được điền vào đơn")
                                        .build().show();
                            cus = null;
                        }
//                        else
//                        {
//                            cus = new Customer();
//                            cus.setCusName(cusNameTxt.getText());
//                            cus.setCusEmail(cusEmailTxt.getText());
//                            cus.setCusTel(cusTelTxt.getText());
//                            cus.setCusAdd(cusAddTxt.getText());
//                        }
                }
            }
        });

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
    
    public static void editOrder(Orders order ,Consumer<Customer> saveCusHandler, Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Users>> userList, Supplier<List<Customer>> cusList){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuadonhang.class.getClassLoader().getResource("view/chinhsuadonhang.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuadonhang controller = loader.getController();
            controller.init(order , saveCusHandler,saveHandler,areaList,userList,cusList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Customer> saveCusHandler,Consumer<Orders> saveHandler,Supplier<List<Areas>> areaList,Supplier<List<Users>> userList,Supplier<List<Customer>> cusList  ){
        editOrder(null,saveCusHandler,saveHandler,areaList,userList,cusList);
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
            currentDate = LocalDate.now();
            createDatepicker.setValue(currentDate);
            userCbb.getItems().clear();
            shipperNameTxt.clear();
            shipperTelTxt.clear();
            shipperEmailTxt.clear();
            orderCodeLabel.setVisible(false);
            deliveryDatepicker.disableProperty().setValue(true);
            orderStatusCbb.disableProperty().setValue(true);
            

            for (Users u : this.shipperList){
                if(area != null){
                    if (u.getArea().getId() == area.getId()){
                        userCbb.getItems().add(u);
                    }
                }
                else{
                    if (u.getArea().getId() == areaCbb.getValue().getId()){
                        userCbb.getItems().add(u);
                    }
                }
            }
        }
        else {
            titleTxt.setText("Chỉnh sửa đơn hàng");
            this.order = order;
            //deliveryAddTxt.setText(order.getDeliveryAdd()); 
            
            cusTelTxt.setText(order.getCustomer().getCusTel());            
            cusNameTxt.setText(order.getCustomer().getCusName());
            cusAddTxt.setText(order.getCustomer().getCusAdd());
            cusEmailTxt.setText(order.getCustomer().getCusEmail());
            deliveryDatepicker.disableProperty().setValue(false);
            
            if(order.getStatus().equals("Không thành công")){
                errStatusCbb.setVisible(true);
                errStatusCbb.setManaged(true);
                
                errStatusCbb.setValue(order.getErrStatus());
                reason.setVisible(true);
                reason.setManaged(true);
            }
            else if(order.getStatus().equals("Thành công")){
                errStatusCbb.setVisible(false);
                errStatusCbb.setManaged(false);
                errStatusCbb.setValue("");
                reason.setVisible(false);
                reason.setManaged(false);
                orderStatusCbb.disableProperty().setValue(true);
            }
            else{
                errStatusCbb.setVisible(false);
                errStatusCbb.setManaged(false);
                errStatusCbb.setValue("");
                reason.setVisible(false);
                reason.setManaged(false);
                orderStatusCbb.disableProperty().setValue(true);
            }
            
            if(!order.getStatus().equals("Mới tạo")){
                ObservableList<String> listCbb = FXCollections.observableArrayList("Đang giao","Thành công","Không thành công");
                orderStatusCbb.getItems().clear();
                orderStatusCbb.setItems(listCbb);
            }
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
    
    
}
