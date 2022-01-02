package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.ThongKeCovidService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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



public class ThongKeDichTeController implements Initializable {

    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private ComboBox<String> gioiTinhCB;

    @FXML
    private TextField tuTuoiText;

    @FXML
    private TextField denTuoiText;

    @FXML
    private CheckBox cachLyCB;

    @FXML
    private CheckBox mui2CB;

    @FXML
    private CheckBox mui1CB;

    @FXML
    private Button btnTimKiemThongkeDT;

    @FXML
    private Button btnXuatFile;

    @FXML
    private TableView<NhanKhauBean> table;

    @FXML
    private TableColumn<NhanKhauBean, String> col_hoTen;

    @FXML
    private TableColumn<NhanKhauBean, String> col_cccd;

    @FXML
    private TableColumn<NhanKhauBean, String> col_gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> col_diaChi;

    @FXML
    private TableColumn<?, ?> col_tiemLan1;

    @FXML
    private TableColumn<?, ?> col_ngayTiemLan1;

    @FXML
    private TableColumn<?, ?> col_loaiVaccineLan1;

    @FXML
    private TableColumn<?, ?> col_tiemLan2;

    @FXML
    private TableColumn<?, ?> col_ngayTiemLan2;

    @FXML
    private TableColumn<?, ?> col_loaiVaccineLan2;

    @FXML
    private TableColumn<?, ?> col_cachLy;

    @FXML
    private TableColumn<?, ?> col_covid;
    List<NhanKhauBean> listNhanKhauCovidBeans;
    ThongKeCovidService thongKeCovidService;
    ObservableList<NhanKhauBean> observablelistNhanKhauCovid;
    ObservableList<String> gioiTinhList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        thongKeCovidService = new ThongKeCovidService();
        listNhanKhauCovidBeans = thongKeCovidService.getListNhanKhau();
        setData();
        gioiTinhList = FXCollections.observableArrayList("Toàn bộ", "Nam", "Nữ");
        gioiTinhCB.setItems(gioiTinhList);
        gioiTinhCB.getSelectionModel().selectFirst();


    }

    public void setData(){
        int tuTuoi = -1;
        int denTuoi = 200;
        int cachly = 0, tiemmui1 =0,tiemmui2=0;
        int gioiTinh1 = -1 ;
        String status = "Toan Bo";
        int accessCount =0;
        if (accessCount != 0){
             gioiTinh1 = 0;
            if(gioiTinhCB.getValue().toString().equals("Nam")){
                gioiTinh1 = 1;
            }


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
            if(cachLyCB.isSelected()) cachly = 1;
            if(mui1CB.isSelected()) tiemmui1 = 1;
            if(mui2CB.isSelected()) tiemmui2 = 1;

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }

        listNhanKhauCovidBeans = thongKeCovidService.statisticNhanKhau(tuTuoi, denTuoi, gioiTinh1, cachly, tiemmui1,tiemmui2);
//        System.out.println("xong init data");

        setDataTable();
    }

    private void setDataTable() {

        observablelistNhanKhauCovid = FXCollections.observableList(listNhanKhauCovidBeans);
        col_hoTen.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        col_cccd.setCellValueFactory((nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT())));
        col_gioiTinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinhString()));
        col_diaChi.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        table.setItems(observablelistNhanKhauCovid);

    }


    @FXML
    void setTimKiemThongKeDT(ActionEvent event) {
        setData();
    }

    @FXML
    void setXuatFileThongke(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Chọn nơi để lưu");
        Stage stage = (Stage) anchorpane1.getScene().getWindow();
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
                for (NhanKhauBean nhanKhau : listNhanKhauCovidBeans) {
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
