package com.example.quanlyphuong.controllers.dich_te;

import com.example.quanlyphuong.beans.KhaiBaoBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.controllers.ho_khau.MainHoKhauController;
import com.example.quanlyphuong.models.KhaiBaoModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.services.KhaiBaoService;
import com.example.quanlyphuong.services.ThongKeNhanKhauService;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.CachLyModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class KhaiBaoController implements Initializable {
    public static KhaiBaoController frame;

    public KhaiBaoController() {
        if (frame == null) {
            frame = this;
        } else {
//            super();
        }
    }
    @FXML
    private TableColumn<KhaiBaoBean, String> CCCD;

    @FXML
    private TableColumn<KhaiBaoBean, String> DiaDiem;

    @FXML
    private TableColumn<KhaiBaoBean, String> HoTen;

    @FXML
    private TableColumn<KhaiBaoBean, String> ID;

    @FXML
    private TableColumn<KhaiBaoBean, String> BieuHien;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnCheckCCCD;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnXoa;

    @FXML
    private TableView<KhaiBaoBean> tbvChiTiet;

    @FXML
    private TextField tfCCCD;

    @FXML
    private TextField tfDiaDiem;

    @FXML
    private TextField tfHoTen;

    @FXML
    private DatePicker tfKhaiBao;

    @FXML
    private TextArea taBieuHien;

    @FXML
    private TableColumn<KhaiBaoBean, String> thoigiankhaibao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    List<KhaiBaoBean> listNhanKhauKhaiBao;
    KhaiBaoService khaiBaoService;
    ObservableList<KhaiBaoBean> observableListHoKhauBeans;
    ThongKeNhanKhauService thongKeNhanKhauService;
    NhanKhauBean nhanKhauKhaiBao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void refresh(){
        khaiBaoService = new KhaiBaoService();
        listNhanKhauKhaiBao = khaiBaoService.getListNhanKhauKhaiBao();

        observableListHoKhauBeans = FXCollections.observableList(listNhanKhauKhaiBao);

        ID.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getMaNhanKhau()));
        HoTen.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getNhanKhauModel().getHo_ten()));
        CCCD.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getNhanKhauBean().getChungMinhThuModel().getSoCMT()));
        DiaDiem.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getKhaiBaoModel().getVungDich()));
        //thoigiankhaibao.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getKhaiBaoModel().getNgayKhaiBao().toString()));
        thoigiankhaibao.setCellValueFactory(bean -> new ReadOnlyObjectWrapper<>(bean.getValue().getKhaiBaoModel().getNgayKhaiBao()));
        BieuHien.setCellValueFactory(bean-> new ReadOnlyObjectWrapper<>(bean.getValue().getKhaiBaoModel().getBieuHien()));
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
            KhaiBaoBean bean = tbvChiTiet.getSelectionModel().getSelectedItem();
            khaiBaoService = new KhaiBaoService();
            khaiBaoService.deleteKhaiBao(bean);
            refresh();
            Alert thanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
            thanhCongAlert.setContentText("Xoa Thanh Cong");
            thanhCongAlert.show();
            reFreshThongTin(event);
        } else if (result.get() == ButtonType.CANCEL) {
            reFreshThongTin(event);
            alert.close();
        }

    }
    @FXML
    void reFreshThongTin(ActionEvent event) {
        clearTf();
        tfHoTen.setDisable(false);
        tfCCCD.setDisable(false);
        btnThem.setDisable(false);

    }
    void clearTf(){
        tfCCCD.clear();
        tfHoTen.clear();
        tfDiaDiem.clear();
        taBieuHien.clear();
        tfKhaiBao.setValue(null);

    }
    @FXML
    void checkCCCD(ActionEvent event) throws IOException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        String cmt = tfCCCD.getText();

        nhanKhauKhaiBao = thongKeNhanKhauService.getNhanKhau(cmt);
        if(nhanKhauKhaiBao == null || nhanKhauKhaiBao.getNhanKhauModel() == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Không tồn tại CCCD! Bạn có muốn thêm nhân khẩu mới không?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/quanlyphuong/nhan_khau/pop_up_them_nhan_khau.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("Them nhan khau");
                stage.setScene(new Scene(root1));
                stage.show();
                return;
            } else {
                return;
            }


        }else{
            tfHoTen.setText(nhanKhauKhaiBao.getNhanKhauModel().getHo_ten());
        }
    }
    boolean isMissingField(){
        if ( tfCCCD.getText().isBlank() || tfHoTen.getText().isEmpty() || tfDiaDiem.getText().isEmpty()
                || (tfKhaiBao.getValue() == null) || taBieuHien.getText().isEmpty() ){
            return true;
        }
        return false;

    }
    @FXML
    void themKhaiBao(ActionEvent event) throws SQLException {
        thongKeNhanKhauService = new ThongKeNhanKhauService();
        KhaiBaoBean khaiBaoBean = new KhaiBaoBean();
        khaiBaoService = new KhaiBaoService();
//        nhanKhauKhaiBao = thongKeNhanKhauService.getNhanKhau(tfCCCD.getText());
//        System.out.println(nhanKhauKhaiBao.getNhanKhauModel().getHo_ten());
        try {
            checkCCCD(null);
        }catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        for(KhaiBaoBean cl: listNhanKhauKhaiBao){
            if(cl.getNhanKhauBean().getChungMinhThuModel().getSoCMT().equals(tfCCCD.getText())){
                Alert thongBaoTrung = new Alert(Alert.AlertType.WARNING);
                thongBaoTrung.setContentText("Người này hiện đã tồn tại");
                thongBaoTrung.show();
                clearTf();
                return;
            }
        }

        if(isMissingField()){
            Alert missingAlert = new Alert(Alert.AlertType.WARNING);
            missingAlert.setContentText("Vui lòng điền đầy đủ thông tin");
            missingAlert.show();
            return;
        }

        KhaiBaoModel khaiBaoModel = new KhaiBaoModel();
        khaiBaoModel.setVungDich(tfDiaDiem.getText());

        khaiBaoModel.setNgayKhaiBao(tfKhaiBao.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        khaiBaoModel.setBieuHien(taBieuHien.getText());

        khaiBaoBean.setNhanKhauBean(nhanKhauKhaiBao); //check
        khaiBaoBean.setKhaiBaoModel(khaiBaoModel);

        Alert alertCn = new Alert(Alert.AlertType.CONFIRMATION);
        alertCn.setContentText("Bạn có thêm người khai báo");
        Optional<ButtonType> confirmCn = alertCn.showAndWait();

        if(confirmCn.get() == ButtonType.OK){
            SimpleResult rs = khaiBaoService.addKhaiBao(khaiBaoBean);
            if(rs.isSuccess()){
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setContentText("Thêm thành công");


                reFreshThongTin(event);
                alertSuccess.show();
                refresh();
            }else{
                Alert alertFailed = new Alert(Alert.AlertType.INFORMATION);
                alertFailed.setContentText(rs.getMessage());
                alertFailed.show();


                reFreshThongTin(event);
                refresh();
            }
        }else {
            reFreshThongTin(event);
            return;
        }

    }
    @FXML
    void setThongTin(MouseEvent event) {
        khaiBaoService = new KhaiBaoService();
        KhaiBaoBean bean = tbvChiTiet.getSelectionModel().getSelectedItem();

        tfHoTen.setText(bean.getNhanKhauBean().getNhanKhauModel().getHo_ten());
        tfCCCD.setText(bean.getNhanKhauBean().getChungMinhThuModel().getSoCMT());
        tfDiaDiem.setText(bean.getKhaiBaoModel().getVungDich());

        tfKhaiBao.setValue(LocalDate.parse(bean.getKhaiBaoModel().getNgayKhaiBao(),formatter));

        taBieuHien.setText(String.valueOf(bean.getKhaiBaoModel().getBieuHien()));

        tfCCCD.setDisable(true);
        tfHoTen.setDisable(true);

        btnThem.setDisable(true);
    }

}
