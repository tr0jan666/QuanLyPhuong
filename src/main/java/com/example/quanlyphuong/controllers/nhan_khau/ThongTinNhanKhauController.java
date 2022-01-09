package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
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

public class ThongTinNhanKhauController  implements Initializable {
    public NhanKhauBean nhanKhauBean;

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_luuThayDoi;

    @FXML
    private ComboBox<?> cb_gioiTinh;

    @FXML
    private DatePicker dp_ngaySinh;

    @FXML
    private TextField tf_cmt;

    @FXML
    private TextField tf_danToc;

    @FXML
    private TextField tf_diaChiHienTai;

    @FXML
    private TextField tf_hocVan;

    @FXML
    private TextField tf_maNhanKhau;

    @FXML
    private TextField tf_ngheNghiep;

    @FXML
    private TextField tf_nguyenQuan;

    @FXML
    private TextField tf_noiLamViec;

    @FXML
    private TextField tf_noiSinh;

    @FXML
    private TextField tf_quocTich;

    @FXML
    private TextField tf_ten;

    @FXML
    private TextField tf_thuongTru;

    @FXML
    private TextField tf_tienAn;

    @FXML
    private TextField tf_tonGiao;

    @FXML
    void checkNhanKhau(ActionEvent event) {

    }

    @FXML
    void luuThayDoi(ActionEvent event) {
        if(!checkValidForm(false)){
            return;
        }

        NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();

        nhanKhau.setHo_ten(tf_ten.getText());
        nhanKhau.setNamSinh(java.sql.Date.from(dp_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        int gioiTinh = 0;
        if(cb_gioiTinh.getValue().toString().equals("Nam")){
            gioiTinh = 1;
        }
        nhanKhau.setGioiTinh(gioiTinh);
        nhanKhau.setQuocTich(tf_quocTich.getText());
        nhanKhau.setNoiSinh(tf_noiSinh.getText());
        nhanKhau.setNguyenQuan(tf_nguyenQuan.getText());
        nhanKhau.setDanToc(tf_danToc.getText());
        nhanKhau.setTonGiao(tf_tonGiao.getText());
        nhanKhau.setTienAn(tf_tienAn.getText());
        nhanKhau.setTrinhDoHocVan(tf_hocVan.getText());
        nhanKhau.setDiaChiHienNay(tf_diaChiHienTai.getText());
        nhanKhau.setNgheNghiep(tf_ngheNghiep.getText());
        nhanKhau.setNoiLamViec(tf_noiLamViec.getText());
        nhanKhau.setNoiThuongTru(tf_thuongTru.getText());
        nhanKhau.setIdNguoiTao(AuthService.getInstance().getCurrentUser().getID());

        SimpleResult simpleResult = NhanKhauService.getInstance().suaNhanKhau(nhanKhauBean);

        Alert alert;

        if(simpleResult.isSuccess()){
            alert = new Alert(Alert.AlertType.CONFIRMATION, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
            NhanKhauController.frame.refreshScreen();
            tf_cmt.getScene().getWindow().hide();
        }else {
            alert = new Alert(Alert.AlertType.ERROR, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    private boolean checkValidForm(boolean notify){
        if(tf_ten.getText().trim().isEmpty()
                || !dp_ngaySinh.hasProperties() ||
                tf_noiSinh.getText().trim().isEmpty() ||
                tf_danToc.getText().trim().isEmpty() ||
                tf_cmt.getText().trim().isEmpty() ||
                tf_thuongTru.getText().trim().isEmpty()||
                tf_quocTich.getText().trim().isEmpty()||
                tf_nguyenQuan.getText().trim().isEmpty()||
                tf_tonGiao.getText().trim().isEmpty()||
                tf_diaChiHienTai.getText().trim().isEmpty() ||
                tf_maNhanKhau.getText().trim().isEmpty()
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
        tf_ten.setText(nhanKhauModel.getHo_ten());
        dp_ngaySinh.setValue(convertToLocalDateViaMilisecond(nhanKhauModel.getNamSinh()));
        if(nhanKhauModel.getGioiTinh() == 0){
            cb_gioiTinh.getSelectionModel().select(1);
        }
        tf_quocTich.setText(nhanKhauModel.getQuocTich());
        tf_noiSinh.setText( nhanKhauModel.getNoiSinh());
        tf_nguyenQuan.setText(nhanKhauModel.getNguyenQuan()); ;
        tf_danToc.setText( nhanKhauModel.getDanToc());
        tf_tonGiao.setText(nhanKhauModel.getTonGiao());
        tf_cmt.setText(cmt.getSoCMT());
        tf_tienAn.setText(nhanKhauModel.getTienAn());
        tf_hocVan.setText(nhanKhauModel.getTrinhDoHocVan());
        tf_diaChiHienTai.setText(nhanKhauModel.getDiaChiHienNay());
        tf_ngheNghiep.setText(nhanKhauModel.getNgheNghiep());
        tf_noiLamViec.setText(nhanKhauModel.getNoiLamViec());
        tf_thuongTru.setText(nhanKhauModel.getNoiThuongTru());
        tf_maNhanKhau.setText(nhanKhauModel.getMaNhanKhau());

    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}