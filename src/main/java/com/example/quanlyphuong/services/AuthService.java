package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.UserMoldel;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    public AuthService() {

    }

    public SimpleResult login(String userName, String password) {
        String selectUserScript = "select * from users where userName = %s".formatted(userName);
        try (Connection connection = MySQLConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUserScript);

            //1. check db khong co user
            //
            // 2. check db, thay co user nhung sai pass
            //3. cr
        } catch (SQLException ex) {

        }

        return null;
    }

    public SimpleResult register(String userName, String password) {
        String selectUserScript = "select * from users where userName = ?";
        String insertUserScript = "insert into users (userName, passwd) values (?, ?)";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement selectUserStatement = connection.prepareStatement(selectUserScript);
            // check userName ton tai
            selectUserStatement.setString(1, userName);
            ResultSet selectUserRS = selectUserStatement.executeQuery();
            if (selectUserRS.next()) {
                return new SimpleResult(false, "User name đã tồn tại! Vui lòng nhập user name khác!");
            } else {
                PreparedStatement insertUserStatement = connection.prepareStatement(insertUserScript);
                insertUserStatement.setString(1, userName);
                insertUserStatement.setString(2, password);
                boolean resultInsert = insertUserStatement.execute();
                if (resultInsert)
                    return new SimpleResult(true, "Dang ky thanh cong!");
                else
                    return new SimpleResult(false, "Dang ky that bai! Vui long thu lai sau");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
    }
}
