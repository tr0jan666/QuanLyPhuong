package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ThemMoiController {

    @FXML
    private Button btnChonChuHo;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private Button btnXoaThanhVien;

    @FXML
    private TableView<?> tbvThanhVienCuaHo;

    @FXML
    private TextField tfCMTChuHo;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfMaKhuVuc;

    @FXML
    private TextField tfNgaySinhChuHo;

    @FXML
    private TextField tfTenChuHo;
    @FXML
    void chonChuHo(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/chon_chu_ho.fxml", "chon_chu_ho", null);

    }

}
