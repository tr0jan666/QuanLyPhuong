package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class ThongKeController implements Initializable {

    @FXML
    private AnchorPane anchorpanetk;
    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnXuatFile;

    @FXML
    private TextField denNamText;

    @FXML
    private TextField denTuoiText;

    @FXML
    private TableColumn<?, ?> diaChiHienNay;

    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;

    @FXML
    private ComboBox<String> gioiTinhCB;

    @FXML
    private TableColumn<?, ?> hoTen;

    @FXML
    private TableColumn<?, ?> namSinh;

    @FXML
    private TableView<NhanKhauModel> table;

    @FXML
    private ComboBox<String> tinhTrangCB;

    @FXML
    private TextField tuNamText;

    @FXML
    private TextField tuTuoiText;

    MenuNhanKhauController sceneSwitch;
    List<NhanKhauBean> listNhanKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    ObservableList<NhanKhauModel> observablelistNhanKhau;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tinhTrangList;
    int accessCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneSwitch = new MenuNhanKhauController();
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        listNhanKhauBeans = thongKeNhanKhauService.getListNhanKhau();
         //System.out.println("phan nguyen init ");
        //setDataTable();
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
        int tuNam = 0;
        int denNam = 2100;
        String gender = "Toan Bo";
        String status = "Toan Bo";
        if (accessCount != 0){
            int gioiTinh = 0;
            if(gioiTinhCB.getValue().toString().equals("Nam")){
                gioiTinh = 1;
            }
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
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }

        listNhanKhauBeans = thongKeNhanKhauService.statisticNhanKhau(tuTuoi, denTuoi, gender, status, tuNam, denNam);
//        System.out.println("xong init data");

       setDataTable();
    }

    public void setDataTable() {
        ArrayList<NhanKhauModel> listItem = new ArrayList<>();
        listNhanKhauBeans.forEach(nhanKhau -> {
            listItem.add(nhanKhau.getNhanKhauModel());
        });
        observablelistNhanKhau = FXCollections.observableList(listItem);
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("Ho_ten"));
        namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiHienNay.setCellValueFactory(new PropertyValueFactory<>("diaChiHienNay"));
        table.setItems(observablelistNhanKhau);
    }

    public void setTimKiem() {
        setData();
    }

    @FXML
    void setXuatFile(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setTitle("Chọn nơi để lưu");
        Stage stage = (Stage) anchorpanetk.getScene().getWindow();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File filetosave =  fc.showSaveDialog(stage);
        if (filetosave != null) {
            try {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                FileWriter fw = new FileWriter(filetosave);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("ID"+"\t\t"+"Họ tên"+"\t\t"+"Năm sinh"+"\t\t"+"Giới tính"+"\t\t"+"Địa chỉ");
                bw.newLine();
                ArrayList<NhanKhauModel> listItem = new ArrayList<>();
                for (NhanKhauBean nhanKhau : listNhanKhauBeans) {
                    listItem.add(nhanKhau.getNhanKhauModel());
                    String s = formatter.format(nhanKhau.getNhanKhauModel().getNamSinh());
                    bw.write(String.valueOf(nhanKhau.getNhanKhauModel().getID())+"\t\t"+nhanKhau.getNhanKhauModel().getHo_ten()+"\t\t"+s+"\t\t"+nhanKhau.getNhanKhauModel().getGioiTinh()+"\t\t"+nhanKhau.getNhanKhauModel().getDiaChiHienNay());
                    bw.newLine();
                }


                bw.close();
            } catch (IOException e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Check");
                alert1.setContentText("Đã có lỗi xảy ra ,xin vui lòng thử lại !! ");
                alert1.show();
            }


        }
    }



}
