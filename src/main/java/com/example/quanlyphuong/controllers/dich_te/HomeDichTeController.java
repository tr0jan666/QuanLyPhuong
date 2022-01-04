package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.helper.MySQLConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomeDichTeController implements Initializable {
    @FXML
    private Label tongDangCachLyLb;

    @FXML
    private Label tongTestCovidLb;

    @FXML
    private Label tongTiemMui2Lb;

    @FXML
    private Label tongTiemMui1Lb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT COUNT(*) AS tong FROM cach_ly WHERE mucDoCachLy = 1";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongDangCachLyLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();


            query = "SELECT COUNT(*) AS tong FROM tiem_chung WHERE soLanTiem = 1" ;
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongTiemMui1Lb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM tiem_chung WHERE soLanTiem = 2";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongTiemMui2Lb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            query = "SELECT COUNT(*) AS tong FROM test WHERE ketQua = 1";
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                this.tongTestCovidLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();

            connection.close();
        } catch (Exception e) {


        }



    }
}
