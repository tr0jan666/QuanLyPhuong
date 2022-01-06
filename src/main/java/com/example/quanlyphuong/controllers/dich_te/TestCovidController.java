package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.TestCovidBean;

import com.example.quanlyphuong.beans.NhanKhauBean;

import com.example.quanlyphuong.helper.constants.KetQuaTestConstant;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TestCovidModel;
import com.example.quanlyphuong.services.StringService;
import com.example.quanlyphuong.services.TestCovidService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestCovidController implements Initializable {

    public static TestCovidController frame;

    public TestCovidController() {
        if (frame == null) {
            frame = this;
        } else {
//            super
//            return;
//            throw new RuntimeException("Singleton FXML");
        }
    }


    @FXML
    private Button btn_Them;

    @FXML
    private Button btn_Xoa;

    @FXML
    private Button btn_check;

    @FXML
    private TextField tf_cccd;

    @FXML
    private TextField tf_hoVaTen;

    @FXML
    private TextField tf_diaDiem;

    @FXML
    private DatePicker dp_thoiGianTest;

    @FXML
    public ComboBox<String> cb_ketQua;

    @FXML
    private TableView<TestCovidBean> tbv_testCovid;

    @FXML
    private TableColumn<TestCovidBean, Integer> col_id;

    @FXML
    private TableColumn<TestCovidBean, String> col_hoVaTen;

    @FXML
    private TableColumn<TestCovidBean, String> col_cccd;

    @FXML
    private TableColumn<TestCovidBean, String> col_diaDiem;

    @FXML
    private TableColumn<TestCovidBean, String> col_thoiGian;

    @FXML
    private TableColumn<TestCovidBean, String> col_ketQua;



    List<TestCovidBean> listNhanKhauTestCovid;
    TestCovidService testCovidService;
    ObservableList<TestCovidBean> observableListHoKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    NhanKhauBean nhanKhauTestCovid;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_ketQua.setItems(FXCollections.observableArrayList( "Âm tính", "Dương tính"));
        refresh();
    }



    public void refresh() {
        testCovidService = new TestCovidService();
        listNhanKhauTestCovid = testCovidService.getListNhanKhauTestCovid();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauTestCovid);

        col_id.setCellValueFactory(bean -> new ReadOnlyObjectWrapper(bean.getValue().getTestCovidModel().getIDTest()));
        col_hoVaTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        col_cccd.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        col_diaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTestCovidModel().getDiaDiemTest()));
        col_thoiGian.setCellValueFactory(bean -> new ReadOnlyObjectWrapper( bean.getValue().getTestCovidModel().getThoiDiemTest()));
        col_ketQua.setCellValueFactory(bean -> {
            int ketQua = bean.getValue().getTestCovidModel().getKetQua();
            String ketQuaString;
            if(ketQua == KetQuaTestConstant.AM_TINH)
            {
                ketQuaString = "Âm tính";
            }
            else
            {
                ketQuaString = "Dương tính";
            }

            return new ReadOnlyStringWrapper(ketQuaString);
        });
        tbv_testCovid.setItems(observableListHoKhauBeans);

    }



    boolean isMissingField() {
        if (tf_cccd.getText().isBlank() || tf_hoVaTen.getText().isEmpty() || tf_diaDiem.getText().isEmpty()
                || (dp_thoiGianTest.getValue() == null) || cb_ketQua.getItems().isEmpty()) {
            return true;
        }
        return false;

    }

    void clearTf() {
        tf_cccd.clear();
        tf_hoVaTen.clear();
        tf_diaDiem.clear();
        dp_thoiGianTest.setValue(null);
        cb_ketQua.setValue(null);
    }

    @FXML
    void themTestCovid(ActionEvent event) throws SQLException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        TestCovidBean testCovidBean = new TestCovidBean();
        testCovidService = new TestCovidService();
        nhanKhauTestCovid = thongKeNhanKhauService.getNhanKhau(tf_cccd.getText());


        if (isMissingField()) {
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
        }

        TestCovidModel testCovidModel = new TestCovidModel();
        testCovidModel.setDiaDiemTest(tf_diaDiem.getText());
        testCovidModel.setThoiDiemTest(dp_thoiGianTest.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (cb_ketQua.getValue() == "Âm tính") {
            testCovidModel.setKetQua(0);
        } else if (cb_ketQua.getValue() == "Dương tính") {
            testCovidModel.setKetQua(1);
        }
        testCovidBean.setNhanKhauBean(nhanKhauTestCovid);
        testCovidBean.setTestCovidModel(testCovidModel);


        Alert alertCn = new Alert(Alert.AlertType.CONFIRMATION);
        alertCn.setContentText("Bạn có thêm người cách ly");
        Optional<ButtonType> confirmCn = alertCn.showAndWait();

        if (confirmCn.get() == ButtonType.OK) {
            SimpleResult rs = testCovidService.addTestCovid(testCovidBean);
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
            reFreshThongTin(event);
            return;
        }
    }

    @FXML
    void checkCCCD(ActionEvent event) throws IOException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tf_cccd.getText();
        System.out.println(cmt);

        nhanKhauTestCovid = thongKeNhanKhauService.getNhanKhau(cmt);
        if(nhanKhauTestCovid == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD! Bạn có muốn thêm nhân khẩu mới không?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK){
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


        }
        else{
            tf_hoVaTen.setText(nhanKhauTestCovid.getNhanKhauModel().getHo_ten());
        }


    }

    @FXML
    void reFreshThongTin(ActionEvent event) {
        clearTf();
        tf_cccd.setDisable(false);
        tf_diaDiem.setDisable(false);
        tf_hoVaTen.setDisable(false);
        btn_Them.setDisable(false);
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
            TestCovidBean bean = tbv_testCovid.getSelectionModel().getSelectedItem();
            testCovidService = new TestCovidService();
            testCovidService.deleteTestCovid(bean);
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
}
