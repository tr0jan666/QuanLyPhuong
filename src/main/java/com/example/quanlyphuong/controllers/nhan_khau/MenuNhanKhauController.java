package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.controllers.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.quanlyphuong.controllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuNhanKhauController implements Initializable {
    @FXML
    private BorderPane borderPane;
 @FXML
    public void setTrangChu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/main-dich-te.fxml"));
        Pane trangchuPane = (Pane) loader.load();
        borderPane.setCenter(trangchuPane);

    }
    @FXML
    public void setNhanKhau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/NhanKhau1.fxml"));
        Pane nhankhauPane = (Pane) loader.load();
        borderPane.setCenter(nhankhauPane);
    }

    public void setHoKhau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/hokhau.fxml"));
        Pane hokhauPane = (Pane) loader.load();
        borderPane.setCenter(hokhauPane);

    }


    public void setThongKe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/thongKe.fxml"));
        Pane thongkePane = (Pane) loader.load();
        borderPane.setCenter(thongkePane);

    }

    @FXML
    public void setQuayLai(ActionEvent event) {


    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {

            Pane login = FXMLLoader.load(QuanLyNhanKhauApplication.class.getResource("nhan_khau/main-dich-te.fxml"));
            borderPane.setCenter(login);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}