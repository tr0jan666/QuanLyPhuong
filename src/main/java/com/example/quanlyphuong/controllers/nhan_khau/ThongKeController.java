package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThongKeController<SceneSwitch> {

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXuatFile;

    @FXML
    private TextField denTuoi;

    @FXML
    private TableColumn<?, ?> diaChi;

    @FXML
    private TableColumn<?, ?> gioiTinh;

    @FXML
    private ComboBox<String > gioiTinhCB;

    @FXML
    private TableColumn<?, ?> hoTen;

    @FXML
    private TableColumn<?, ?> maHo;

    @FXML
    private TableColumn<?, ?> namSinh;

    @FXML
    private TableView<?> table;

    @FXML
    private ComboBox<String> tinhTrangCB;

    @FXML
    private TextField tuTuoi;

    @FXML
    void setTimKiem(ActionEvent event) {


    }
 /*   int min=-1,max=200;
    SceneSwitch sceneSwitch;
    List<NhanKhauBean> listNhanKhauBeans;
    NhanKhauService nhanKhauService;
    ObservableList<NhanKhauModel> observablelistNhanKhau;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tinhTrangList;
    int accessCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneSwitch = new SceneSwitch();
        nhanKhauService = new NhanKhauService();
        setData();
        gioiTinhList = FXCollections.observableArrayList("Toàn bộ", "Nam", "Nữ");
        tinhTrangList = FXCollections.observableArrayList("Toàn bộ", "Thường trú", "Tạm trú", "Tạm vắng");
        gioiTinhCB.setItems(gioiTinhList);
        gioiTinhCB.getSelectionModel().selectFirst();
        tinhTrangCB.setItems(tinhTrangList);
        tinhTrangCB.getSelectionModel().selectFirst();
    }

    public void setData() {
        int tuTuoi = -1;
        int denTuoi = 200;
      //  int tuNam = 0;
       // int denNam = 2100;
        String gender = "Toan Bo";
        String status = "Toan Bo";
        if (accessCount != 0){
            gender = StringService.covertToString(gioiTinhCB.getSelectionModel().getSelectedItem());
            status = StringService.covertToString(tinhTrangCB.getSelectionModel().getSelectedItem());
        }
        accessCount++;
        try {
            if (!tuTuoi.getText().trim().isEmpty()) {
                tuTuoi = Integer.parseInt(tuTuoi.getText().trim());
            } else {
                min = -1;
            }
            if (!denTuoi.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(denTuoi.getText().trim());
            } else {
                max = 200;
            }


        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }
        listNhanKhauBeans = nhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status);
        setDataTable();
    }

    public void setDataTable() {
        List<NhanKhauModel> listItem = new ArrayList<>();
        listNhanKhauBeans.forEach(nhanKhau -> {
            listItem.add(nhanKhau.getNhanKhauModel());
        });
        observablelistNhanKhau = FXCollections.observableList(listItem);
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        table.setItems(observablelistNhanKhau);
    }
*/



}
