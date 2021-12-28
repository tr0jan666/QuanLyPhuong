package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.NhanKhauModel;
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
    private TableView<NhanKhauModel> tableView;

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
        NhanKhauModel nhanKhauModel = tableView.getSelectionModel().getSelectedItem();
        if(nhanKhauModel != null) {
            UIHelper.navigateNew("nhan_khau/pop_up_thong_tin.fxml", "Thông tin chi tiết", null, 1000, 700);
        }
    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {

    }
}