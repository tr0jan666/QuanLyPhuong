package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChonChuHoController   {

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
    NhanKhauService nhanKhauService;

    @FXML
    void chonChuHo(MouseEvent event) {

    }

    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }


}

