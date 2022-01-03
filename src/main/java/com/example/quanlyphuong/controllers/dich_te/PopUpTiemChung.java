package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.TiemChungBean;
import com.example.quanlyphuong.services.TiemChungService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PopUpTiemChung implements Initializable {
    @FXML
    private TextField tf_HoTen;
    @FXML
    private TextField tf_CCCD;
    @FXML
    private TextField tf_LoaiVacxinL1;
    @FXML
    private TextField tf_LoaiVacxinL2;
    @FXML
    private DatePicker dp_NgayTiemL1;
    @FXML
    private DatePicker dp_NgayTiemL2;
    @FXML
    private Button btn_CapNhat;

    TiemChungService tiemChungService;
    public void setData(TiemChungBean bean){
        tiemChungService = new TiemChungService();
        int id = bean.getNhanKhauBean().getNhanKhauModel().getID();
        tf_HoTen.setText(bean.getNhanKhauBean().getNhanKhauModel().getHo_ten());
        tf_CCCD.setText(bean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tf_LoaiVacxinL1.setText(tiemChungService.getTiemChungL1(id).getVacxin());
        dp_NgayTiemL1.setValue(LocalDate.parse((CharSequence) tiemChungService.getTiemChungL1(id).getNgayTiem()));
        tf_LoaiVacxinL2.setText(tiemChungService.getTiemChungL2(id).getVacxin());
        dp_NgayTiemL2.setValue(LocalDate.parse((CharSequence) tiemChungService.getTiemChungL2(id).getNgayTiem()));
    }

    @FXML
    public void updateData(ActionEvent event){
        tiemChungService = new TiemChungService();
        int id = tiemChungService.getIDfromCCCD(Integer.parseInt(tf_CCCD.getText()));
        tiemChungService.updateTiemChung1(id,tf_LoaiVacxinL1.getText(), String.valueOf(dp_NgayTiemL1.getValue()));
        tiemChungService.updateTiemChung2(id,tf_LoaiVacxinL2.getText(), String.valueOf(dp_NgayTiemL2.getValue()));
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        tf_HoTen.setDisable(true);
        tf_CCCD.setDisable(true);
    }
}
