package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.constants.GioiTinhConstant;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import com.example.quanlyphuong.services.StringService;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThongKeController implements Initializable {

    @FXML
    private AnchorPane anchorpanetk;
    @FXML
    private TableColumn<NhanKhauBean, Integer> ID;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXuatFile;

    @FXML
    private TextField denNamText;

    @FXML
    private TextField denTuoiText;

    @FXML
    private TableColumn<NhanKhauBean, String> diaChiHienNay;

    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;

    @FXML
    private ComboBox<String> gioiTinhCB;

    @FXML
    private TableColumn<NhanKhauBean, String> hoTen;

    @FXML
    private TableColumn<NhanKhauBean, Date> namSinh;

    @FXML
    private TableView<NhanKhauBean> table;

    @FXML
    private ComboBox<String> tinhTrangCB;

    @FXML
    private TextField tuNamText;

    @FXML
    private TextField tuTuoiText;

    MenuNhanKhauController sceneSwitch;
    List<NhanKhauBean> listNhanKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    ObservableList<NhanKhauBean> observablelistNhanKhau;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tinhTrangList;
    int accessCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneSwitch = new MenuNhanKhauController();
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        listNhanKhauBeans = thongKeNhanKhauService.getListNhanKhau();

        setData();
        gioiTinhList = FXCollections.observableArrayList("To??n b???", "Nam", "N???");
        tinhTrangList = FXCollections.observableArrayList("To??n b???", "Th?????ng tr??", "T???m tr??", "T???m v???ng");
        gioiTinhCB.setItems(gioiTinhList);
        gioiTinhCB.getSelectionModel().selectFirst();
        tinhTrangCB.setItems(tinhTrangList);
        tinhTrangCB.getSelectionModel().selectFirst();

    }

    public void setData() {
        int tuTuoi = -1;
        int denTuoi = 200;
        int tuNam = 0;
        int denNam = 2100;
        String gender = "Toan Bo";
        String status = "Toan Bo";
        if (accessCount != 0) {

            gender = StringService.covertToString(gioiTinhCB.getSelectionModel().getSelectedItem());
            status = StringService.covertToString(tinhTrangCB.getSelectionModel().getSelectedItem());

        }
        accessCount++;
        try {
            if (!tuTuoiText.getText().trim().isEmpty()) {
                tuTuoi = Integer.parseInt(tuTuoiText.getText().trim());
            } else {
                tuTuoi = -1;
            }
            if (!denTuoiText.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(denTuoiText.getText().trim());
            } else {
                denTuoi = 200;
            }
            if (!tuNamText.getText().trim().isEmpty()) {
                tuNam = Integer.parseInt(tuNamText.getText().trim());
            }
            if (!denNamText.getText().trim().isEmpty()) {
                denNam = Integer.parseInt(denNamText.getText().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui l??ng nh???p ????ng ki???u d??? li???u");
            alert.show();
        }

        listNhanKhauBeans = thongKeNhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status, tuNam, denNam);
//        System.out.println("xong init data");

        setDataTable();
    }

    public void setDataTable() {
        observablelistNhanKhau = FXCollections.observableList(listNhanKhauBeans);
        ID.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getID()));
        hoTen.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        namSinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh()));
        gioiTinh.setCellValueFactory(nhanKhauBean ->
                new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinh() == GioiTinhConstant.NAM ? "Nam" : "N???"));
        diaChiHienNay.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        table.setItems(observablelistNhanKhau);
    }

    public void setTimKiem() {
        setData();
    }

    @FXML
    void setXuatFile(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("Ch???n n??i ????? l??u");
        Stage stage = (Stage) anchorpanetk.getScene().getWindow();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File filetosave = fc.showSaveDialog(stage);
        if (filetosave != null) {
            try {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                FileWriter fw = new FileWriter(filetosave);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("ID" + "\t" + "H??? t??n" + "\t" + "N??m sinh" + "\t" + "Gi???i t??nh" + "\t" + "?????a ch???");
                bw.newLine();
                ArrayList<NhanKhauModel> listItem = new ArrayList<>();
                for (NhanKhauBean nhanKhau : listNhanKhauBeans) {
                    listItem.add(nhanKhau.getNhanKhauModel());
                    String s = formatter.format(nhanKhau.getNhanKhauModel().getNamSinh());
                    bw.write(String.valueOf(nhanKhau.getNhanKhauModel().getID()) + "\t" + nhanKhau.getNhanKhauModel().getHo_ten() + "\t" + s + "\t" + nhanKhau.getNhanKhauModel().getGioiTinhString() + "\t" + nhanKhau.getNhanKhauModel().getDiaChiHienNay());
                    bw.newLine();
                }


                bw.close();
            } catch (IOException e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Check");
                alert1.setContentText("???? c?? l???i x???y ra ,xin vui l??ng th??? l???i !! ");
                alert1.show();
            }


        }
    }


}
