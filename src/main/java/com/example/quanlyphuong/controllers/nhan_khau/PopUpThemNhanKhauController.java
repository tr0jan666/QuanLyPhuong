package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.AuthService;
import com.example.quanlyphuong.services.ChungMinhThuService;
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
import java.util.List;
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
        if(!checkValidForm()){
            return;
        }
        NhanKhauModel nhanKhau = new NhanKhauModel();
        ChungMinhThuModel cmt = new ChungMinhThuModel();

        nhanKhau.setHo_ten(tf_ten.getText());
        nhanKhau.setNamSinh(Date.from(dp_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

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
        nhanKhau.setMaNhanKhau(tf_maNhanKhau.getText());

        cmt.setSoCMT(tf_cmt.getText());
        cmt.setIdNhanKhau(Integer.parseInt(tf_maNhanKhau.getText()));

        NhanKhauBean nhanKhauBean = new NhanKhauBean();
        nhanKhauBean.setNhanKhauModel(nhanKhau);
        nhanKhauBean.setChungMinhThuModel(cmt);

        NhanKhauService.getInstance().taoNhanKhau(nhanKhauBean);

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
                tf_diaChiHienTai.getText().trim().isEmpty() ||
                tf_maNhanKhau.getText().trim().isEmpty()
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

        //check cmt lan 2
        if(validateCMT()){
            return true;
        }else{
            return false;
        }
    }

    ObservableList gioiTinhList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioiTinhList = FXCollections.observableArrayList("Nam", "Nữ");
        cb_gioiTinh.setItems(gioiTinhList);
        cb_gioiTinh.getSelectionModel().selectFirst();



    }

    public void checkCMT(ActionEvent actionEvent) {
        validateCMT();
    }

    public boolean validateCMT(){
        SimpleResult result = ChungMinhThuService.getInstance().checkCMTTonTai(tf_cmt.getText());
        Alert alert;
        if(result.isSuccess()){
            alert = new Alert(Alert.AlertType.CONFIRMATION, result.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
            return true;
        }      else {
            alert = new Alert(Alert.AlertType.ERROR, result.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

        return false;
    }


}

