package com.example.quanlyphuong.helper;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.models.AppScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UIHelper {
    public static double DEFAULT_SCREEN_WIDTH = 1200;
    public static double DEFAULT_SCREEN_HEIGHT = 800;

    public static AppScreen navigateNew(String fxmlPath, String title, Object userData) {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource(fxmlPath));
        System.out.println((QuanLyNhanKhauApplication.class.getResource(fxmlPath)));
        try {
            Scene scene = new Scene(fxmlLoader.load(), DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setUserData(userData);
            stage.setScene(scene);
            stage.show();
            return new AppScreen(fxmlPath, scene, fxmlLoader.getController());
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }

    }
}
