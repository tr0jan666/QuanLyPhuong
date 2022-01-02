package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.TestCovidBean;
import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.controllers.ho_khau.MainHoKhauController;
import com.example.quanlyphuong.models.TestCovidModel;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.services.TestCovidService;
import com.example.quanlyphuong.services.HoKhauService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TestCovidController implements Initializable{

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
    private TextField tf_cccd;

    @FXML
    private TextField tf_hoVaTen;

    @FXML
    private TextField tf_diaDiem;

    @FXML
    private DatePicker dp_thoGianTest;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_ketQua.setItems(FXCollections.observableArrayList( "Âm tính","Dương tính"));

    }

    List<TestCovidBean> listNhanKhauTestCovid;
    TestCovidService testCovidService;
    ObservableList<TestCovidBean> observableListHoKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    NhanKhauBean nhanKhauTiemChung;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void refresh(){
        testCovidService = new TestCovidService();
        listNhanKhauTestCovid = testCovidService.getListNhanKhauCachLy();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauCachLy);

        col_id.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getMaNhanKhau()));
        col_hoVaTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        col_cccd.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        //col_diaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTestCovidModel().get()));
        BatDau.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianBatDau()));
        MucDo.setCellValueFactory(bean-> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getMucDo()));
        KetThuc.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getCachLyModel().getThoiGianKetThuc()));
        tbvChiTiet.setItems(observableListHoKhauBeans);

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
        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }
}
