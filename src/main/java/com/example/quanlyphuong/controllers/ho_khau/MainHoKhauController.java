package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.services.HoKhauService;
import com.example.quanlyphuong.services.MysqlConnection;
import com.example.quanlyphuong.services.NhanKhauService;
import javafx.beans.property.Property;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainHoKhauController implements Initializable {
    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;

    @FXML private TextField tfTimKiem;

    @FXML private TableView<HoKhauBean> tbvBangThongKe;

    @FXML private TableColumn<HoKhauBean, String> maHoKhau;

    @FXML private TableColumn<HoKhauBean, String> hoTenChuHo;

    @FXML private TableColumn<HoKhauBean, String> diaChi;

    @FXML private Button btnThemMoi;

    @FXML private Button btnTachHoKhau;

    @FXML private Button btnXoa;

    @FXML private Button btnHoanTac;

    @FXML private AnchorPane mhkPane;
    HoKhauBean _hkBean = new HoKhauBean();
    @FXML
    private void deleteRowFromTable(ActionEvent event) {
        Stage stage = (Stage)mhkPane.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.initOwner(stage);
        alert.setContentText("Hãy nhấn OK để thực hiện lệnh xóa, nhấn hủy để hủy lệnh xóa!");
        alert.setHeaderText("Bạn có muốn xóa hàng này không?");

        Optional<ButtonType> result = alert.showAndWait();

        // Đoạn này xóa em k biết làm này
        if(result.get() == ButtonType.OK) {
            for (HoKhauBean hkDetailString : tbvBangThongKe.getSelectionModel().getSelectedItems()) {
                _hkBean.delete(hkDetailString.getHoKhauModel());
                tbvBangThongKe.getItems().remove(hkDetailString);
            }
        } else if(result.get() == ButtonType.CANCEL)
        {
            alert.close();
        }
    }

    @FXML
    void changeSceneThemMoi(ActionEvent event) throws IOException {
        UIHelper.navigateNew("ho_khau/them_moi.fxml", "Thêm mới hộ khẩu", null);
    }
    @FXML
    void changeSceneTachHoKhau(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/tach_hk.fxml", "Tách  hộ khẩu", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauService = new HoKhauService();
        listHoKhauBeans = hoKhauService.getListHoKhau();

        observableListHoKhauBeans = FXCollections.observableList(listHoKhauBeans);

        maHoKhau.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        diaChi.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        hoTenChuHo.setCellValueFactory(hoKhau -> new ReadOnlyObjectWrapper<>(hoKhau.getValue().getChuHo().getHo_ten()));
        tbvBangThongKe.setItems(observableListHoKhauBeans);


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<HoKhauBean> filteredData = new FilteredList<>(observableListHoKhauBeans, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfTimKiem.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(_HoKhauBean -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (_HoKhauBean.getChuHo().getHo_ten().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else {
                    return false;
                }

            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<HoKhauBean> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tbvBangThongKe.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tbvBangThongKe.setItems(sortedData);


    }



    @FXML
    void huy(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }
}
