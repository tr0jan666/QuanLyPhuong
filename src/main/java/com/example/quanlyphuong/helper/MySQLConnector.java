package com.example.quanlyphuong.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {
    private static String DB_URL = "jdbc:mysql://localhost:3306/quan_ly_nhan_khau_new";
    private static String USER_NAME = "root";
    private static String PASSWORD = "passwordQuan@116";

    public static Connection getConnection() {
        Connection conn = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
