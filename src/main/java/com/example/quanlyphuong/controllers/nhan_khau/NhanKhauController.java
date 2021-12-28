package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NhanKhauController {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    @FXML
    private Button btn_ThemNhanKhau;

    @FXML
    private Button btn_Xoa;

    @FXML
    private Button btn_chiTiet;

    @FXML
    private Button btn_khaiTu;

    @FXML
    private Button btn_tamTru;

    @FXML
    private Button btn_tamVang;

    @FXML
    private Button btn_timKiem;

    @FXML
    private TableColumn<?, ?> col_cmt;

    @FXML
    private TableColumn<?, ?> col_gioiTinh;

    @FXML
    private TableColumn<?, ?> col_hoVaTen;

    @FXML
    private TableColumn<?, ?> col_tuoi;

    @FXML
    void khaiTuNhanKhau(ActionEvent event) {

    }

    @FXML
    void tamTruNhanKhau(ActionEvent event) {

    }

    @FXML
    void tamVangNhanKhau(ActionEvent event) {

    }

    @FXML
    void themNhanKhau(ActionEvent event) throws IOException {
        UIHelper.navigateNew("nhan_khau/pop_up_them_nhan_khau.fxml", "Thêm nhân khẩu", null,1000, 700);
//        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    @FXML
    void xemChiTietNhanKhau(ActionEvent event) {

    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {

    }


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