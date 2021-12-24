package com.example.quanlyphuong.controllers;

import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.AppScreen;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private Button btnRegister;

    @FXML
    private TextField tfPwd;

    @FXML
    private TextField tfRePwd;

    @FXML
    private TextField tfUserName;

    @FXML
    void onRegister(ActionEvent event) {
        AuthService authService = new AuthService();
        Alert errAlert = new Alert(Alert.AlertType.ERROR);
        errAlert.setResult(ButtonType.OK);
        SimpleResult validateResult = validateRegisterForm();
        if(validateResult.isSuccess())
        {
            SimpleResult registerResult = authService.register(tfUserName.getText(), tfPwd.getText());
            if(registerResult.isSuccess()) {
                // demo code
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, registerResult.getMessage(), ButtonType.OK);
                successAlert.show();
                // xử lý chuyển màn hình ở đây

            }else {
                errAlert.setContentText(registerResult.getMessage());
                errAlert.show();
            }
        } else {
            errAlert.setContentText(validateResult.getMessage());
            errAlert.show();
        }

    }
    @FXML
    void setQuayLai(ActionEvent event) {
        AppScreen menuChucNangScreen = UIHelper.navigateNew("menu-chuc-nang.fxml", "Chọn chức năng", null,600,400);
        assert menuChucNangScreen != null ;
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    SimpleResult validateRegisterForm() {
        if(tfUserName.getText().length() < 5) return new SimpleResult(false, "Tài khoản có độ dài > 5 kí tự");
        // viết thêm code validate vào đây
        // đây chỉ là phương thức demo


        return new SimpleResult(true, SimpleResult.DEFAULT_SUCCESS_MESSAGE);
    }

}
