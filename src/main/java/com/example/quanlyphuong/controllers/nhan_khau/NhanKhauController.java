package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class NhanKhauController {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    @FXML
    Button btn_ThemNhanKhau;

    public void chooseOption(ActionEvent event){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("");

        CheckBox box1 = new CheckBox("Thêm thường trú");
        CheckBox box2 = new CheckBox("Thêm tạm trú");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        dialog.getDialogPane().getChildren().addAll(box1,box2);
        dialog.setResultConverter(dialogButton ->{
            if(dialogButton == ButtonType.OK){
                if(box1.isSelected()){
                    changeSceneThuongTru(event);
                }else if(box2.isSelected()){
                    changeSceneTamTru(event);
                }else return null;
            }
            return null;
        });
    }

    public void changeSceneTamTru(ActionEvent event) {
        UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_tam_tru.fxml", "Đăng ký tạm trú", null);
        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    public void changeSceneThuongTru(ActionEvent event) {
        UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_thuong_tru.fxml", "Đăng ký thường trú", null);
        btn_ThemNhanKhau.getScene().getWindow().hide();
    }
}