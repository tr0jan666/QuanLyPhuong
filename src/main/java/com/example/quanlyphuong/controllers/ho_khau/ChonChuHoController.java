package com.example.quanlyphuong.controllers.ho_khau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChonChuHoController {

    @FXML
    private Button btnHuy;

    @FXML
    private TableColumn<?, ?> diaChiHienNay;

    @FXML
    private TableColumn<?, ?> gioiTinh;

    @FXML
    private TableColumn<?, ?> hoTen;

    @FXML
    private TableColumn<?, ?> ngaySinh;

    @FXML
    private TableColumn<?, ?> soCMT;

    @FXML
    private TableView<?> tbvDanhSachChuHo;

    @FXML
    void chonChuHo(MouseEvent event) {

    }

    @FXML
    public void huy(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

}

