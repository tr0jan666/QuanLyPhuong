package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.*;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {
    NhanKhauBean nhanKhauBean;
    NhanKhauBean nguoiKhaiBean;

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
    private TextField tf_lyDoMat;

    @FXML
    private Button btn_check;

    @FXML
    public void checkCCCD(ActionEvent event){
        nguoiKhaiBean = NhanKhauService.getInstance().getNhanKhau(tf_cmtNguoiKhai.getText());
        if (nguoiKhaiBean == null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD! Bạn có muốn thêm nhân khẩu mới không?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                UIHelper.navigateNew("nhan_khau/pop_up_them_nhan_khau.fxml","Thêm nhân khẩu");
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quanlyphuong/nhan_khau/pop_up_them_nhan_khau.fxml"));
//                Parent root1 = (Parent) fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.initStyle(StageStyle.UNDECORATED);
//                stage.setTitle("ABC");
//                stage.setScene(new Scene(root1));
//                stage.show();
                return;
            } else {
                return;
            }


        } else {
            return;
        }
    }

    public boolean checkNguoiKhai(){
        nguoiKhaiBean = NhanKhauService.getInstance().getNhanKhau(tf_cmtNguoiKhai.getText());
        if (nguoiKhaiBean == null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD!");
            return false;

        } else {
            tf_hoTenNguoiKhai.setText(nhanKhauBean.getNhanKhauModel().getHo_ten());
            tf_hoTenNguoiKhai.setEditable(false);
            return true;
        }
    }

    @FXML
    void luuThongTin(ActionEvent event) {
        if(!checkValidForm()){
            return;
        }

        if(checkNguoiKhai()) {
            tf_hoTenNguoiKhai.setText(nguoiKhaiBean.getNhanKhauModel().getHo_ten());

            NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
            ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();


            KhaiTuModel khaiTuModel = new KhaiTuModel();

            khaiTuModel.setIdNguoiChet(cmt.getIdNhanKhau());
            khaiTuModel.setIdNguoiKhai(nguoiKhaiBean.getChungMinhThuModel().getIdNhanKhau());

            khaiTuModel.setNgayChet(java.sql.Date.from(date_ngayMat.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            Calendar calendar = Calendar.getInstance();
            java.sql.Date createDate = new java.sql.Date(calendar.getTime().getTime());
            khaiTuModel.setNgayKhai(createDate);

//        tamVangModel.setMaGiayTamVang(tf_maTamVang.getText());
//        tamVangModel.setNoiTamTru(tf_NoiDen.getText());
//        tamVangModel.setIdNhanKhau(cmt.getIdNhanKhau());
//        tamVangModel.setLyDo(tf_lyDo.getText());
//        tamVangModel.setTuNgay(java.sql.Date.from(date_Tu.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        tamVangModel.setDenNgay(java.sql.Date.from(date_Den.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));


            SimpleResult simpleResult = NhanKhauService.getInstance().khaiTuNhanKhau(nhanKhauBean, khaiTuModel);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, simpleResult.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();

            if (simpleResult.isSuccess()) {
                tf_hoTenNguoiKhai.getScene().getWindow().hide();
                NhanKhauController.frame.refreshScreen();

            } else {
                // hide alert
            }
        }
    }

    private boolean checkValidForm(){
        if(tf_hoTenNguoiKhai.getText().trim().isEmpty()
                || !tf_cmtNguoiKhai.hasProperties() ||
                tf_maGiayKhaiTu.getText().trim().isEmpty()
                || tf_lyDoMat.getText().trim().isEmpty()
                || (date_ngayMat.getValue()==null)
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
