package com.example.quanlyphuong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QuanLyNhanKhauApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    //  FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("login-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("controllers/nhan_khau/menu-nhan-khau.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1075, 678);
        stage.setTitle("Quản lý nhân khẩu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}