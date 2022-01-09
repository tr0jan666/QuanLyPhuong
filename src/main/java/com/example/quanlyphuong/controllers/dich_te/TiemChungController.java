package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.beans.TiemChungBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.helper.constants.MuiTiemConstant;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private TableColumn<TiemChungBean,String> tc_ngayLan1;
    @FXML
    private TableColumn<TiemChungBean,String> tc_loaiVaccine1;
    @FXML
    private TableColumn<TiemChungBean,String> tc_diaDiem;
    @FXML
    private TextField tf_diaDiem;
    @FXML
    private TextField tf_cccd;
    @FXML
    private ComboBox<String> cb_tiemLan;
    @FXML
    private TextField tf_loaiVaccine;
    @FXML
    private DatePicker dt_thoiGianTiem;
    @FXML
    private Button btn_capNhat;
    @FXML
    private Button btn_thongKe;

    @FXML
    private Label lbCheck;

    private TiemChungService tiemChungService;
    private ObservableList<TiemChungBean> tiemChungBeanObservableList;
    private List<TiemChungBean> tiemChungBeanList;
    NhanKhauBean nhanKhauTiemChung;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        tc_tiemLan1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getSoLanTiem()));
        tc_loaiVaccine1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getVacxin()));
        tc_ngayLan1.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getNgayTiem()));
        tc_diaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTiemChungModel().getDiaDiem()));

        tableTiemChung.setItems(tiemChungBeanObservableList);
        cb_tiemLan.setItems(FXCollections.observableList(MuiTiemConstant.LIST_LAN_TIEM));
        cb_tiemLan.setValue(MuiTiemConstant.LIST_LAN_TIEM.get(0));
    }

    boolean MissingFields(){
        if(tf_diaDiem.getText().isEmpty()||tf_cccd.getText().isEmpty()
                ||tf_loaiVaccine.getText().isEmpty()||(dt_thoiGianTiem.getValue() == null)){
            return true;
        }
        return false;
    }

    void clearInput(){
        tf_diaDiem.clear();
        tf_cccd.clear();
        cb_tiemLan.setValue(MuiTiemConstant.LIST_LAN_TIEM.get(0));
        tf_loaiVaccine.clear();
        dt_thoiGianTiem.setValue(null);
    }

    @FXML
    public void BtnThem(ActionEvent event){
        tiemChungService = new TiemChungService();
        TiemChungBean tiemChungBean = new TiemChungBean();
        ThongKeNhanKhauService thongKeNhanKhauService = new ThongKeNhanKhauService();
        nhanKhauTiemChung = thongKeNhanKhauService.getNhanKhau(tf_cccd.getText());

//        for(TiemChungBean tc : tiemChungBeanList){
//            if(tc.getNhanKhauBean().getChungMinhThuModel().getSoCMT().equals(tf_cccd.getText())){
//                if(tc.getTiemChungModel().getSoLanTiem() == Integer.parseInt(cb_tiemLan.getValue())){
//                    Alert thongBaoTrung = new Alert(Alert.AlertType.WARNING);
//                    thongBaoTrung.setContentText("Người này hiện đã tiêm mũi" + tf_tiemLan.getText());
//                    thongBaoTrung.show();
//                    clearInput();
//                    return;
//                }
//            }
//        }

        if(MissingFields()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
        }

        TiemChungModel tiemChungModelTemp = new TiemChungModel();
//        tiemChungModelTemp.setCCCD(tf_cccd.getText());
//        tiemChungModelTemp.setHoTen(tf_hoTen.getText());
        tiemChungModelTemp.setSoLanTiem(Integer.parseInt(cb_tiemLan.getValue()));
        tiemChungModelTemp.setNgayTiem(dt_thoiGianTiem.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tiemChungModelTemp.setVacxin(tf_loaiVaccine.getText());
        tiemChungModelTemp.setDiaDiem(tf_diaDiem.getText());
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
    void setThongTin(MouseEvent event) {
        tiemChungService = new TiemChungService();
        TiemChungBean tiemChungBean = tableTiemChung.getSelectionModel().getSelectedItem();

        tf_cccd.setText(tiemChungBean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tf_diaDiem.setText(tiemChungBean.getTiemChungModel().getDiaDiem());
        cb_tiemLan.setValue(String.valueOf(tiemChungBean.getTiemChungModel().getSoLanTiem()));
        tf_loaiVaccine.setText(tiemChungBean.getTiemChungModel().getVacxin());
        dt_thoiGianTiem.setValue(LocalDate.parse(tiemChungBean.getTiemChungModel().getNgayTiem(),formatter));

        tf_cccd.setDisable(true);
    }

//    @FXML
//    void setThongTinL1(MouseEvent event){
//        tiemChungService = new TiemChungService();
//        TiemChungBean tiemChungBean = tableTiemChung.getSelectionModel().getSelectedItem();
//        FXMLLoader fxmlLoader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/pop-up-tiem-chung.fxml"));
//        System.out.println((QuanLyNhanKhauApplication.class.getResource("dich_te/pop-up-tiem-chung.fxml")));
//        try {
//            Scene scene = new Scene(fxmlLoader.load(), DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
//            Stage stage = new Stage();
//            stage.setTitle("Chi tiết");
//            stage.setResizable(false);
//            stage.setScene(scene);
//            PopUpTiemChung popUpTiemChung = fxmlLoader.getController();
//            popUpTiemChung.setData(tiemChungBean);
//
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    void capNhat(ActionEvent event){
        tiemChungService = new TiemChungService();
        TiemChungBean tiemChungBean = tableTiemChung.getSelectionModel().getSelectedItem();
        String loaiVacxin = tf_loaiVaccine.getText();
        String thoiGianTiem = dt_thoiGianTiem.getValue().format(formatter);
        String diaDiem = tf_diaDiem.getText();
        int tiemLan = Integer.parseInt(cb_tiemLan.getValue());

        if(MissingFields()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
            return;
        }

        tiemChungService.updateTiemChung(tiemChungBean.getNhanKhauBean().getNhanKhauModel().getID() ,loaiVacxin,thoiGianTiem,diaDiem,tiemLan);
        setData();
        clearInput();
        tf_cccd.setDisable(false);
    }

    @FXML
    void btnCheckEvent(ActionEvent event){
        tiemChungService = new TiemChungService();
        ThongKeNhanKhauService thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tf_cccd.getText();
        lbCheck.setVisible(true);
        NhanKhauBean nahnKhauTiemChung = thongKeNhanKhauService.getNhanKhau(cmt);
        if(nahnKhauTiemChung == null || nahnKhauTiemChung.getNhanKhauModel() == null){
            lbCheck.setText("CCCD không tồn tại");
            lbCheck.setTextFill(Color.RED);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD! Bạn có muốn thêm nhân khẩu mới không?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK){
                UIHelper.navigateNew("nhan_khau/pop_up_them_nhan_khau.fxml", "Thêm nhân khẩu");
                return;
            } else {
                return;
            }
        }else{
            lbCheck.setText("OK");
            lbCheck.setTextFill(Color.GREEN);
        }

        int soLanTiem = tiemChungService.getTiemChung(tf_cccd.getText());
        if(soLanTiem == 3){
            cb_tiemLan.setValue("1");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Người này đã tiêm đủ 3 mũi !");
        }else{
            cb_tiemLan.setValue(String.valueOf(soLanTiem+1));
        }

    }

}
