package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TamVangModel;
import com.example.quanlyphuong.services.AuthService;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class TamVangController implements Initializable {
    public NhanKhauBean nhanKhauBean;

    @FXML
    private Button btn_luu;

    @FXML
    private DatePicker date_Den;

    @FXML
    private DatePicker date_Tu;

    @FXML
    private DatePicker date_ngaySinh;

    @FXML
    private TextField tf_NoiDen;

    @FXML
    private TextField tf_cmd;

    @FXML
    private ComboBox<String> cb_gioiTinh;

    @FXML
    private TextField tf_hoTen;

    @FXML
    private TextField tf_lyDo;

    @FXML
    private TextField tf_noiHienTai;

    @FXML
    private TextField tf_maTamVang;

    @FXML
    void luuData(ActionEvent event) {
        if(!checkValidForm(false)){
            return;
        }

        NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();

        TamVangModel tamVangModel = new TamVangModel();
        tamVangModel.setMaGiayTamVang(tf_maTamVang.getText());
        tamVangModel.setNoiTamTru(tf_NoiDen.getText());
        tamVangModel.setIdNhanKhau(cmt.getIdNhanKhau());
        tamVangModel.setLyDo(tf_lyDo.getText());
        tamVangModel.setTuNgay(java.sql.Date.from(date_Tu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamVangModel.setDenNgay(java.sql.Date.from(date_Den.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));


        SimpleResult simpleResult = NhanKhauService.getInstance().taoTamVang(nhanKhauBean, tamVangModel);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, simpleResult.getMessage(), ButtonType.CLOSE);
        alert.showAndWait();

        if (simpleResult.isSuccess()) {
            tf_maTamVang.getScene().getWindow().hide();
            NhanKhauController.frame.refreshScreen();


            tf_maTamVang.getScene().getWindow().hide();
        } else {
            // hide alert
        }
    }

    private boolean checkValidForm(boolean notify){
        if(tf_lyDo.getText().trim().isEmpty()
                || !date_Tu.hasProperties() ||
                tf_NoiDen.getText().trim().isEmpty()
                || tf_maTamVang.getText().trim().isEmpty()
        ){
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Cảnh báo!");
            missingFieldAlert.setContentText("Vui lòng nhập vào những trường bắt buộc còn thiếu");
            missingFieldAlert.show();
            return false;
        }
        return true;

    }

    ObservableList gioiTinhList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
        cb_gioiTinh.setItems(gioiTinhList);
        cb_gioiTinh.getSelectionModel().selectFirst();

        nhanKhauBean = NhanKhauController.chosenNhanKhauBean;
        NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
        tf_hoTen.setText(nhanKhauModel.getHo_ten());
        date_ngaySinh.setValue(convertToLocalDateViaMilisecond(nhanKhauModel.getNamSinh()));
        if(nhanKhauModel.getGioiTinh() == 0){
            cb_gioiTinh.getSelectionModel().select(1);
        }
        tf_cmd.setText(cmt.getSoCMT());
        tf_noiHienTai.setText(nhanKhauModel.getDiaChiHienNay());

    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}