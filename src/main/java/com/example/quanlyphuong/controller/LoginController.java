package com.example.quanlyphuong.controller;

import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    void onCancel(ActionEvent event) {

    }
    AuthService authService = new AuthService();

    @FXML
    void onLogin(ActionEvent event) {
        SimpleResult simpleResult = authService.login(tftUser.getText(), tftPassword.getText());
        if(simpleResult.isResult()) {
            System.out.println("chuyen trang o day nhung ko co trang nao de chuyen");
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                //do stuff
//            }
        }
    }

}
