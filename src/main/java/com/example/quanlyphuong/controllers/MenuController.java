package com.example.quanlyphuong.controllers;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {



    @FXML
    private Button btnDichTe;

    @FXML
    private Button btnNhanKhau;

    @FXML
    private Text txtRegister;

    @FXML
    void goToDichTeScreen(ActionEvent event) {
        UIHelper.navigateNew("dich_te/menu-dich-te.fxml", "Quản lý dịch tễ", null);
        btnDichTe.getScene().getWindow().hide();
    }

    @FXML
    void goToNhanKhauScreen(ActionEvent event) {
        UIHelper.navigateNew("nhan_khau/main-nhan-khau.fxml", "Quản lý nhân khẩu", null);
        btnNhanKhau.getScene().getWindow().hide();
    }


    public void goToRegister(MouseEvent mouseEvent) {
        UIHelper.navigateNew("register-view.fxml", "Đăng ký tài khoản cho cán bộ", null);
        txtRegister.getScene().getWindow().hide();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Text getTxtRegister() {
        return txtRegister;
    }
}
