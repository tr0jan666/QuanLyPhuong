package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.controllers.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.quanlyphuong.controllers.MenuController;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.AppScreen;
import com.example.quanlyphuong.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuNhanKhauController implements Initializable {
    @FXML
    private BorderPane borderPane;
 @FXML
    public void setTrangChu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/main-nhan-khau.fxml"));
        Pane trangchuPane = (Pane) loader.load();
        borderPane.setCenter(trangchuPane);

    }
    @FXML
    public void setNhanKhau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("nhan_khau/NhanKhau.fxml"));
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
        AppScreen menuChucNangScreen = UIHelper.navigateNew("menu-chuc-nang.fxml", "Chọn chức năng", null,600,400);
        menuChucNangScreen.<MenuController>getController().getTxtRegister().setVisible(AuthService.getInstance().getCurrentUser().isAdministrator());
        ((Node) event.getSource()).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {

            Pane mainNhanKhau = FXMLLoader.load(QuanLyNhanKhauApplication.class.getResource("nhan_khau/main-nhan-khau.fxml"));
            borderPane.setCenter(mainNhanKhau);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
