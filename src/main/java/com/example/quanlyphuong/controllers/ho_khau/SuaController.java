package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.MemOfFamily;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.models.ThanhVienCuaHoModel;
import com.example.quanlyphuong.services.HoKhauService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class SuaController implements Initializable {

    @FXML
    private TableView<NhanKhauBean> dataTable;

    @FXML
    private TableColumn<String, String> IDColumn;

    @FXML
    private TableColumn<NhanKhauBean, String> hoTen;

    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;

    @FXML
    private TableColumn<NhanKhauBean, String> ngaySinh;

    @FXML
    private TableColumn<NhanKhauBean, String> soCMT;

    @FXML
    private TableView<MemOfFamily> addedDataTable;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenAdded;

    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhAdded;

    @FXML
    private TableColumn<MemOfFamily, String> quanHeVoiChuHo;


    List<NhanKhauBean> listNhanKhauBean;
    List<MemOfFamily> listMemOfFamily;
    HoKhauService hoKhauService;
    Dialog<String> dialog;

    ObservableList<MemOfFamily> memOfFamilyObservableList;
    ObservableList<NhanKhauBean> nhanKhauBeanObservableList;
    TextField quanHeText;
    NhanKhauBean chuho;
    @FXML
    void add(ActionEvent event) {
        NhanKhauBean selectedNhanKhau = dataTable.getSelectionModel().getSelectedItem();
        MemOfFamily memOfFamily = new MemOfFamily();
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quanHe -> {
            if(quanHe.equals("chuho")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Đã có chủ hộ");
                alert.show();
                return;
            }
            ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
            thanhVienCuaHoModel.setQuanHeVoiChuHo(quanHe);
            memOfFamily.setThanhVienCuaHoModel(thanhVienCuaHoModel);
        });
        memOfFamily.setNhanKhau(selectedNhanKhau);

        memOfFamily.getThanhVienCuaHoModel().setIdNhanKhau(selectedNhanKhau.getNhanKhauModel().getID());
        memOfFamilyObservableList.add(memOfFamily);
        quanHeText.setText("");
    }

    @FXML
    void remove(ActionEvent event) {
        MemOfFamily selectedMemOfFamily = addedDataTable.getSelectionModel().getSelectedItem();
        if(selectedMemOfFamily.getThanhVienCuaHoModel().getQuanHeVoiChuHo().equals("chuho")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Không thể remove chủ hộ");
            alert.show();
            return;
        }
        memOfFamilyObservableList.remove(selectedMemOfFamily);
    }

    @FXML
    void save(ActionEvent event) {
        ThanhVienHoHolder thanhVienHoHolder = ThanhVienHoHolder.getInstance();
        thanhVienHoHolder.setListThanhVienHo(memOfFamilyObservableList);
        huy(event);
    }
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        chuho = ChuHoHolder.getInstance().getNhanKhauBean();
        System.out.println("chu ho hien tai" +chuho.getNhanKhauModel().getHo_ten());

        listMemOfFamily = new ArrayList<>();

        try {
            listNhanKhauBean = hoKhauService.danhSachNhanKhauCoTheLamChuHo();
            Iterator<NhanKhauBean> itr = listNhanKhauBean.iterator();
                while(itr.hasNext()){
                    NhanKhauBean bean = itr.next();
                if(bean.getNhanKhauModel().getID() == chuho.getNhanKhauModel().getID()){
                    System.out.println("chủ hộ là "+ bean.getNhanKhauModel().getHo_ten());
                    MemOfFamily memOfFamilyChuho = new MemOfFamily();

                    memOfFamilyChuho.setNhanKhau(bean);

                    ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                    thanhVienCuaHoModel.setQuanHeVoiChuHo("chuho");
                    thanhVienCuaHoModel.setIdNhanKhau(bean.getNhanKhauModel().getID());
                    memOfFamilyChuho.setThanhVienCuaHoModel(thanhVienCuaHoModel);

                    listMemOfFamily.add(memOfFamilyChuho);

                    itr.remove();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        nhanKhauBeanObservableList = FXCollections.observableList(listNhanKhauBean);
//        IDColumn.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(String.valueOf(nhanKhauBean.getValue().getID())));
        hoTen.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getHo_ten()));
        gioiTinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getGioiTinh()));
        ngaySinh.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getNhanKhauModel().getNamSinh().toString()));
        soCMT.setCellValueFactory(nhanKhauBean -> new ReadOnlyObjectWrapper<>(nhanKhauBean.getValue().getChungMinhThuModel().getSoCMT()));
        dataTable.setItems(nhanKhauBeanObservableList);

        //Set data bang ben phai
        memOfFamilyObservableList = FXCollections.observableList(listMemOfFamily);
        hoTenAdded.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHo_ten()));
        ngaySinhAdded.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeVoiChuHo.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        addedDataTable.setItems(memOfFamilyObservableList);

        //Tao "quan he voi chu ho dialog"
        dialog = new Dialog<>();
        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        quanHeText = new TextField();
        Label quanHeLB = new Label("Nhập quan hệ với chủ hộ:");

        grid.add(quanHeLB, 0, 0);
        grid.add(quanHeText, 0, 1);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        quanHeText.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType){
                return quanHeText.getText();
            }
            return null;
        });

    }
}
