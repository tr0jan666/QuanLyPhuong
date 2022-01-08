package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {
    NhanKhauBean nhanKhauBean;

    @FXML
    private Button btn_luu;

    @FXML
    private ComboBox<?> cb_gioiTinhNguoiMat;

    @FXML
    private DatePicker date_ngayMat;

    @FXML
    private DatePicker date_ngaySinhNguoiMat;

    @FXML
    private TextField tf_cmtNguoiKhai;

    @FXML
    private TextField tf_cmtNguoiMat;

    @FXML
    private TextField tf_diaChiNguoiMat;

    @FXML
    private TextField tf_hoTenNguoiKhai;

    @FXML
    private TextField tf_hoTenNguoiMat;

    @FXML
    private TextField tf_maGiayKhaiTu;

    @FXML
    void luuThongTin(ActionEvent event) {

    }

    ObservableList gioiTinhList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
        cb_gioiTinhNguoiMat.setItems(gioiTinhList);
        cb_gioiTinhNguoiMat.getSelectionModel().selectFirst();

        nhanKhauBean = NhanKhauController.chosenNhanKhauBean;
        NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
        tf_hoTenNguoiMat.setText(nhanKhauModel.getHo_ten());
        if(nhanKhauModel.getGioiTinh() == 0){
            cb_gioiTinhNguoiMat.getSelectionModel().select(1);
        }
        date_ngaySinhNguoiMat.setValue(convertToLocalDateViaMilisecond(nhanKhauModel.getNamSinh()));
        tf_cmtNguoiMat.setText(cmt.getSoCMT());
        tf_diaChiNguoiMat.setText(nhanKhauModel.getDiaChiHienNay());


    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
