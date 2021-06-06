package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.OrderDetail;
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
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Chinhsuachitietdonhang implements Initializable {

    
    @FXML
    private TextField titleTxt;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private Button saveBtn;
    
    @FXML
    private OrderDetail orderDetail;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField nameTxt;

    @FXML
    private TextField qtyTxt;

    @FXML
    private TextField priceTxt;
    
    @FXML
    private TextField deliveryAddTxt;

    @FXML
    private Consumer<OrderDetail> saveHandler;

    private SimpleDateFormat dateFormat;
    
    private Date currentDate;
    
    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
            orderDetail.setName(nameTxt.getText());
            orderDetail.setQty(parseInt(qtyTxt.getText()));
            orderDetail.setPrice(parseDouble(priceTxt.getText()));
            orderDetail.setSumPrice(parseDouble(priceTxt.getText())*parseInt(qtyTxt.getText()));
            
            saveHandler.accept(orderDetail);
            
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
        
    }    
    
    public static void editOrderDetail(int orderId,OrderDetail orderDetail , Consumer<OrderDetail> saveHandler){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuachitietdonhang.class.getClassLoader().getResource("view/chinhsuachitietdonhang.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuachitietdonhang controller = loader.getController();
            controller.init(orderDetail , saveHandler,orderId);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(int orderId,Consumer<OrderDetail> saveHandler){
        editOrderDetail(orderId,null,saveHandler);
    }
    

    
    private void init(OrderDetail orderDetail , Consumer<OrderDetail> saveHandler,int orderId) throws FileNotFoundException, IOException{
        this.orderDetail = orderDetail;

        this.saveHandler = saveHandler;
        
        if(orderDetail == null){
            titleTxt.setText("Thêm mới đơn hàng");
            this.orderDetail = new OrderDetail();
            this.orderDetail.setOrderId(orderId);

        }
        else {
            titleTxt.setText("Chỉnh sửa đơn hàng");
            this.orderDetail = orderDetail;
            nameTxt.setText(this.orderDetail.getName());
            qtyTxt.setText(String.valueOf(this.orderDetail.getQty()));        
            priceTxt.setText(String.valueOf(this.orderDetail.getPrice()));

        }
        
        
    }
    
    
}
