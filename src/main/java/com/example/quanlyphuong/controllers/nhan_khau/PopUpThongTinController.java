package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpThongTinController implements Initializable {

    @FXML
    private TextField tf_HovaTen;

    @FXML
    private Button btn_Luu;

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
    private  TextField tf_chuyenMon;

    @FXML
    private  TextField tf_LiDoChuyenDen;

    @FXML
    private  TextField tf_NgayChuyenDen;

    @FXML
    private  TextField tf_LiDoChuyenDi;

    @FXML
    private  TextField tf_NgayChuyenDi;

    @FXML
    private TextField tf_NgaySinh;

    @FXML
    private TextField tf_GioiTinh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NhanKhauService nhanKhauService = new NhanKhauService();

    }

    public void AddData(NhanKhauModel nhanKhauModel){
        tf_HovaTen.setText(nhanKhauModel.getHo_ten());
        tf_GioiTinh.setText(nhanKhauModel.getGioiTinh());
        tf_NgaySinh.setText(String.valueOf(nhanKhauModel.getNamSinh()));
        tf_quocTich.setText(nhanKhauModel.getQuocTich());
        tf_noiSinh.setText(nhanKhauModel.getNoiSinh());
        tf_nguyenQuan.setText(nhanKhauModel.getNguyenQuan());
        tf_danToc.setText(nhanKhauModel.getDanToc());
        tf_tonGiao.setText(nhanKhauModel.getTonGiao());
        tf_tienAn.setText(nhanKhauModel.getTienAn());
        tf_hocVan.setText(nhanKhauModel.getTrinhDoHocVan());
        tf_ngheNghiep.setText(nhanKhauModel.getNgheNghiep());
        tf_noiLamViec.setText(nhanKhauModel.getNoiLamViec());
        tf_thuongTru.setText(nhanKhauModel.getNoiThuongTru());
        tf_maNhanKhau.setText(nhanKhauModel.getMaNhanKhau());
        tf_diaChiHienTai.setText(nhanKhauModel.getDiaChiHienNay());
        tf_LiDoChuyenDen.setText(nhanKhauModel.getLyDoChuyenDen());
        tf_NgayChuyenDen.setText(String.valueOf(nhanKhauModel.getNgayChuyenDen()));
        tf_LiDoChuyenDi.setText(nhanKhauModel.getLyDoChuyenDi());
        tf_NgayChuyenDi.setText(String.valueOf(nhanKhauModel.getNgayChuyenDi()));
    }
}
