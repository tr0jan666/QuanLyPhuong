package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.*;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static com.example.quanlyphuong.helper.CommonUtils.convertToLocalDateViaMilisecond;

public class ThongTinTamVangController  implements Initializable {
    NhanKhauBean nhanKhauBean;
    TamVangModel tamVangModel;

    @FXML
    private Button btn_luu;

    @FXML
    private ComboBox<?> cb_gioiTinh;

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
    private TextField tf_hoTen;

    @FXML
    private TextField tf_lyDo;

    @FXML
    private TextField tf_maTamVang;

    @FXML
    private TextField tf_noiHienTai;

    @FXML
    void luuData(ActionEvent event) {
        if(!checkValidForm(false)){
            return;
        }
        tamVangModel.setMaGiayTamVang(tf_maTamVang.getText());
        tamVangModel.setTuNgay(Date.from(date_Tu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamVangModel.setDenNgay(Date.from(date_Den.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamVangModel.setLyDo(tf_lyDo.getText());
        tamVangModel.setNoiTamTru(tf_NoiDen.getText());
        tamVangModel.setIdNhanKhau(nhanKhauBean.getChungMinhThuModel().getIdNhanKhau());



        SimpleResult result = NhanKhauService.getInstance().updateTamVang(tamVangModel);
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, result.getMessage(), ButtonType.OK);
        successAlert.showAndWait();

        if (result.isSuccess()) {
            tf_maTamVang.getScene().getWindow().hide();
            NhanKhauController.frame.refreshScreen();

        } else {
            // hide alert
        }
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

        tamVangModel = NhanKhauService.getInstance().getTamVang(cmt.getIdNhanKhau());

        tf_maTamVang.setText(tamVangModel.getMaGiayTamVang());
        date_Tu.setValue(convertToLocalDateViaMilisecond(tamVangModel.getTuNgay()));
        date_Den.setValue(convertToLocalDateViaMilisecond(tamVangModel.getDenNgay()));

        tf_lyDo.setText(tamVangModel.getLyDo());
        tf_NoiDen.setText(tamVangModel.getNoiTamTru());
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
}
