package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    List<NhanKhauBean> listNhanKhauBean;
    ObservableList<NhanKhauBean> observableListNhanKhauBeans;

    @FXML
    private Button btn_ThemNhanKhau;

    @FXML
    private Button btn_Xoa;

    @FXML
    private Button btn_chiTiet;

    @FXML
    private Button btn_khaiTu;

    @FXML
    private Button btn_tamTru;

    @FXML
    private Button btn_tamVang;

    @FXML
    private Button btn_timKiem;

    @FXML
    private TableView<NhanKhauBean> tv_nhanKhau;

    @FXML
    private TableColumn<NhanKhauBean, String> col_cmt;

    @FXML
    private TableColumn<NhanKhauBean, String> col_gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> col_hoVaTen;

    @FXML
    private TableColumn<NhanKhauBean, Date> col_ngaySinh;

    @FXML
    private TableColumn<NhanKhauBean, String > col_ngheNghiep;

    @FXML
    private TableColumn<NhanKhauBean,String> col_diaChi;

    @FXML
    private Button btn_refresh;

    @FXML
    void refreshTrang(){
        refreshScreen();
    }

    @FXML
    void khauTuNhanKhau(ActionEvent event){

    }

    @FXML
    void tamTruNhanKhau(ActionEvent event) {

    }

    @FXML
    void tamVangNhanKhau(ActionEvent event) {

    }

    @FXML
    void themNhanKhau(ActionEvent event) throws IOException {
        UIHelper.navigateNew("nhan_khau/pop_up_them_nhan_khau.fxml", "Thêm nhân khẩu", null,1000, 700);
//        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    @FXML
    void xemChiTietNhanKhau(ActionEvent event) {

    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshScreen();



    }

    public void chooseOption(ActionEvent event){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("");

        CheckBox box1 = new CheckBox("Thêm thường trú");
        CheckBox box2 = new CheckBox("Thêm tạm trú");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        dialog.getDialogPane().getChildren().addAll(box1,box2);
        dialog.setResultConverter(dialogButton ->{
            if(dialogButton == ButtonType.OK){
                if(box1.isSelected()){
                    changeSceneThuongTru(event);
                }else if(box2.isSelected()){
                    changeSceneTamTru(event);
                }else return null;
            }
            return null;
        });
    }

    public void changeSceneTamTru(ActionEvent event) {
        UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_tam_tru.fxml", "Đăng ký tạm trú", null);
        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    public void changeSceneThuongTru(ActionEvent event) {
        UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_thuong_tru.fxml", "Đăng ký thường trú", null);
        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    public void refreshScreen(){

        listNhanKhauBean=  NhanKhauService.getInstance().getListNhanKhau();

        observableListNhanKhauBeans = FXCollections.observableList(listNhanKhauBean);

        col_hoVaTen.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        col_cmt.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));
        col_ngaySinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh()));
        col_diaChi.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        col_ngheNghiep.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNgheNghiep()));
        col_gioiTinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinhString()));

        tv_nhanKhau.setItems(observableListNhanKhauBeans);

    }
}