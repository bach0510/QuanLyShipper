/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.controller;

import com.company.quanlyshipper.QuanlyshipperApplication;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.utils.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

/**
 *
 * @author Admin
 */
public class Main implements Initializable {
    
    @FXML
    private StackPane ContentView;
    @FXML
    private VBox MenuBar;

    @FXML
    private ImageView MenuBtn;
    
    @FXML
    private Label UserName;
    
    private static int UserId;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // mac dinh hide menu
//        MenuBar.setTranslateX(-196);       
//        ContentView.setTranslateX(-196);

        //UserName.setText(User.getUserName());
        try{
            ContentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/home.fxml"));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            ContentView.getChildren().add(view);

        }catch (Exception e){
            e.printStackTrace();
        }
        showMenu();
    }
    
    @FXML
    void clickMenu(MouseEvent event){
        try{
            Node node = (Node) event.getSource();
            System.out.print(node.getId());
            Menu menu = Menu.valueOf(node.getId());
            ContentView.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(menu.getFxml()));
            loader.setControllerFactory(QuanlyshipperApplication.getApplicationContext()::getBean);
            Parent view = loader.load();
            ContentView.getChildren().add(view);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void showMenu(){
        // ham show menu bar
//        if (MenuBar.getTranslateX() == 0)
//        {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(javafx.util.Duration.seconds(0.3));
//            slide.setNode(MenuBar);
//
//            slide.setToX(-196);
//            slide.play();
//            MenuBar.setTranslateX(-196);
//            TranslateTransition slideContent = new TranslateTransition();
//            slideContent.setDuration(javafx.util.Duration.seconds(0.3));
//            slideContent.setNode(ContentView);
//
//            slideContent.setToX(-196);
//            slideContent.play();
//            ContentView.setTranslateX(-196);
//        }
//        else {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(javafx.util.Duration.seconds(0.3));
//            slide.setNode(MenuBar);
//
//            slide.setToX(0);
//            slide.play();
//            MenuBar.setTranslateX(0);
//            TranslateTransition slideContent = new TranslateTransition();
//            slideContent.setDuration(javafx.util.Duration.seconds(0.3));
//            slideContent.setNode(ContentView);
//
//            slideContent.setToX(0);
//            slideContent.play();
//            ContentView.setTranslateX(0);
//        }
            
    }
    
    @FXML
    void hideMenu(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(javafx.util.Duration.seconds(0.3));
        slide.setNode(MenuBar);

        slide.setToX(-199);
        slide.play();
        MenuBar.setTranslateX(-196);
    }
    
    @FXML
    void logOut(){
        MenuBtn.getScene().getWindow().hide();
        Login.loadView();
    }
    
    public static void loadView() {
        try {
            Stage stage = new Stage();
            Parent view = FXMLLoader.load(QuanlyshipperApplication.class.getClassLoader().getResource("view/Main.fxml"));
            stage.setScene(new Scene(view));
//            stage.initStyle(StageStyle.UTILITY);
            
            Image img = new Image("pictures/logo.png"); // set logo cho man hinh chinh
            stage.getIcons().add(img);
            
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
  
}
