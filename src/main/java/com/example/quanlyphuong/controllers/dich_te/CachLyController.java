package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.CachLyBean;
import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.controllers.ho_khau.MainHoKhauController;
import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.services.CachLyService;
import com.example.quanlyphuong.services.HoKhauService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CachLyController implements Initializable {

    public static CachLyController frame;

    public CachLyController() {
        if (frame == null) {
            frame = this;
        } else {
//            super
//            return;
//            throw new RuntimeException("Singleton FXML");
        }
    }
    @FXML
    private TextField tfCCCD;

    @FXML
    private TextField tfHoTen;

    @FXML
    private TextField tfDiaDiem;

    @FXML
    private DatePicker tfStart;

    @FXML
    private DatePicker tfEnd;

    @FXML
    private TextField tfMucDo;

    @FXML
    private TableView<CachLyBean> tbvChiTiet;

    @FXML
    private TableColumn<CachLyBean, String> ID;

    @FXML
    private TableColumn<CachLyBean, String> HoTen;

    @FXML
    private TableColumn<CachLyBean, String> CCCD;

    @FXML
    private TableColumn<CachLyBean, String> DiaDiem;

    @FXML
    private TableColumn<CachLyBean, String> BatDau;

    @FXML
    private TableColumn<CachLyBean, String> KetThuc;

    @FXML
    private TableColumn<CachLyBean, Integer> MucDo;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnCapNhap;

    List<CachLyBean> listNhanKhauCachLy;
    CachLyService cachLyService;
    ObservableList<CachLyBean> observableListHoKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    NhanKhauBean nhanKhauCachLy;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void refresh(){
        cachLyService = new CachLyService();
        listNhanKhauCachLy = cachLyService.getListNhanKhauCachLy();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauCachLy);

        ID.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getMaNhanKhau()));
        HoTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        CCCD.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        DiaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getDiaDiemCachLy()));
        BatDau.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianBatDau()));
        MucDo.setCellValueFactory(bean-> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getMucDo()));
        KetThuc.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianKetThuc()));
        tbvChiTiet.setItems(observableListHoKhauBeans);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    @FXML
    void xoa(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Hãy nhấn OK để thực hiện lệnh xóa, nhấn hủy để hủy lệnh xóa!");
        alert.setHeaderText("Bạn có muốn xóa hàng này không?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // delete from database
            CachLyBean bean = tbvChiTiet.getSelectionModel().getSelectedItem();
            cachLyService = new CachLyService();
            cachLyService.deleteCachLy(bean);
            refresh();
            Alert thanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
            thanhCongAlert.setContentText("Xoa Thanh Cong");
            thanhCongAlert.show();
        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    @FXML
    void timNhanKhauBean(ActionEvent event) {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tfCCCD.getText();
        System.out.println(cmt);

        nhanKhauCachLy = thongKeNhanKhauService.getNhanKhau(cmt);
        if(nhanKhauCachLy == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Không tồn tại chứng minh thư ");
            alert.show();
        }else{
            tfHoTen.setText(nhanKhauCachLy.getNhanKhauModel().getHo_ten());
        }

    }
    boolean isMissingField(){
        if ( tfCCCD.getText().isBlank() || tfHoTen.getText().isEmpty() || tfDiaDiem.getText().isEmpty()
            || (tfStart.getValue() == null) || tfMucDo.getText().isEmpty() || (tfEnd.getValue() == null)){
            return true;
        }
        return false;

    }
    void clearTf(){
        tfCCCD.clear();
        tfHoTen.clear();
        tfDiaDiem.clear();
        tfMucDo.clear();
        tfStart.setValue(null);
        tfEnd.setValue(null);
    }

    @FXML
    void themCachLy(ActionEvent event) throws SQLException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        CachLyBean cachLyBean = new CachLyBean();
        cachLyService = new CachLyService();
        nhanKhauCachLy = thongKeNhanKhauService.getNhanKhau(tfCCCD.getText());

        for(CachLyBean cl: listNhanKhauCachLy){
            if(cl.getNhanKhauBean().getChungMinhThuModel().getSoCMT().equals(tfCCCD.getText())){
                Alert thongBaoTrung = new Alert(Alert.AlertType.WARNING);
                thongBaoTrung.setContentText("Người này hiện đang cách ly");
                thongBaoTrung.show();
                clearTf();
                return;
            }
        }

            if(isMissingField()){
                Alert missingAlert = new Alert(Alert.AlertType.WARNING);
                missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
                missingAlert.show();
            }

            CachLyModel cachLyModel = new CachLyModel();
            cachLyModel.setDiaDiemCachLy(tfDiaDiem.getText());
            cachLyModel.setMucDo(Integer.parseInt(tfMucDo.getText()));
            cachLyModel.setThoiGianBatDau(tfStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cachLyModel.setThoiGianKetThuc(tfEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            cachLyBean.setNhanKhauBean(nhanKhauCachLy);
            cachLyBean.setCachLyModel(cachLyModel);

            cachLyService.addCachLy(cachLyBean);

            refresh();
            clearTf();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setContentText("Thêm thành công");
            successAlert.show();


    }

    @FXML
    void setThongTin(MouseEvent event) {
        cachLyService = new CachLyService();
        CachLyBean bean = tbvChiTiet.getSelectionModel().getSelectedItem();

        tfHoTen.setText(bean.getNhanKhauBean().getNhanKhauModel().getHo_ten());
        tfCCCD.setText(bean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tfDiaDiem.setText(bean.getCachLyModel().getDiaDiemCachLy());
        tfStart.setValue(LocalDate.parse(bean.getCachLyModel().getThoiGianBatDau(),formatter));
        tfEnd.setValue(LocalDate.parse(bean.getCachLyModel().getThoiGianKetThuc(),formatter));
        tfMucDo.setText(String.valueOf(bean.getCachLyModel().getMucDo()));

        tfCCCD.setDisable(true);
        tfHoTen.setDisable(true);

        btnThem.setDisable(true);
    }

    @FXML
    void capNhap(ActionEvent event) throws SQLException {
        cachLyService = new CachLyService();
        CachLyBean cachLyBean = tbvChiTiet.getSelectionModel().getSelectedItem();
        String diadiem = tfDiaDiem.getText();
        int mucdo = Integer.parseInt(tfMucDo.getText());
        String start = tfStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String end = tfEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if(isMissingField()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
        }

        cachLyService.updateCachLy(cachLyBean, diadiem, start, end, mucdo);
        refresh();
    }

    @FXML
    void reFresh(ActionEvent event) {
        clearTf();
        tfHoTen.setDisable(false);
        tfCCCD.setDisable(false);
        btnThem.setDisable(false);

    }



}
