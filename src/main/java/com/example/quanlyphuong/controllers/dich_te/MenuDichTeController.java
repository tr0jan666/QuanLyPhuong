package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import com.example.quanlyphuong.controllers.MenuController;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.AppScreen;
import com.example.quanlyphuong.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuDichTeController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnCachLy;

    @FXML
    private Button btnKhaiBao;

    @FXML
    private Button btnQuayLai;

    @FXML
    private Button btnTestCovid;

    @FXML
    private Button btnThongKe;

    @FXML
    private Button btnTiemChung;

    @FXML
    private Button trangChu;

    @FXML
    void setCachLy(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/cachly.fxml"));
        Pane cachlyPane = (Pane) loader.load();
        borderPane.setCenter(cachlyPane);

    }

    @FXML
    void setKhaiBao(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/khaibao.fxml"));
        Pane khaibaoPane = (Pane) loader.load();
        borderPane.setCenter(khaibaoPane);

    }

    @FXML
    void setQuayLai(ActionEvent event) throws IOException {
        AppScreen menuChucNangScreen = UIHelper.navigateNew("menu-chuc-nang.fxml", "Chọn chức năng", null,600,400);
        menuChucNangScreen.<MenuController>getController().getTxtRegister().setVisible(AuthService.getInstance().getCurrentUser().isAdministrator());
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void setTestCovid(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/testcovid.fxml"));
        Pane testcovidPane = (Pane) loader.load();
        borderPane.setCenter(testcovidPane);

    }

    @FXML
    void setThongKe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/thongke-dichte.fxml"));
        Pane thongkePane = (Pane) loader.load();
        borderPane.setCenter(thongkePane);

    }

    @FXML
    void setTiemChung(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/tiemchung.fxml"));
        Pane tiemchungPane = (Pane) loader.load();
        borderPane.setCenter(tiemchungPane);

    }

    @FXML
    void setTrangChu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/main-dich-te.fxml"));
        Pane trangchuPane = (Pane) loader.load();
        borderPane.setCenter(trangchuPane);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            Pane mainDichTe = FXMLLoader.load(QuanLyNhanKhauApplication.class.getResource("dich_te/main-dich-te.fxml"));
            borderPane.setCenter(mainDichTe);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
