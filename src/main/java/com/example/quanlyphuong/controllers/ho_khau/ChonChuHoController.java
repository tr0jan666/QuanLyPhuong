package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.table.TableRowExpanderColumn;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ChonChuHoController implements Initializable{

    @FXML
    private Button btnHuy;

    @FXML
    private TableColumn<NhanKhauBean, String> diaChiHienNay;

    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> hoTen;

    @FXML
    private TableColumn<NhanKhauBean, Date> ngaySinh;

    @FXML
    private TableColumn<NhanKhauBean, String> soCMT;

    @FXML
    private TableView<NhanKhauBean> tbvDanhSachChuHo;


    List<NhanKhauBean> listNhanKhauBean = new ArrayList<>();
    NhanKhauModel chuho;
    ChungMinhThuModel cmt;
    NhanKhauService nhanKhauService;

    ObservableList<NhanKhauBean> nhanKhauBeanObservableList;


    @FXML
    void chonChuHo(MouseEvent mouseEvent) {
        ObservableList<NhanKhauBean> selectedChuHo = tbvDanhSachChuHo.getSelectionModel().getSelectedItems();

        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2 && (!selectedChuHo.isEmpty())){

            }
        }
    }

    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList nhanKhauBeanObservableList = FXCollections.observableList(listNhanKhauBean);
        diaChiHienNay.setCellValueFactory(NhanKhauBean -> new ReadOnlyObjectWrapper<>(NhanKhauBean.getValue().getNhanKhauModel().getDiaChiHienNay()));
        gioiTinh.setCellValueFactory(NhanKhauBean -> new ReadOnlyObjectWrapper<>(NhanKhauBean.getValue().getNhanKhauModel().getGioiTinh()));
        hoTen.setCellValueFactory(NhanKhauBean -> new ReadOnlyObjectWrapper<>(NhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        ngaySinh.setCellValueFactory(NhanKhauBean -> new ReadOnlyObjectWrapper<>(NhanKhauBean.getValue().getNhanKhauModel().getNamSinh()));
        soCMT.setCellValueFactory(NhanKhauBean -> new ReadOnlyObjectWrapper<>(NhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));

        tbvDanhSachChuHo.setItems(nhanKhauBeanObservableList);

    }

}

