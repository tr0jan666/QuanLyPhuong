package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.services.HoKhauService;
import com.example.quanlyphuong.services.NhanKhauService;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChonChuHoController  implements  Initializable {

    @FXML
    private Button btnHuy;

    @FXML
    private TableColumn<NhanKhauBean, String> diaChiHienNay;

    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> hoTen;

    @FXML
    private TableColumn<NhanKhauBean, Date> ngaySinh;

    @FXML
    private TableColumn<NhanKhauBean, String> soCMT;

    @FXML
    private TableView<NhanKhauBean> tbvDanhSachChuHo;


    List<NhanKhauBean> listNhanKhauBean;
    HoKhauService hoKhauService;

    @FXML
    void chonChuHo(MouseEvent event) {
        NhanKhauBean selectedNhanKhauBean = tbvDanhSachChuHo.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && selectedNhanKhauBean != null){
            ChuHoHolder holder = ChuHoHolder.getInstance();
            holder.setData(selectedNhanKhauBean);


            System.out.println("ten ho khau" +selectedNhanKhauBean.getNhanKhauModel().getHo_ten());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.close();
        }

    }

    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        try {
            listNhanKhauBean = hoKhauService.danhSachNhanKhauCoTheLamChuHo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList nhanKhauBeanObservableList = FXCollections.observableList(listNhanKhauBean);
        hoTen.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        gioiTinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinh()));
        ngaySinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh()));
        soCMT.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));
        diaChiHienNay.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        tbvDanhSachChuHo.setItems(nhanKhauBeanObservableList);
    }
}

