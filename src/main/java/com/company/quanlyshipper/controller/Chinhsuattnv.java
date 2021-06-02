package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
    
    @FXML
    private ComboBox typeCbb;
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
            user.setType(typeCbb.getValue().toString());
        
            saveHandler.accept(user);
            
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
        ObservableList<String> listCbb = FXCollections.observableArrayList("Ship lấy","Ship giao");
        typeCbb.getItems().clear();
        typeCbb.setItems(listCbb);
    }    
    
    public static void editUser(Users user , Consumer<Users> saveHandler){
        try{
            Stage stage = new Stage(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader(Chinhsuattnv.class.getClassLoader().getResource("view/chinhsuattnv.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Chinhsuattnv controller = loader.getController();
            controller.init(user , saveHandler);

            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addNew(Consumer<Users> saveHandler){
        editUser(null,saveHandler);
    }
    
    private void init(Users user , Consumer<Users> saveHandler) throws FileNotFoundException, IOException{
        this.user = user;
        this.saveHandler = saveHandler;
        if(user == null){
            titleTxt.setText("Thêm mới nhân viên");
            this.user = new Users();
            this.user.setRoleId(2);
            this.user.setType("Ship lấy");
            typeCbb.setValue(this.user.getType());
        }
        else {
            titleTxt.setText("Chỉnh sửa nhân viên");
            this.user = user;
            fullNameTxt.setText(user.getFullName());     
            codeTxt.setText(user.getCode()); 
            telTxt.setText(user.getTel()); 
            cmndTxt.setText(user.getCmnd()); 
            emailTxt.setText(user.getEmail()); 
            typeCbb.setValue(user.getType());
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
