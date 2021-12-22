package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ThemMoiController {

    @FXML
    private TextField tfMaKhuVuc;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfTenChuHo;

    @FXML
    private TextField tfNgaySinhChuHo;

    @FXML
    private TextField tfCMTChuHo;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private Button btnXoaThanhVien;

    @FXML
    private TableView<?> tbvThanhVienCuaHo;

    @FXML
    private TableColumn<?, ?> hoTenThanhVien;

    @FXML
    private TableColumn<?, ?> ngaySinhThanhVien;

    @FXML
    private TableColumn<?, ?> quanHeVoiChuHo;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnChonChuHo;


    @FXML
    void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    void luu(ActionEvent event) {

    }

    @FXML
    void themThanhVien(ActionEvent event) {

    }

    @FXML
    void xoaThanhVienDaChon(ActionEvent event) {

    }


    @FXML
    void chonChuHo(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/chon_chu_ho.fxml", "chon_chu_ho", null);

    }

}
