package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.services.HoKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TachHoKhauController implements Initializable {

    @FXML
    private TableView<HoKhauModel> tbvHoCanTach;

    @FXML
    private TableColumn<HoKhauModel, String> maHoKhauChuHo;

    @FXML
    private TableColumn<HoKhauModel, String> hoTenChuHo;

    @FXML
    private TableColumn<HoKhauModel, String> diaChiChuHo;

    @FXML
    private TableView<HoKhauModel> tbvNguoiSangHoMoi;

    @FXML
    private TableColumn<HoKhauModel, String> hoTenNhanKhauCu;

    @FXML
    private TableColumn<HoKhauModel, Date> ngaySinhNhanKhauCu;

    @FXML
    private TableColumn<HoKhauModel, String> quanHeCu;

    @FXML
    private TextField tfChuHoHienTai;

    @FXML
    private TextField tfMaKhuVuc;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfMaHoKhauMoi;

    @FXML
    private TextField tfChuHoMoi;

    @FXML
    private Button btnChuyenSang;

    @FXML
    private Button btnChuyenVe;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private Button btnChonChuHoMoi;

    @FXML
    private TableView<?> tbvNguoiHoMoi;

    @FXML
    private TableColumn<HoKhauModel, String> hoTenNhanKhauMoi;

    @FXML
    private TableColumn<HoKhauModel, Date> ngaySinhMoi;

    @FXML
    private TableColumn<HoKhauModel, Date> quanHeMoi;


    @FXML
    void chuyenSang(ActionEvent event) {

    }

    @FXML
    void chuyenVe(ActionEvent event) {

    }


    @FXML
    void xacNhanTachHoKHau(ActionEvent event) {

    }

    @FXML
    void chonChuHo(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/chon_chu_ho.fxml", "chon_chu_ho", null);
    }




    @FXML
    void huyTachHoKhau(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();

    }


    ArrayList<HoKhauModel> listHoKhauModels;
    HoKhauService hoKhauService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        listHoKhauModels = hoKhauService.getListHoKhau();

        ObservableList observableListHoKhau = FXCollections.observableList(listHoKhauModels);
        maHoKhauChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getMaHoKhau()));
        diaChiChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getDiaChi()));
        hoTenChuHo.setCellValueFactory(hoKhau -> new ReadOnlyObjectWrapper<>(hoKhau.getValue().getHoTenChuHo()));
        tbvHoCanTach.setItems(observableListHoKhau);
    }
}

