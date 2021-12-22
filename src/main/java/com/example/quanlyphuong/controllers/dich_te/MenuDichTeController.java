package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.QuanLyNhanKhauApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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


    }

    @FXML
    void setTestCovid(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/testcovid.fxml"));
        Pane testcovidPane = (Pane) loader.load();
        borderPane.setCenter(testcovidPane);

    }

    @FXML
    void setThongKe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(QuanLyNhanKhauApplication.class.getResource("dich_te/thongke.fxml"));
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

            Pane login = FXMLLoader.load(QuanLyNhanKhauApplication.class.getResource("dich_te/main-dich-te.fxml"));
            borderPane.setCenter(login);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
