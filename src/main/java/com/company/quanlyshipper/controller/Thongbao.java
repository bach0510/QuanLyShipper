package com.company.quanlyshipper.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.company.quanlyshipper.QuanlyshipperApplication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author PC
 */
@Controller
public class Thongbao {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text titleTxt;
    @FXML 
    private Text messageTxt;     
    @FXML 
    private Button confirmBtn; 
    @FXML 
    private Button cancelBtn; 

    private Stage stage;
    @FXML
    public void show(){
        stage.show();
    }
    @FXML
    public void cancel(){
        cancelBtn.getScene().getWindow().hide();
    }
    public static class ThongbaoBuilder {
        private String title;
        
        private String message;        
        
        private Stage stage;

        private ActionListener okAction;
        
        private ThongbaoBuilder(){
            
        }
        
        public ThongbaoBuilder okAction(ActionListener ok){
            this.okAction = ok;
            return this;
        }
        
        public ThongbaoBuilder message(String mess){
            this.message = mess;
            return this;
        }
        
        public ThongbaoBuilder title(String titl){
            this.title = titl;
            return this;
        }
        
        public Thongbao build(){
            try {
                Stage stage = new Stage(StageStyle.UNDECORATED);
                FXMLLoader loader = new FXMLLoader(Login.class.getClassLoader().getResource("view/thongbao.fxml"));
                Parent view = loader.load();
                stage.setScene(new Scene(view));
                Thongbao controller = loader.getController();
                controller.stage = stage;
                
                controller.messageTxt.setText(this.message);                
                controller.titleTxt.setText(this.title);

                if (okAction != null){
                    controller.confirmBtn.setOnAction(e -> 
                    {
                        controller.cancel();
                        okAction.doAction();
                    });
                } else {
                    controller.confirmBtn.setVisible(false);
                    controller.cancelBtn.setText("Đóng");
                }
                return controller;
                
            } catch (Exception e){
                 e.printStackTrace();
                 throw new RuntimeException(e);
            }
        }
        
        public static ThongbaoBuilder builder(){
            return new ThongbaoBuilder();
        }
    }
    
    public static interface ActionListener {
        void doAction();
    } 
    
}
