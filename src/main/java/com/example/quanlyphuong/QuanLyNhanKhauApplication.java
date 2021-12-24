package com.example.quanlyphuong;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class QuanLyNhanKhauApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("main-nhan-khau.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Quản lý nhân khẩu");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_thuong_tru.fxml","Test",null);
    }

    public static void main(String[] args) {
        launch();
    }
}
