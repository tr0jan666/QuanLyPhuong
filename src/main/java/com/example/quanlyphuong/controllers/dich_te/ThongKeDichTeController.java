package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.StringService;
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
import java.util.Date;
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
    private ComboBox<String> ketQuaCovidCB;

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
    private TableColumn<NhanKhauBean, Integer> col_tiemLan;

    @FXML
    private TableColumn<NhanKhauBean, Date> col_ngayTiemLan;

    @FXML
    private TableColumn<NhanKhauBean, String> col_loaiVaccineLan;

   /* @FXML
    private TableColumn<NhanKhauBean, Integer> col_tiemLan2;

    @FXML
    private TableColumn<NhanKhauBean, Date> col_ngayTiemLan2;

    @FXML
    private TableColumn<NhanKhauBean, String> col_loaiVaccineLan2;*/

    @FXML
    private ComboBox<String> tiemChungCB;

    @FXML
    private TableColumn<NhanKhauBean, String> col_cachLy;

    @FXML
    private TableColumn<NhanKhauBean, String> col_covid;

    List<NhanKhauBean> listNhanKhauCovidBeans;
    ThongKeCovidService thongKeCovidService;
    ObservableList<NhanKhauBean> observablelistNhanKhauCovid;
    ObservableList<String> gioiTinhList;
    ObservableList<String> tiemChungList;
    ObservableList<String> ketQuaCovidList;
    int accessCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        thongKeCovidService = new ThongKeCovidService();
        listNhanKhauCovidBeans = thongKeCovidService.getListNhanKhau();
        setData();
        gioiTinhList = FXCollections.observableArrayList("Toàn bộ", "Nam", "Nữ");
        gioiTinhCB.setItems(gioiTinhList);
        gioiTinhCB.getSelectionModel().selectFirst();
        tiemChungList = FXCollections.observableArrayList("Toàn bộ", "Đã tiêm mũi 1", "Đã tiêm mũi 2");
        tiemChungCB.setItems(tiemChungList);
        tiemChungCB.getSelectionModel().selectFirst();
        ketQuaCovidList = FXCollections.observableArrayList("Toàn bộ", "Dương tính", "Âm tính");
        ketQuaCovidCB.setItems(ketQuaCovidList);
        ketQuaCovidCB.getSelectionModel().selectFirst();

    }

    public void setData(){
        int tuTuoi = -1;
        int denTuoi = 200;
        int cachly = 0;
        String gender = "Toan Bo" ;
        String status = "Toan Bo";
        String testCovid = "Toan Bo";

        if (accessCount != 0){
            gender = StringService.covertToString(gioiTinhCB.getSelectionModel().getSelectedItem());
            status = StringService.covertToString(tiemChungCB.getSelectionModel().getSelectedItem());
            testCovid = StringService.covertToString(ketQuaCovidCB.getSelectionModel().getSelectedItem());

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



        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Vui lòng nhập đúng kiểu dữ liệu");
            alert.show();
        }

        listNhanKhauCovidBeans = thongKeCovidService.statisticNhanKhau(tuTuoi, denTuoi, gender, cachly, testCovid, status);


        setDataTable();
    }

    private void setDataTable() {

        observablelistNhanKhauCovid = FXCollections.observableList(listNhanKhauCovidBeans);
        col_hoTen.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        col_cccd.setCellValueFactory((nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT())));
        col_gioiTinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinhString()));
        col_diaChi.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));

        col_tiemLan.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getSoLanTiem()));
        col_ngayTiemLan.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getNgayTiem()));
        col_loaiVaccineLan.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getVacxin()));
        /*col_tiemLan2.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getSoLanTiem()));
        col_ngayTiemLan2.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getNgayTiem()));
        col_loaiVaccineLan2.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getTiemChungModel().getVacxin()));*/
        col_cachLy.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getCachLyModel().getMucDoString()));
        col_covid.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper(nhanKhauBean.getValue().getTestCovidModel().getKetQua()));

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
                bw.write("Họ tên"+"\t"+"CCCD"+"\t"+"Giới tính"+"\t"+"Địa chỉ"+"\t"+"Tiêm lần"+"\t"+"Ngày"+"\t"+"Vaccine"+"\t"+"Tình trạng cách ly"+"\t"+"Covid");
                bw.newLine();
                ArrayList<NhanKhauModel> listItem = new ArrayList<>();
                for (NhanKhauBean nhanKhau : listNhanKhauCovidBeans) {
                    listItem.add(nhanKhau.getNhanKhauModel());
                  //  String s = formatter.format(nhanKhau.getNhanKhauModel().getNamSinh());
                    bw.write(nhanKhau.getNhanKhauModel().getHo_ten()+"\t"+nhanKhau.getChungMinhThuModel().getSoCMT()+"\t"+nhanKhau.getNhanKhauModel().getGioiTinhString()+"\t"+nhanKhau.getNhanKhauModel().getDiaChiHienNay()+"\t");
                    bw.write(nhanKhau.getTiemChungModel().getSoLanTiem()+"\t"+nhanKhau.getTiemChungModel().getNgayTiem()+"\t"+nhanKhau.getTiemChungModel().getVacxin()+"\t");
                    bw.write(nhanKhau.getCachLyModel().getMucDoString()+"\t"+nhanKhau.getTestCovidModel().getKetQua());
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
