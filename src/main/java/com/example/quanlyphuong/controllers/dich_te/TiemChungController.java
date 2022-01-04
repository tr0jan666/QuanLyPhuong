package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.beans.TiemChungBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.AppScreen;
import com.example.quanlyphuong.models.TiemChungModel;
import com.example.quanlyphuong.services.NhanKhauService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import com.example.quanlyphuong.services.TiemChungService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.quanlyphuong.helper.UIHelper.DEFAULT_SCREEN_HEIGHT;
import static com.example.quanlyphuong.helper.UIHelper.DEFAULT_SCREEN_WIDTH;

public class TiemChungController implements Initializable {
    @FXML
    private TableView<TiemChungBean> tableTiemChung;
    @FXML
    private TableColumn<TiemChungBean,String> tc_cccd;
    @FXML
    private TableColumn<TiemChungBean,String> tc_hoVaTen;
    @FXML
    private TableColumn<TiemChungBean,Integer> tc_tiemLan1;
    @FXML
    private TableColumn<TiemChungBean,Date> tc_ngayLan1;
    @FXML
    private TableColumn<TiemChungBean,String> tc_loaiVaccine1;
    @FXML
    private TableColumn<TiemChungBean,Integer> tc_tiemLan2;
    @FXML
    private TableColumn<TiemChungBean,Date> tc_ngayLan2;
    @FXML
    private TableColumn<TiemChungBean,String> tc_loaiVaccine2;
    @FXML
    private TextField tf_hoTen;
    @FXML
    private TextField tf_cccd;
    @FXML
    private TextField tf_tiemLan;
    @FXML
    private TextField tf_loaiVaccine;
    @FXML
    private DatePicker dt_thoiGianTiem;
    @FXML
    private Button btn_capNhat;
    @FXML
    private Button btn_thongKe;

    private TiemChungService tiemChungService;
    private ObservableList<TiemChungBean> tiemChungBeanObservableList;
    private List<TiemChungBean> tiemChungBeanList;
    NhanKhauBean nhanKhauTiemChung;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    public void setData(){
        tiemChungService = new TiemChungService();
        tiemChungBeanList = tiemChungService.getListTiemChung();
        tiemChungBeanObservableList = FXCollections.observableList(tiemChungBeanList);

        tc_hoVaTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        tc_cccd.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        if(tc_tiemLan1.getText() == null){
            tc_tiemLan1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getSoLanTiem()));
            tc_loaiVaccine1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getVacxin()));
            tc_ngayLan1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getNgayTiem()));
        }else{
            tc_tiemLan2.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getSoLanTiem()));
            tc_loaiVaccine2.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getVacxin()));
            tc_ngayLan2.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getNgayTiem()));
        }
        tableTiemChung.setItems(tiemChungBeanObservableList);
    }

    boolean MissingFields(){
        if(tf_hoTen.getText().isEmpty()||tf_cccd.getText().isEmpty()
                ||tf_tiemLan.getText().isEmpty()||tf_loaiVaccine.getText().isEmpty()||(dt_thoiGianTiem.getValue() == null)){
            return true;
        }
        return false;
    }

    void clearInput(){
        tf_hoTen.clear();
        tf_cccd.clear();
        tf_tiemLan.clear();
        tf_loaiVaccine.clear();
        dt_thoiGianTiem.setValue(null);
    }

    @FXML
    public void BtnThem(ActionEvent event){
        tiemChungService = new TiemChungService();
        TiemChungBean tiemChungBean = new TiemChungBean();
        ThongKeNhanKhauService thongKeNhanKhauService = new ThongKeNhanKhauService();
        nhanKhauTiemChung = thongKeNhanKhauService.getNhanKhau(tf_cccd.getText());

        if(MissingFields()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
        }

        TiemChungModel tiemChungModelTemp = new TiemChungModel();
        tiemChungModelTemp.setCCCD(tf_cccd.getText());
        tiemChungModelTemp.setHoTen(tf_hoTen.getText());
        tiemChungModelTemp.setSoLanTiem(Integer.parseInt(tf_tiemLan.getText()));
        tiemChungModelTemp.setNgayTiem(Date.from(dt_thoiGianTiem.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        tiemChungModelTemp.setVacxin(tf_loaiVaccine.getText());

        tiemChungBean.setTiemChungModel(tiemChungModelTemp);
        tiemChungBean.setNhanKhauBean(nhanKhauTiemChung);

        tiemChungService.addTiemChung(tiemChungBean);

        setData();
        clearInput();

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Thêm thành công");
        successAlert.show();
    }

    @FXML
    void setThongTinL1(MouseEvent event){
        tiemChungService = new TiemChungService();
        TiemChungBean tiemChungBean = tableTiemChung.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/pop-up-tiem-chung.fxml"));
        System.out.println((QuanLyNhanKhauApplication.class.getResource("dich_te/pop-up-tiem-chung.fxml")));
        try {
            Scene scene = new Scene(fxmlLoader.load(), DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
            Stage stage = new Stage();
            stage.setTitle("Chi tiết");
            stage.setResizable(false);
            stage.setScene(scene);
            PopUpTiemChung popUpTiemChung = fxmlLoader.getController();
            popUpTiemChung.setData(tiemChungBean);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void capNhat(ActionEvent actionEvent) {

    }

//    @FXML
//    void capNhat(ActionEvent event){
//        tiemChungService = new TiemChungService();
//        TiemChungBean tiemChungBean = tableTiemChung.getSelectionModel().getSelectedItem();
//        String loaiVacxin = tf_loaiVaccine.getText();
//        String thoiGianTiem = dt_thoiGianTiem.getValue().format(formatter);
//
//        if(MissingFields()){
//            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
//            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
//            missingAlert.show();
//        }
//
//        tiemChungService.updateTiemChung(tiemChungBean,loaiVacxin,thoiGianTiem);
//        setData();
//    }
}
