package com.example.quanlyphuong.controllers;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {



    @FXML
    private Button btnDichTe;

    @FXML
    private Button btnNhanKhau;

    @FXML
    private Button txtRegister;

    @FXML
    void goToDichTeScreen(ActionEvent event) {
//        UIHelper.navigateNew("dich_te/menu-dich-te.fxml", "Quản lý dịch tễ", null,1000,630);
        UIHelper.navigateNew("dich_te/menu-dich-te.fxml", "Quản lý dịch tễ", null,1200, 800);

        btnDichTe.getScene().getWindow().hide();
    }

    @FXML
    void goToNhanKhauScreen(ActionEvent event) {
        UIHelper.navigateNew("nhan_khau/menu-nhan-khau.fxml", "Quản lý nhân khẩu", null,1200,800);
        btnNhanKhau.getScene().getWindow().hide();

    }


    public void goToRegister(ActionEvent event) {
        UIHelper.navigateNew("register-view.fxml", "Đăng ký tài khoản cho cán bộ",null,800,530);
        txtRegister.getScene().getWindow().hide();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Button getTxtRegister() {
        return txtRegister;
    }
}
