package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.MemOfFamily;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.ThanhVienCuaHoModel;
import com.example.quanlyphuong.services.HoKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.lang.reflect.Member;
import java.net.URL;
import java.util.*;

public class TachHoKhauController implements Initializable {
    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;
    ObservableList<MemOfFamily> memOfFamilies;
    ObservableList<MemOfFamily> memOfFamilies2;

    NhanKhauModel chuHo;

    @FXML
    private TableView<HoKhauBean> tbvHoCanTach;

    @FXML
    private TableColumn<HoKhauBean, String> maHoKhauChuHo;

    @FXML
    private TableColumn<HoKhauBean, String> hoTenChuHo;

    @FXML
    private TableColumn<HoKhauBean, String> diaChiChuHo;

    @FXML
    private TableView<MemOfFamily> tbvNguoiSangHoMoi;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenNhanKhauCu;

    @FXML
    private TableColumn<MemOfFamily, Date> ngaySinhNhanKhauCu;

    @FXML
    private TableColumn<MemOfFamily, String> quanHeCu;

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
    private TableView<MemOfFamily> tbvNguoiHoMoi;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenNhanKhauMoi;

    @FXML
    private TableColumn<MemOfFamily, Date> ngaySinhMoi;

    @FXML
    private TableColumn<MemOfFamily, Date> quanHeMoi;


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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        listHoKhauBeans = hoKhauService.getListHoKhau();

        ObservableList observableListHoKhau = FXCollections.observableList(listHoKhauBeans);
        maHoKhauChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        diaChiChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        hoTenChuHo.setCellValueFactory(hoKhau -> new ReadOnlyObjectWrapper<>(hoKhau.getValue().getChuHo().getHo_ten()));
        tbvHoCanTach.setItems(observableListHoKhau);
    }

    public void chonHoCanTach(MouseEvent mouseEvent) {
        HoKhauBean hoKhauBean = tbvHoCanTach.getSelectionModel().getSelectedItem();
        List<ThanhVienCuaHoModel> thanhVienCuaHoModels = hoKhauBean.getListThanhVienCuaHo();
        List<NhanKhauModel> nhanKhauModels = hoKhauBean.getListNhanKhauModels();

        List<MemOfFamily> memOfFamilyList = new ArrayList<>();

        for(NhanKhauModel nk : nhanKhauModels){
            System.out.println(nk.getHo_ten());
        }
        for(ThanhVienCuaHoModel ho: thanhVienCuaHoModels){
            System.out.println(ho.getIdHoKhau()+"-"+ho.getIdNhanKhau());
        }
        tfChuHoHienTai.setText(hoKhauBean.getChuHo().getHo_ten());

        //Set thanh vien cua ho
        thanhVienCuaHoModels.stream().forEach(thanhVienCuaHoModel -> {
            MemOfFamily memOfFamily = new MemOfFamily();
            memOfFamily.setThanhVienCuaHoModel(thanhVienCuaHoModel);
            memOfFamilyList.add(memOfFamily);
        });

        System.out.println(memOfFamilyList.size());


        for (int i = 0; i < memOfFamilyList.size(); i++) {
            System.out.println(nhanKhauModels.get(i).getHo_ten());
            NhanKhauModel nk = nhanKhauModels.get(i);
            NhanKhauBean bean = new NhanKhauBean();
            bean.setNhanKhauModel(nk);
            memOfFamilyList.get(i).setNhanKhau(bean);
        }
//        for(int i =0;i <memOfFamilyList.size();i++){
//            System.out.println(memOfFamilyList.get(i).getNhanKhau().getNhanKhauModel());
//        }

//
//
        memOfFamilies = FXCollections.observableList(memOfFamilyList);
        hoTenNhanKhauCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHo_ten()));
        ngaySinhNhanKhauCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh()));
        quanHeCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        tbvNguoiSangHoMoi.setItems(memOfFamilies);








    }
}

