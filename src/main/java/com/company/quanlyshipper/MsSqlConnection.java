/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bach
 */
public class MsSqlConnection {
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=shippers;user=admin;password=admin";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionUrl);
//            System.out.println("Kết nối thành công!");
            return conn;
        } catch (Exception e) {
//            System.out.println("Kết nối không thành công!");
            e.printStackTrace();
            return null;
        }
    }
}
