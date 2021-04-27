package com.company.quanlyshipper;

import com.company.quanlyshipper.controller.Login;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * JavaFX App
 */
@Configuration
@SpringBootApplication
public class QuanlyshipperApplication extends Application {

//    private static Scene scene;
    private static ConfigurableApplicationContext applicationContext;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(QuanlyshipperApplication.class);
    }
    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Login.loadView(stage); 
    }
    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        launch();
    }
    
    

}