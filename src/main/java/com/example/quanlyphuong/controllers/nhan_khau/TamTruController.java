package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TamTruModel;
import com.example.quanlyphuong.services.AuthService;
import com.example.quanlyphuong.services.ChungMinhThuService;
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


public class TamTruController implements Initializable {

    @FXML
    private Button btn_save;

    @FXML
    private ComboBox<String> cb_gioiTinh;

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
    private TextField tf_noiThuongTru;

    @FXML
    private TextField tf_sdt;

    @FXML
    private TextField tf_maNhanKhau;

    @FXML
    private TextField tf_ten;


    @FXML
    void DangKyTamTru(ActionEvent event) {
        if(!checkValidForm(false)){
            return;
        }
        NhanKhauModel nhanKhau = new NhanKhauModel();
        ChungMinhThuModel cmt = new ChungMinhThuModel();

        nhanKhau.setHo_ten(tf_ten.getText());
        nhanKhau.setNamSinh(Date.from(date_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        int gioiTinh = 0;
        if(cb_gioiTinh.getValue().toString().equals("Nam")){
            gioiTinh = 1;
        }
        nhanKhau.setGioiTinh(gioiTinh);
        nhanKhau.setQuocTich("");
        nhanKhau.setNoiSinh("");
        nhanKhau.setNguyenQuan("");
        nhanKhau.setDanToc("");
        nhanKhau.setTonGiao("");
        nhanKhau.setTienAn("");
        nhanKhau.setTrinhDoHocVan("");
        nhanKhau.setDiaChiHienNay("");
        nhanKhau.setNgheNghiep("");
        nhanKhau.setNoiLamViec("");
        nhanKhau.setNoiThuongTru(tf_noiThuongTru.getText());
        nhanKhau.setIdNguoiTao(AuthService.getInstance().getCurrentUser().getID());
        nhanKhau.setMaNhanKhau(tf_maNhanKhau.getText());

        cmt.setSoCMT(tf_cmt.getText());
//        cmt.setIdNhanKhau(Integer.parseInt(tf_maNhanKhau.getText()));

        NhanKhauBean nhanKhauBean = new NhanKhauBean();
        nhanKhauBean.setNhanKhauModel(nhanKhau);
        nhanKhauBean.setChungMinhThuModel(cmt);

//        NhanKhauService.getInstance().taoNhanKhau(nhanKhauBean);

        TamTruModel tamTruModel = new TamTruModel();
        tamTruModel.setMaGiayTamTru(tf_maGiayTamTru.getText());
        tamTruModel.setSoDienThoaiNguoiDangKy(tf_sdt.getText());
        tamTruModel.setTuNgay(Date.from(date_tuNgay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamTruModel.setDenNgay(Date.from(date_denNgay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamTruModel.setLyDo(tf_lyDo.getText());

        NhanKhauService.getInstance().taoTamTru(nhanKhauBean, tamTruModel);
    }

    private boolean checkValidForm(boolean notify) {

        if(tf_maGiayTamTru.getText().trim().isEmpty() ||
                !date_ngaySinh.hasProperties() ||
                tf_cmt.getText().trim().isEmpty()||
                tf_noiThuongTru.getText().trim().isEmpty() ||
                tf_sdt.getText().trim().isEmpty() ||
                !date_tuNgay.hasProperties() ||
                !date_denNgay.hasProperties() ||
                tf_lyDo.getText().trim().isEmpty() ||
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
        if(validateCMT(notify)){
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



    public boolean validateCMT(boolean notify){
        SimpleResult result = ChungMinhThuService.getInstance().checkCMTTonTai(tf_cmt.getText());
        Alert alert;
        if(result.isSuccess()){
            if(notify==true) {
                alert = new Alert(Alert.AlertType.CONFIRMATION, result.getMessage(), ButtonType.CLOSE);
                alert.showAndWait();
            }
            return true;
        }      else {

            alert = new Alert(Alert.AlertType.ERROR, result.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();

        }

        return false;
    }
}
