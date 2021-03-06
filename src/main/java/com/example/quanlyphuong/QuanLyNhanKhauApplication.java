package com.example.quanlyphuong;

import com.example.quanlyphuong.helper.constants.ScreenSizeConstant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class QuanLyNhanKhauApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), ScreenSizeConstant.LOGIN_WIDTH,ScreenSizeConstant.LOGIN_HEIGHT);
        stage.getIcons().add(new Image(QuanLyNhanKhauApplication.class.getResourceAsStream("app_logo.png")));
        stage.setTitle("Quản lý phường");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    

    }

    public static void main(String[] args) {launch();
    }
}
