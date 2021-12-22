package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.helper.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainHoKhauController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<?> tbvBangThongKe;

    @FXML
    private Button btnThemMoi;

    @FXML
    private Button btnTachHoKhau;

    @FXML
    private TextField tfTimKiem;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnHoanTac;

    @FXML
    void changeSceneThemMoi(ActionEvent event) throws IOException {
        UIHelper.navigateNew("ho_khau/them_moi.fxml", "Thêm mới hộ khẩu", null);
    }
    @FXML
    void changeSceneTachHoKhau(ActionEvent event) {
        UIHelper.navigateNew("ho_khau/tach_hk.fxml", "Tách  hộ khẩu", null);
    }
}
