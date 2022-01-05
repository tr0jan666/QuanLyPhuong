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
    private Date dp_thoiGianTest;

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
    NhanKhauBean nhanKhauTestCovid;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void refresh(){
        testCovidService = new TestCovidService();
        listNhanKhauTestCovid = testCovidService.getListNhanKhauTestCovid();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauTestCovid);

        col_id.setCellValueFactory(bean -> new ReadOnlyObjectWrapper(bean.getValue().getNhanKhauBean().getNhanKhauModel().getMaNhanKhau()));
        col_hoVaTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        col_cccd.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        col_diaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getTestCovidModel().getDiaDiemTest()));
        col_thoiGian.setCellValueFactory(bean -> new ReadOnlyObjectWrapper(bean.getValue().getTestCovidModel().getThoiDiemTest()));
        col_ketQua.setCellValueFactory(bean-> new ReadOnlyObjectWrapper(bean.getValue().getTestCovidModel().getKetQua()));
        tbv_testCovid.setItems(observableListHoKhauBeans);

    }

    @FXML
    void xoaTestCovid(ActionEvent event) {

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

    @FXML
    void timNhanKhauBean(ActionEvent event) {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tf_cccd.getText();
        System.out.println(cmt);

        nhanKhauTestCovid = thongKeNhanKhauService.getNhanKhau(cmt);
        if(nhanKhauTestCovid == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Không tồn tại chứng minh thư ");
            alert.show();
        }else{
            tf_hoVaTen.setText(nhanKhauTestCovid.getNhanKhauModel().getHo_ten());
        }

    }
    boolean isMissingField(){
        if ( tf_cccd.getText().isBlank() || tf_hoVaTen.getText().isEmpty() || tf_diaDiem.getText().isEmpty()
                || (dp_thoiGianTest.getClass() == null) || cb_ketQua.getItems().isEmpty()){
            return true;
        }
        return false;

    }
    void clearTf(){
        tf_cccd.clear();
        tf_hoVaTen.clear();
        tf_diaDiem.clear();
        dp_thoiGianTest.setTime(0);
        cb_ketQua.setValue(null);
    }

    @FXML
    void themTestCovid(ActionEvent event) throws SQLException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        TestCovidBean testCovidBean = new TestCovidBean();
        testCovidService = new TestCovidService();
        nhanKhauTestCovid = thongKeNhanKhauService.getNhanKhau(tf_cccd.getText());

        for(TestCovidBean cl: listNhanKhauTestCovid){
            if(cl.getNhanKhauBean().getChungMinhThuModel().getSoCMT().equals(tf_cccd.getText())){
                Alert thongBaoChung = new Alert(Alert.AlertType.WARNING);
                thongBaoChung.setContentText("Người này hiện đã tiêm ");
                thongBaoChung.show();
                clearTf();
                return;
            }
        }

        if(isMissingField()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
        }

        TestCovidModel testCovidModel = new TestCovidModel() ;
        testCovidModel.setDiaDiemTest(tf_diaDiem.getText());
        testCovidModel.setThoiDiemTest(dp_thoiGianTest);
        testCovidModel.setKetQua(Integer.parseInt(cb_ketQua.getValue()));

        testCovidBean.setNhanKhauBean(nhanKhauTestCovid);
        testCovidBean.setTestCovidModel(testCovidModel);

        testCovidService.addTestCovid(testCovidBean);

        refresh();
        clearTf();
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setContentText("Thêm thành công");
        successAlert.show();


    }

    @FXML
    void setThongTin(MouseEvent event) {
        testCovidService = new TestCovidService();
        TestCovidBean bean = tbv_testCovid.getSelectionModel().getSelectedItem();

        tf_hoVaTen.setText(bean.getNhanKhauBean().getNhanKhauModel().getHo_ten());
        tf_cccd.setText(bean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tf_diaDiem.setText(bean.getTestCovidModel().getDiaDiemTest());
        dp_thoiGianTest.setTime(bean.getTestCovidModel().getThoiDiemTest().getTime());
        if(bean.getTestCovidModel().getKetQua() == 1){
            cb_ketQua.setItems(FXCollections.observableArrayList("Âm tính"));
        }
        else{
            cb_ketQua.setItems(FXCollections.observableArrayList("Dương tính"));
        }

        tf_cccd.setDisable(true);
        tf_hoVaTen.setDisable(true);

        btn_Them.setDisable(true);
    }


    @FXML
    void reFresh(ActionEvent event) {
        clearTf();
        tf_hoVaTen.setDisable(false);
        tf_cccd.setDisable(false);
        btn_Them.setDisable(false);

    }

}
