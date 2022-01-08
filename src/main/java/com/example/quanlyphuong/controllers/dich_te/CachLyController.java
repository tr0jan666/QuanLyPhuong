package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.CachLyBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.CommonUtils;
import com.example.quanlyphuong.helper.constants.MucDoCachLyConstant;
import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.CachLyService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    private DatePicker dpStart;

    @FXML
    private DatePicker dpEnd;

    @FXML
    private ComboBox<String> cbMucDo;

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
    private TableColumn<CachLyBean,
            String> MucDo;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnCapNhap;

    @FXML
    private Button btnCheckCCCD;


    List<CachLyBean> listNhanKhauCachLy;
    CachLyService cachLyService;
    ObservableList<CachLyBean> observableListHoKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    NhanKhauBean nhanKhauCachLy;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void refresh() {
        cachLyService = new CachLyService();
        listNhanKhauCachLy = cachLyService.getListNhanKhauCachLy();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauCachLy);

        ID.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getMaNhanKhau()));
        HoTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        CCCD.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        DiaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getDiaDiemCachLy()));
        BatDau.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianBatDau()));
        MucDo.setCellValueFactory(bean -> {
            String mucDoString = CommonUtils.getMucDoString(bean.getValue().getCachLyModel().getMucDo());
            return new ReadOnlyObjectWrapper<String>(mucDoString);
        });
        KetThuc.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianKetThuc()));
        tbvChiTiet.setItems(observableListHoKhauBeans);

        cbMucDo.setItems(FXCollections.observableList(MucDoCachLyConstant.LIST_MUC_DO_STRING));
        cbMucDo.setValue(MucDoCachLyConstant.LIST_MUC_DO_STRING.get(0));

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
            reFreshThongTin(event);
        } else if (result.get() == ButtonType.CANCEL) {
            reFreshThongTin(event);
            alert.close();
        }

    }

    @FXML
    void checkCCCD(ActionEvent event) throws IOException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tfCCCD.getText();
        System.out.println(cmt);

        nhanKhauCachLy = thongKeNhanKhauService.getNhanKhau(cmt);
        if (nhanKhauCachLy == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD! Bạn có muốn thêm nhân khẩu mới không?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quanlyphuong/nhan_khau/pop_up_them_nhan_khau.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
                return;
            } else {
                return;
            }


        } else {
            tfHoTen.setText(nhanKhauCachLy.getNhanKhauModel().getHo_ten());
        }


    }

    @FXML
    void timNhanKhauBean(ActionEvent event) {

    }

    boolean isValidateWrong() {
        //validate thoi gian
        if (dpStart.getValue().isAfter(dpEnd.getValue()))
            return true;
        if (tfCCCD.getText().isBlank() || tfHoTen.getText().isEmpty() || tfDiaDiem.getText().isEmpty()
                || (dpStart.getValue() == null) ||
//                tfMucDo.getText().isEmpty() ||
                (dpEnd.getValue() == null)) {
            return true;
        }
        return false;

    }

    void clearTf() {
        tfCCCD.clear();
        tfHoTen.clear();
        tfDiaDiem.clear();
//        tfMucDo.clear();
        dpStart.setValue(null);
        dpEnd.setValue(null);
    }

    @FXML
    void themCachLy(ActionEvent event) throws SQLException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        CachLyBean cachLyBean = new CachLyBean();
        cachLyService = new CachLyService();
        nhanKhauCachLy = thongKeNhanKhauService.getNhanKhau(tfCCCD.getText());

        for (CachLyBean cl : listNhanKhauCachLy) {
            if (cl.getNhanKhauBean().getChungMinhThuModel().getSoCMT().equals(tfCCCD.getText())) {
                Alert thongBaoTrung = new Alert(Alert.AlertType.WARNING);
                thongBaoTrung.setContentText("Người này hiện đang cách ly");
                thongBaoTrung.show();
                clearTf();
                return;
            }
        }

        if (isValidateWrong()) {
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ và chính xác thông tin thông tin");
            missingAlert.show();
        } else {

            CachLyModel cachLyModel = new CachLyModel();
            cachLyModel.setDiaDiemCachLy(tfDiaDiem.getText());
//        cachLyModel.setMucDo(Integer.parseInt(tfMucDo.getText()));
            cachLyModel.setMucDo(CommonUtils.getMucDoInt(cbMucDo.getValue()));

            cachLyModel.setThoiGianBatDau(dpStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cachLyModel.setThoiGianKetThuc(dpEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            cachLyBean.setNhanKhauBean(nhanKhauCachLy);
            cachLyBean.setCachLyModel(cachLyModel);

            Alert alertCn = new Alert(Alert.AlertType.CONFIRMATION);
            alertCn.setContentText("Bạn có thêm người cách ly");
            Optional<ButtonType> confirmCn = alertCn.showAndWait();

            if (confirmCn.get() == ButtonType.OK) {
                SimpleResult rs = cachLyService.addCachLy(cachLyBean);
                if (rs.isSuccess()) {
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setContentText("Thêm thành công");


                    reFreshThongTin(event);
                    alertSuccess.show();
                    refresh();
                } else {
                    Alert alertFailed = new Alert(Alert.AlertType.INFORMATION);
                    alertFailed.setContentText(rs.getMessage());
                    alertFailed.show();

                    reFreshThongTin(event);
                    refresh();
                }
            } else {
                // khong reset thong tin khi user cancel confirm

//                reFreshThongTin(event);
                return;
            }
        }


    }

    @FXML
    void setThongTin(MouseEvent event) {
        cachLyService = new CachLyService();
        CachLyBean bean = tbvChiTiet.getSelectionModel().getSelectedItem();

        tfHoTen.setText(bean.getNhanKhauBean().getNhanKhauModel().getHo_ten());
        tfCCCD.setText(bean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tfDiaDiem.setText(bean.getCachLyModel().getDiaDiemCachLy());
        dpStart.setValue(LocalDate.parse(bean.getCachLyModel().getThoiGianBatDau(), formatter));
        dpEnd.setValue(LocalDate.parse(bean.getCachLyModel().getThoiGianKetThuc(), formatter));
//        tfMucDo.setText(String.valueOf(bean.getCachLyModel().getMucDo()));
        cbMucDo.setValue(CommonUtils.getMucDoString(bean.getCachLyModel().getMucDo()));
        tfCCCD.setDisable(true);
        tfHoTen.setDisable(true);

        btnThem.setDisable(true);
    }

    @FXML
    void capNhap(ActionEvent event) throws SQLException {
        cachLyService = new CachLyService();
        CachLyBean cachLyBean = tbvChiTiet.getSelectionModel().getSelectedItem();
        String diadiem = tfDiaDiem.getText();
        int mucdo = CommonUtils.getMucDoInt(cbMucDo.getValue());
        String start = dpStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String end = dpEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("start: " + start);
        System.out.println("end: " + end);

        if (isValidateWrong()) {
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ và chính xác thông tin");
            missingAlert.show();
        }
        else {
            Alert alertCn = new Alert(Alert.AlertType.CONFIRMATION);
            alertCn.setContentText("Bạn có muốn cập nhập");
            Optional<ButtonType> confirmCn = alertCn.showAndWait();
            if (confirmCn.get() == ButtonType.OK) {
                SimpleResult rs = cachLyService.updateCachLy(cachLyBean.getNhanKhauBean().getNhanKhauModel().getID(), diadiem, start, end, mucdo);
                if (rs.isSuccess()) {
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setContentText("Cập nhập thành công");


                    reFreshThongTin(event);
                    alertSuccess.show();
                    refresh();
                } else {
                    Alert alertFailed = new Alert(Alert.AlertType.INFORMATION);
                    alertFailed.setContentText(rs.getMessage());
                    alertFailed.show();


                    reFreshThongTin(event);
                    refresh();
                }
            } else {
                // khong reset thong tin khi user cancel confirm
//            reFreshThongTin(event);
                return;
            }
        }




    }

    @FXML
    void reFreshThongTin(ActionEvent event) {
        clearTf();
        tfHoTen.setDisable(false);
        tfCCCD.setDisable(false);
        btnThem.setDisable(false);

    }


}
