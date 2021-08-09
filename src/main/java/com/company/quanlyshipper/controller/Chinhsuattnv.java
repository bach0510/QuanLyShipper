package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Areas;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Chinhsuattnv implements Initializable {

    @FXML
    private ImageView imageView;    
    
    private File file;
    private FileInputStream fs;    

    @Autowired
    private UserService service;
    
    @Autowired
    private AreaService areaService;
    
    @FXML
    private ComboBox typeCbb;
    
    @FXML
    private  ComboBox<Areas> areaCbb;
    
    @FXML
    private Button browse;
    
    @FXML
    private TextField titleTxt;

    @FXML
    private Button saveBtn;
    
    @FXML
    private Users user;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField fullNameTxt;
    
    @FXML
    private TextField registerNoTxt;

    @FXML
    private TextField cmndTxt;

    @FXML
    private TextField codeTxt;

    @FXML
    private TextField telTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button saveBtn1;    
    
    private static Stage stage;

    
    @FXML
    private Consumer<Users> saveHandler;

    private Supplier<List<Areas>> areaList;    
    private Supplier<List<Users>> userList;

    @FXML
    void cancel() {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    private void save() {
        try{
//            Users user = service.getShipperInfoByCode(code)
            user.setFullName(fullNameTxt.getText());            
            user.setCode(codeTxt.getText().toUpperCase());
            user.setTel(telTxt.getText());
            user.setCmnd(cmndTxt.getText());
            user.setEmail(emailTxt.getText());
            user.setType("");
            user.setArea(areaCbb.getValue());
            user.setRegisterNo(registerNoTxt.getText());
            user.setUserName(codeTxt.getText());
            user.setPassword("1");
            if (validate()== true){
                saveHandler.accept(user);
            
                cancel();
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private boolean validate(){
        if (codeTxt.getText().toString() == null || codeTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Mã nhân viên không được để trống")
                .build().show();
            codeTxt.requestFocus();
            return false;
        }
        for(Users x : userList.get()){
            
            if (user == null){
                if (codeTxt.getText().toString().toLowerCase().equals(x.getCode().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Mã nhân viên đã tồn tại")
                    .build().show();
                codeTxt.requestFocus();
                return false;
                }
                if (emailTxt.getText().toString().toLowerCase().equals(x.getEmail().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Email đã tồn tại")
                    .build().show();
                emailTxt.requestFocus();
                return false;
                }
            }
            else{
                if (user.getId() != x.getId() && codeTxt.getText().toString().toLowerCase().equals(x.getCode().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Mã nhân viên đã tồn tại")
                    .build().show();
                codeTxt.requestFocus();
                return false;
                }
                if (user.getId() != x.getId() && emailTxt.getText().toString().toLowerCase().equals(x.getEmail().toString().toLowerCase())){
                Thongbao.ThongbaoBuilder.builder()
                    .message("Email đã tồn tại")
                    .build().show();
                emailTxt.requestFocus();
                return false;
                }
            }
        }
        if (fullNameTxt.getText().toString() == null || fullNameTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Tên nhân viên không được để trống")
                .build().show();
            fullNameTxt.requestFocus();
            return false;
        }
        if (telTxt.getText().toString() == null || telTxt.getText().toString().equals("")){
            Thongbao.ThongbaoBuilder.builder()
                .message("Số điện thoại không được để trống")
                .build().show();
            telTxt.requestFocus();
            return false;
        }
        if ((emailTxt.getText().toString() != null && !emailTxt.getText().toString().equals("")) && !isValid(emailTxt.getText())){
            Thongbao.ThongbaoBuilder.builder()
                .message("Email không hợp lệ")
                .build().show();
            emailTxt.requestFocus();
            return false;
        }  
        return true;
    }
    static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public static void editUser(Supplier<List<Users>> userList,Users user , Consumer<Users> saveHandler,Supplier<List<Areas>> areaList){
        
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuattnv.class.getClassLoader().getResource("view/chinhsuattnv.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuattnv controller = loader.getController();
            controller.init(userList,user , saveHandler,areaList);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Supplier<List<Users>> userList,Consumer<Users> saveHandler,Supplier<List<Areas>> areaList){
        editUser(userList,null,saveHandler,areaList);
    }
    
    private void init(Supplier<List<Users>> userList,Users user , Consumer<Users> saveHandler,Supplier<List<Areas>> areaList) throws FileNotFoundException, IOException{
        this.user = user;
        this.userList = userList;
        this.saveHandler = saveHandler;
        areaCbb.getItems().clear();
        areaCbb.getItems().addAll(areaList.get());
        if(user == null){
            titleTxt.setText("Thêm mới nhân viên");
            this.user = new Users();
            this.user.setRoleId(2);
            this.user.setType("");
            Areas area = new Areas();
            area.setAreaName("Thanh Xuân");
            area.setAreaCode("TX");
            area.setId(1);
            areaCbb.setValue(area);
        }
        else {
            titleTxt.setText("Chỉnh sửa nhân viên");
            this.user = user;
            fullNameTxt.setText(user.getFullName());     
            codeTxt.setText(user.getCode()); 
            telTxt.setText(user.getTel()); 
            cmndTxt.setText(user.getCmnd());  
            emailTxt.setText(user.getEmail()); 
            registerNoTxt.setText(user.getRegisterNo());
            areaCbb.setValue(user.getArea());
            if(user.getImage() != null){
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                os.write(user.getImage());
                os.close();
                Image image = new Image("file:photo.jpg",151,142,true,true);
                imageView.setImage(image);
                imageView.setFitWidth(151);
                imageView.setFitHeight(142);

                imageView.setPreserveRatio(true);
            }
        }
        
        
    }
    @FXML
    void choosePicture(ActionEvent event) {
        Stage stage = (Stage)codeTxt.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("chọn ảnh");
        this.file = fc.showOpenDialog(stage);
        if(this.file != null){
            Image image = new Image(this.file.toURI().toString(),151,142,true,true);
            imageView.setImage(image);
            imageView.setFitWidth(151);
            imageView.setFitHeight(142);
            
            imageView.setPreserveRatio(true);
            try {
                byte[]img = Files.readAllBytes(file.toPath());
                
                this.user.setImage(img);
            } catch (Exception e){
                
            }
        }
    }
    
    
}
