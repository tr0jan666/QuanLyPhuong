package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.UserMoldel;

import java.sql.*;
import java.util.Objects;

public class AuthService {
    private static String USER_ADMIN = "admin";
    private static String PWD_ADMIN = "admin";
    private static int ID_ADMIN = 0;
    private static final AuthService INSTANCE = new AuthService();
    private UserMoldel currentUser; // currentUser be always set value after login

    private AuthService() {

    }

    public static AuthService getInstance() {
        return INSTANCE;
    }

    public SimpleResult login(String userName, String password, boolean isAdmin) {
        //check administrator
        if (isAdmin) {
            if (userName.equals(USER_ADMIN) && password.equals(PWD_ADMIN)) {
                currentUser = new UserMoldel(ID_ADMIN, USER_ADMIN, PWD_ADMIN);
                currentUser.setAdministrator(true);
                return new SimpleResult(true, SimpleResult.DEFAULT_SUCCESS_MESSAGE);
            } else {
                return new SimpleResult(false, "Tài khoản administrator không chính xác!");
            }
        }
        String selectUserScript = "select * from users where userName = ?";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement selectUserSM = connection.prepareStatement(selectUserScript);
            selectUserSM.setString(1, userName);
            ResultSet selectUserRS = selectUserSM.executeQuery();
            if (selectUserRS.next()) {
                //db co user
                int id = selectUserRS.getInt(1);
                String dbPassword = selectUserRS.getString(2);

                if (Objects.equals(dbPassword, password)) {
                    this.currentUser = new UserMoldel(id, userName, password);
                    return new SimpleResult(true, SimpleResult.DEFAULT_SUCCESS_MESSAGE);
                } else return new SimpleResult(false, "Sai mật khẩu");
            } else {
                //db ko co user
                return new SimpleResult(false, "Tài khoản không tồn tại trên hệ thống");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
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
                int countRowUpdated = insertUserStatement.executeUpdate();
                if (countRowUpdated > 0)
                    return new SimpleResult(true, "Dang ky thanh cong!");
                else
                    return new SimpleResult(false, "Dang ky that bai! Vui long thu lai sau");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
    }

    public UserMoldel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserMoldel currentUser) {
        this.currentUser = currentUser;
    }
}
