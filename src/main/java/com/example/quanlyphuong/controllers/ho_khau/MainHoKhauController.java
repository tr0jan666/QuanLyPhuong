package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.HoKhauService;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.beans.property.Property;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainHoKhauController implements Initializable {


    @FXML
    private TableView<HoKhauModel> tbvBangThongKe;

    @FXML
    private TableColumn<HoKhauModel, String> maHoKhau;

    @FXML
    private TableColumn<HoKhauModel, String> hoTenChuHo;

    @FXML
    private TableColumn<HoKhauModel, String> diaChi;

    @FXML
    private Button btnThemMoi;

    @FXML
    private Button btnTachHoKhau;

    @FXML
    private TextField tfTimKiem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnHoanTac;

    @FXML
    void changeSceneThemMoi(ActionEvent event) throws IOException {
        UIHelper.navigateNew("ho_khau/them_moi.fxml", "Thêm mới hộ khẩu", null);
    }
    @FXML
    void changeSceneTachHoKhau(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/tach_hk.fxml", "Tách  hộ khẩu", null);
    }

    ArrayList<HoKhauModel> listHoKhauModels;
    HoKhauService hoKhauService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        listHoKhauModels = hoKhauService.getListHoKhau();

        ObservableList observableListHoKhau = FXCollections.observableList(listHoKhauModels);
        maHoKhau.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getMaHoKhau()));
        diaChi.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getDiaChi()));

        hoTenChuHo.setCellValueFactory(hoKhau -> new ReadOnlyObjectWrapper<>(hoKhau.getValue().getHoTenChuHo()));
        tbvBangThongKe.setItems(observableListHoKhau);


    }

    @FXML
    void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}
