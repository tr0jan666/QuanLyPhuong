package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.controllers.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuNhanKhauController implements Initializable {
    @FXML
    private BorderPane borderPane;

    public void setTrangChu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/Main.fxml"));
        Pane trangchuPane = (Pane) loader.load();
        borderPane.setCenter(trangchuPane);

    }
    public void setNhanKhau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/NhanKhau1.fxml"));
        Pane nhankhauPane = (Pane) loader.load();
        borderPane.setCenter(nhankhauPane);
    }

    public void setHoKhau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/HoKhau.fxml"));
        Pane hokhauPane = (Pane) loader.load();
        borderPane.setCenter(hokhauPane);

    }


    public void setThongKe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/views/ThongKe.fxml"));
        Pane thongkePane = (Pane) loader.load();
        borderPane.setCenter(thongkePane);

    }

    @FXML
    public void setQuayLai(ActionEvent event) {


    }





    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {


            Pane login = FXMLLoader.load(getClass().getResource("main-nhan-khau.fxml"));
            borderPane.setCenter(login);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}