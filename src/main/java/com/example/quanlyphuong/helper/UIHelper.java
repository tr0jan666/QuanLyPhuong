package com.example.quanlyphuong.helper;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UIHelper {
    public static double DEFAULT_SCREEN_WIDTH = 1200;
    public static double DEFAULT_SCREEN_HEIGHT = 800;

    public static void navigateNew(String fxmlPath, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource(fxmlPath));
        try {

            Scene scene = new Scene(fxmlLoader.load(), DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(false);
//        stage.set
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
