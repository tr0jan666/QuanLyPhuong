package com.example.quanlyphuong.controllers.nhan_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.controllers.ho_khau.MainHoKhauController;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.helper.constants.NhanKhauConstant;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {
    private Stage stage;
    private FXMLLoader loader;
    private Parent parent;
    private Scene scene;

    public static NhanKhauController frame;

    public static NhanKhauBean chosenNhanKhauBean;

    public NhanKhauController() {
        if (frame == null) {
            frame = this;
        } else {
//            super
//            return;
//            throw new RuntimeException("Singleton FXML");
        }
    }
    List<NhanKhauBean> listNhanKhauBean;
    ObservableList<NhanKhauBean> observableListNhanKhauBeans;

    @FXML
    private Button btn_ThemNhanKhau;

    @FXML
    private Button btn_Xoa;

    @FXML
    private Button btn_chiTiet;

    @FXML
    private Button btn_khaiTu;

    @FXML
    private Button btn_tamTru;

    @FXML
    private Button btn_tamVang;

    @FXML
    private Button btn_timKiem;

    @FXML
    private TableView<NhanKhauBean> tv_nhanKhau;

    @FXML
    private TableColumn<NhanKhauBean, String> col_cmt;

    @FXML
    private TableColumn<NhanKhauBean, String> col_gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> col_hoVaTen;

    @FXML
    private TableColumn<NhanKhauBean, Date> col_ngaySinh;

    @FXML
    private TableColumn<NhanKhauBean, String > col_ngheNghiep;

    @FXML
    private TableColumn<NhanKhauBean,String> col_diaChi;

    @FXML
    private TableColumn<NhanKhauBean,String> col_status;

    @FXML
    private Button btn_xoaTamVang;

    @FXML
    private Button btn_refresh;

    @FXML
    private Button btn_thanhThuongTru;

    @FXML
    private TextField tfTimKiem;

    @FXML
    void refreshTrang(){
        refreshScreen();
    }

    @FXML
    void khauTuNhanKhau(ActionEvent event){
        UIHelper.navigateNew("nhan_khau/khai_tu.fxml", "Khai tử nhân khẩu", null,800, 800);
    }

    @FXML
    void thanhThuongTru(ActionEvent event){
        UIHelper.navigateNew("nhan_khau/pop_up_thanh_thuong_tru.fxml", "Thành thường trú", null,1000, 700);
        refreshScreen();
    }

    @FXML
    void xoaTamTru(ActionEvent event){
        SimpleResult simpleResult = NhanKhauService.getInstance().xoaTamTru(chosenNhanKhauBean);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, simpleResult.getMessage(), ButtonType.CLOSE);
        alert.showAndWait();
        btn_xoaTamVang.setVisible(false);
        btn_tamVang.setVisible(true);

        refreshScreen();
    }

    @FXML
    void xoaTamVang(ActionEvent event){
        SimpleResult simpleResult = NhanKhauService.getInstance().xoaTamVang(chosenNhanKhauBean);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, simpleResult.getMessage(), ButtonType.CLOSE);
        alert.showAndWait();
        btn_xoaTamVang.setVisible(false);
        btn_tamVang.setVisible(true);

        refreshScreen();
    }

    @FXML
    void tamTruNhanKhau(ActionEvent event) {
        UIHelper.navigateNew("nhan_khau/pop_up_dk_tam_tru.fxml", "Thêm tạm trú", null,850, 600);
    }

    @FXML
    void tamVangNhanKhau(ActionEvent event) {
        UIHelper.navigateNew("nhan_khau/pop_up_dk_tam_vang.fxml", "Thêm tạm vắng", null,650, 600);
        btn_tamVang.setVisible(false);
        btn_xoaTamVang.setVisible(true);
    }

    @FXML
    void themNhanKhau(ActionEvent event) throws IOException {
        UIHelper.navigateNew("nhan_khau/pop_up_them_nhan_khau.fxml", "Thêm nhân khẩu", null,1000, 700);
//        btn_ThemNhanKhau.getScene().getWindow().hide();
    }

    @FXML
    void xemChiTietNhanKhau(ActionEvent event) {
        changeSceneChiTiet();
    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshScreen();



    }

    public void chooseOption(ActionEvent event){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("");

        CheckBox box1 = new CheckBox("Thêm thường trú");
        CheckBox box2 = new CheckBox("Thêm tạm trú");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
        dialog.getDialogPane().getChildren().addAll(box1,box2);
        dialog.setResultConverter(dialogButton ->{
            if(dialogButton == ButtonType.OK){
                if(box1.isSelected()){
//                    changeSceneThuongTru(event);
                }else if(box2.isSelected()){
                    changeSceneTamTru(event);
                }else return null;
            }
            return null;
        });
    }

    public void changeSceneTamTru(ActionEvent event) {
        UIHelper.navigateNew("/com.example.quanlyphuong/nhankhau/pop_up_dk_tam_tru.fxml", "Đăng ký tạm trú", null);
        btn_ThemNhanKhau.getScene().getWindow().hide();
    }


    public void refreshScreen(){

        listNhanKhauBean=  NhanKhauService.getInstance().getListNhanKhau();

        observableListNhanKhauBeans = FXCollections.observableList(listNhanKhauBean);

        col_hoVaTen.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        col_cmt.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));
        col_ngaySinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh()));
        col_diaChi.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        col_ngheNghiep.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNgheNghiep()));
        col_gioiTinh.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinhString()));
        col_status.setCellValueFactory(nhanKhauBean-> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getStatusString()));


        tv_nhanKhau.setItems(observableListNhanKhauBeans);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<NhanKhauBean> filteredData = new FilteredList<>(observableListNhanKhauBeans, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfTimKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(_nhanKhau -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (_nhanKhau.getNhanKhauModel().getHo_ten().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false;
                }

            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<NhanKhauBean> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tv_nhanKhau.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv_nhanKhau.setItems(sortedData);

        btn_tamVang.setVisible(false);
        btn_Xoa.setVisible(false);
        btn_chiTiet.setVisible(false);
        btn_khaiTu.setVisible(false);
        btn_xoaTamVang.setVisible(false);
        btn_thanhThuongTru.setVisible(false);
    }


    public void chiTietNhanKhau(MouseEvent event) {
        NhanKhauBean nhanKhauBean = tv_nhanKhau.getSelectionModel().getSelectedItem();
        NhanKhauController.chosenNhanKhauBean = nhanKhauBean;
        btn_chiTiet.setVisible(true);

        btn_Xoa.setVisible(false);
        btn_tamVang.setVisible(false);
        btn_xoaTamVang.setVisible(false);
        btn_thanhThuongTru.setVisible(false);

        if(nhanKhauBean.getNhanKhauModel().getStatus() == NhanKhauConstant.TAM_TRU_STATUS){
            btn_Xoa.setVisible(true);
            btn_thanhThuongTru.setVisible(true);
        }

        if(nhanKhauBean.getNhanKhauModel().getStatus() == NhanKhauConstant.THUONG_TRU_STATUS){
            btn_tamVang.setVisible(true);
            btn_khaiTu.setVisible(true);
        }

        if(nhanKhauBean.getNhanKhauModel().getStatus() == NhanKhauConstant.TAM_VANG_STATUS){
            btn_xoaTamVang.setVisible(true);
        }


        if(event.getClickCount() ==2 && (nhanKhauBean != null)){
            changeSceneChiTiet();
        }
    }

    public void changeSceneChiTiet() {

        UIHelper.navigateNew("nhan_khau/pop_up_thong_tin.fxml", "Chi tiết", null,1000,700);

    }
}
