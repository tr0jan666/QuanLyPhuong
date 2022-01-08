package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThemMoiController implements Initializable {

    @FXML
    private TextField tfMaHoKhau;
    @FXML
    private TextField tfMaKhuVuc;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfTenChuHo;

    @FXML
    private TextField tfNgaySinhChuHo;

    @FXML
    private TextField tfCMTChuHo;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private Button btnXoaThanhVien;

    @FXML
    private TableView<MemOfFamily> tbvThanhVienCuaHo;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenThanhVien;

    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhThanhVien;

    @FXML
    private TableColumn<MemOfFamily, String> quanHeVoiChuHo;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnChonChuHo;

    ObservableList<MemOfFamily> memOfFamilyObservableList;
    List<MemOfFamily> memOfFamilyList;
    NhanKhauBean selectedNhanKhau;

    @FXML
    void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainHoKhauController.frame.refreshScreen();
        stage.hide();
    }

    @FXML
    public void luu(ActionEvent event) throws SQLException, ClassNotFoundException {

        HoKhauBean hoKhauBean = new HoKhauBean();
        //Set thong tin ho khau
        HoKhauModel hoKhau = new HoKhauModel();
        hoKhau.setMaHoKhau(tfMaHoKhau.getText());
        hoKhau.setMaKhuVuc(tfMaKhuVuc.getText());
        hoKhau.setDiaChi(tfDiaChi.getText());
        hoKhau.setIdChuHo(selectedNhanKhau.getNhanKhauModel().getID());
        hoKhauBean.setHoKhauModel(hoKhau);
        //set thong tin chu ho
        NhanKhauModel chuHo = selectedNhanKhau.getNhanKhauModel();
        hoKhauBean.setChuHo(chuHo);
        //set thanh vien cua ho
        List<ThanhVienCuaHoModel> listThanhVienCuaHo = new ArrayList<>();

        memOfFamilyObservableList.stream().forEach(memOfFamily -> {
            listThanhVienCuaHo.add(memOfFamily.getThanhVienCuaHoModel());
        });
        hoKhauBean.setListThanhVienCuaHo(listThanhVienCuaHo);
        new HoKhauService().addNew(hoKhauBean);
        MainHoKhauController.frame.refreshScreen();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Tạo mới thành công");
        alert.show();
        huy(event);
    }


    @FXML
    void themThanhVien(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/quanlyphuong/ho_khau/Sua.fxml"));
        Parent parent = loader.load();
        stage.getIcons().add(new Image(QuanLyNhanKhauApplication.class.getResourceAsStream("app_logo.png")));
        Scene scene = new Scene(parent);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        addDataToTable();
    }

    public void addDataToTable(){
        ThanhVienHoHolder thanhVienHoHolder = ThanhVienHoHolder.getInstance();
        memOfFamilyObservableList.addAll(thanhVienHoHolder.getMemOfFamilyObservableList());
        List<MemOfFamily> list = new ArrayList<>(memOfFamilyObservableList);
        System.out.println(list.get(0).getNhanKhau().getNhanKhauModel().getHo_ten());
        tbvThanhVienCuaHo.refresh();
    }

    @FXML
    void xoaThanhVienDaChon(ActionEvent event) {
        MemOfFamily selectedMemOfFamily = tbvThanhVienCuaHo.getSelectionModel().getSelectedItem();
        memOfFamilyObservableList.remove(selectedMemOfFamily);
    }


    @FXML
    void chonChuHo(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/quanlyphuong/ho_khau/chon_chu_ho.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.getIcons().add(new Image(QuanLyNhanKhauApplication.class.getResourceAsStream("app_logo.png")));
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(stage);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        setDataChuHo();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetChuHo();
        memOfFamilyList = new ArrayList<>();
        memOfFamilyObservableList = FXCollections.observableList(memOfFamilyList);
        hoTenThanhVien.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHo_ten()));
        ngaySinhThanhVien.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHo.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        tbvThanhVienCuaHo.setItems(memOfFamilyObservableList);
    }

    public void setDataChuHo(){
        selectedNhanKhau = ChuHoHolder.getInstance().getNhanKhauBean();
        if(selectedNhanKhau == null) return;
        System.out.println(selectedNhanKhau.getNhanKhauModel().getHo_ten());
        tfTenChuHo.setText(selectedNhanKhau.getNhanKhauModel().getHo_ten());
        tfNgaySinhChuHo.setText(selectedNhanKhau.getNhanKhauModel().getNamSinh().toString());
        tfCMTChuHo.setText(selectedNhanKhau.getChungMinhThuModel().getSoCMT());
    }

    public void resetChuHo(){
        tfTenChuHo.setText("");
        tfNgaySinhChuHo.setText("");
        tfCMTChuHo.setText("");
    }
}
