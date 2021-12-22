package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TachHoKhauController {

    @FXML
    private Button btnChonChuHoMoi;

    @FXML
    private Button btnChuyenSang;

    @FXML
    private Button btnChuyenVe;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private TableView<?> tbvHoCanTach;

    @FXML
    private TableView<?> tbvNguoiHoMoi;

    @FXML
    private TableView<?> tbvNguoiSangHoMoi;

    @FXML
    private TextField tfChuHoHienTai;

    @FXML
    private TextField tfChuHoMoi;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfMaHoKhauMoi;

    @FXML
    private TextField tfMaKhuVuc;


    @FXML
    void chonChuHo(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/chon_chu_ho.fxml", "chon_chu_ho", null);
    }

    @FXML
    void chuyenSang(ActionEvent event) {

    }

    @FXML
    void chuyenVe(ActionEvent event) {

    }

    @FXML
    void huyTachHoKhau(ActionEvent event) {

    }

    @FXML
    void xacNhanTachHoKHau(ActionEvent event) {

    }
}

