package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.CommonUtils;
import com.example.quanlyphuong.helper.constants.GioiTinhConstant;
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
import java.util.Calendar;
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
        if(checkValidForm(true)){

        }
    }

    @FXML
    void themNhanKhau(ActionEvent event) {
        if(!checkValidForm(false)){
            return;
        }
        NhanKhauModel nhanKhau = new NhanKhauModel();
        ChungMinhThuModel cmt = new ChungMinhThuModel();

        nhanKhau.setHo_ten(tf_ten.getText());
        nhanKhau.setNamSinh(Date.from(dp_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        int gioiTinh;
        if(cb_gioiTinh.getValue().toString().equals("Nam")){
            gioiTinh = GioiTinhConstant.NAM;
        }else gioiTinh = GioiTinhConstant.NU;
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
        nhanKhau.setStatus(1);

        cmt.setSoCMT(tf_cmt.getText());
        cmt.setIdNhanKhau(Integer.parseInt(tf_maNhanKhau.getText()));

        NhanKhauBean nhanKhauBean = new NhanKhauBean();
        nhanKhauBean.setNhanKhauModel(nhanKhau);
        nhanKhauBean.setChungMinhThuModel(cmt);

        SimpleResult simpleResult = NhanKhauService.getInstance().taoNhanKhau(nhanKhauBean);
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
                || dp_ngaySinh.getValue()==null ||
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
            missingFieldAlert.setTitle("C???nh b??o!");
            missingFieldAlert.setContentText("Vui l??ng nh???p v??o nh???ng tr?????ng b???t bu???c c??n thi???u");
            missingFieldAlert.show();
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());
        if(dp_ngaySinh.getValue().isAfter(CommonUtils.convertToLocalDateViaMilisecond(today))){
            Alert ngaySinhAlert = new Alert(Alert.AlertType.ERROR);
            ngaySinhAlert.setTitle("C???nh b??o!");
            ngaySinhAlert.setContentText("Ng??y sinh kh??ng h???p l???");
            ngaySinhAlert.show();
            return false;
        }

        //Check cmt
        try{
            long d = Long.parseLong(tf_cmt.getText());
        }catch (Exception E){
            Alert invalidCMT = new Alert(Alert.AlertType.ERROR);
            invalidCMT.setTitle("C???nh b??o!");
            invalidCMT.setContentText("S??? cmt ch??? c?? th??? ch???a c??c s???");
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
        gioiTinhList = FXCollections.observableArrayList("Nam", "N???");
        cb_gioiTinh.setItems(gioiTinhList);
        cb_gioiTinh.getSelectionModel().selectFirst();



    }

    public void checkCMT(ActionEvent actionEvent) {
        validateCMT(true);
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

