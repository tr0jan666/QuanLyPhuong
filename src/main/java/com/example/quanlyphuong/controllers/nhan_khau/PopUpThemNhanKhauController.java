package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.AuthService;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class PopUpThemNhanKhauController implements Initializable {

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_themNhanKhau;

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
    private TextField tf_ngheNghiep;

    @FXML
    private TextField tf_noiLamViec;

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
    private TextField tf_noiSinh;

    @FXML
    private TextField tf_nguyenQuan;

    @FXML
    private TextField tf_maNhanKhau;

    @FXML
    void checkNhanKhau(ActionEvent event) {
        if(checkValidForm()){

        }
    }

    @FXML
    void themNhanKhau(ActionEvent event) {
        NhanKhauModel nhanKhau = new NhanKhauModel();
        ChungMinhThuModel cmt = new ChungMinhThuModel();

        nhanKhau.setHo_ten(tf_ten.getText());
        nhanKhau.setNamSinh(Date.from(dp_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        nhanKhau.setGioiTinh(cb_gioiTinh.getValue().toString());
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

        cmt.setSoCMT(tf_cmt.getText());
        cmt.setIdNhanKhau(Integer.parseInt(tf_maNhanKhau.getText()));

//        NhanKhauService.getIn

    }

    private boolean checkValidForm(){
        if(tf_ten.getText().trim().isEmpty()
                || !dp_ngaySinh.hasProperties() ||
                tf_noiSinh.getText().trim().isEmpty() ||
                tf_danToc.getText().trim().isEmpty() ||
                tf_cmt.getText().trim().isEmpty() ||
                tf_thuongTru.getText().trim().isEmpty()||
                tf_quocTich.getText().trim().isEmpty()||
                tf_nguyenQuan.getText().trim().isEmpty()||
                tf_tonGiao.getText().trim().isEmpty()||
                tf_diaChiHienTai.getText().trim().isEmpty()
            ){
            Alert missingFieldAlert = new Alert(Alert.AlertType.ERROR);
            missingFieldAlert.setTitle("Cảnh báo!");
            missingFieldAlert.setContentText("Vui lòng nhập vào những trường bắt buộc còn thiếu");
            missingFieldAlert.show();
            return false;
        }

        //Check cmt
        try{
            long d = Long.parseLong(tf_cmt.getText());
        }catch (Exception E){
            Alert invalidCMT = new Alert(Alert.AlertType.ERROR);
            invalidCMT.setTitle("Cảnh báo!");
            invalidCMT.setContentText("Số cmt chỉ có thể chứa các số");
            invalidCMT.show();
            return false;
        }

        return true;
    }

    ObservableList gioiTinhList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
        cb_gioiTinh.setItems(gioiTinhList);
        cb_gioiTinh.getSelectionModel().selectFirst();



    }
}

