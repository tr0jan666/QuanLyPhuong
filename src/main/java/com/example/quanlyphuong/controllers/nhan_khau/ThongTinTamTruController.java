package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TamTruModel;
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

import static com.example.quanlyphuong.helper.CommonUtils.convertToLocalDateViaMilisecond;

public class ThongTinTamTruController implements Initializable {
    NhanKhauBean nhanKhauBean;
    TamTruModel tamTruModel;

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
        if(!checkValidForm(false)){
            return;
        }
        TamTruModel tamTruModel = new TamTruModel();
        tamTruModel.setMaGiayTamTru(tf_maGiayTamTru.getText());
        tamTruModel.setSoDienThoaiNguoiDangKy(tf_sdt.getText());
        tamTruModel.setTuNgay(Date.from(date_tuNgay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamTruModel.setDenNgay(Date.from(date_denNgay.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tamTruModel.setLyDo(tf_lyDo.getText());
        tamTruModel.setIdNhanKhau(nhanKhauBean.getChungMinhThuModel().getIdNhanKhau());

        SimpleResult result = NhanKhauService.getInstance().updateTamTru(tamTruModel);
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, result.getMessage(), ButtonType.OK);
        successAlert.showAndWait();

        if (result.isSuccess()) {
            tf_cmt.getScene().getWindow().hide();
            NhanKhauController.frame.refreshScreen();

        } else {
            // hide alert
        }
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

        TamTruModel tamTruModel = NhanKhauService.getInstance().getTamTru(cmt.getIdNhanKhau());



        date_tuNgay.setValue(convertToLocalDateViaMilisecond(tamTruModel.getTuNgay()));
        date_denNgay.setValue(convertToLocalDateViaMilisecond(tamTruModel.getDenNgay()));
        tf_maGiayTamTru.setText(tamTruModel.getMaGiayTamTru());
        if(nhanKhauModel.getGioiTinh() == 0){
            cb_gioiTinh.getSelectionModel().select(1);
        }

        tf_cmt.setText(cmt.getSoCMT());
        date_ngaySinh.setValue(convertToLocalDateViaMilisecond(nhanKhauModel.getNamSinh()));
        tf_noiThuongTru.setText(nhanKhauModel.getNoiThuongTru());
        tf_sdt.setText(tamTruModel.getSoDienThoaiNguoiDangKy());
        tf_lyDo.setText(tamTruModel.getLyDo());
        tf_maNhanKhau.setText(nhanKhauModel.getMaNhanKhau());


    }


}

