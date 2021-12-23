package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.helper.MySQLConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MainNhanKhauController implements Initializable {
    @FXML
    private Label tongNhanKhauLb;

    @FXML
    private Label nhanKhauTamVangLb;

    @FXML
    private Label nhanKhauTamTruLb;

    @FXML
    private Label tongHoKhauLb;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongNhanKhauLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM ho_khau";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongHoKhauLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_tru WHERE denNgay < NOW()";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.nhanKhauTamTruLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tam_vang WHERE denNgay < NOW()";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.nhanKhauTamVangLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            connection.close();
        } catch (Exception e) {
        }

    }
}
