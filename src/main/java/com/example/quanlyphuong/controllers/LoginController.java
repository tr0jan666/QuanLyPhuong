package com.example.quanlyphuong.controllers;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox cbAdmin;

    @FXML
    private TextField tftPassword;

    @FXML
    private TextField tftUser;

    @FXML
    private Text txtRegister;


    @FXML
    void onCancel(ActionEvent event) {

    }

    AuthService authService = new AuthService();

    @FXML
    void onLogin(ActionEvent event) {
        SimpleResult simpleResult = authService.login(tftUser.getText(), tftPassword.getText());
        if (simpleResult.isSuccess()) {
            System.out.println("chuyen trang o day nhung ko co trang nao de chuyen");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                //do stuff
//            }
        }
    }

    public void onRegisterClicked(MouseEvent mouseEvent) {
        UIHelper.navigateNew("register-view.fxml", "Register");
        txtRegister.getScene().getWindow().hide();
    }


}
