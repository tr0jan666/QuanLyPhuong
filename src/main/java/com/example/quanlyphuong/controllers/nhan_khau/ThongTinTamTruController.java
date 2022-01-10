package com.example.quanlyphuong.controllers.nhan_khau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongTinTamTruController implements Initializable {

    @FXML
    private Button btn_save;

    @FXML
    private ComboBox<?> cb_gioiTinh;

    @FXML
    private DatePicker date_denNgay;

    @FXML
    private DatePicker date_ngaySinh;

    @FXML
    private DatePicker date_tuNgay;

    @FXML
    private TextField tf_cmt;

    @FXML
    private TextArea tf_lyDo;

    @FXML
    private TextField tf_maGiayTamTru;

    @FXML
    private TextField tf_maNhanKhau;

    @FXML
    private TextField tf_noiThuongTru;

    @FXML
    private TextField tf_sdt;

    @FXML
    private TextField tf_ten;

    @FXML
    void luuThongTin(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

