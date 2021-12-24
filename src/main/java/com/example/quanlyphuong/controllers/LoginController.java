package com.example.quanlyphuong.controllers;

import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.AppScreen;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
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

    AuthService authService = AuthService.getInstance();

    @FXML
    void onLogin(ActionEvent event) {
        SimpleResult simpleResult = authService.login(tftUser.getText(), tftPassword.getText(), cbAdmin.isSelected());
        if (simpleResult.isSuccess()) {
            AppScreen menuChucNangScreen = UIHelper.navigateNew("menu-chuc-nang.fxml", "Chọn chức năng", null,600,400);
            menuChucNangScreen.<MenuController>getController().getTxtRegister().setVisible(AuthService.getInstance().getCurrentUser().isAdministrator());
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }
    }


}
