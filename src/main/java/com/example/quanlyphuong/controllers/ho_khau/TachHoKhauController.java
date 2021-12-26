package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.beans.MemOfFamily;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.UIHelper;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.reflect.Member;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class TachHoKhauController implements Initializable {
    HoKhauService hoKhauService;
    List<HoKhauBean> listHoKhauBeans;
    ObservableList<HoKhauBean> observableListHoKhauBeans;
    ObservableList<MemOfFamily> memOfFamilies;
    ObservableList<MemOfFamily> memOfFamilies2;
    String quanHeBanDau ;
    Dialog<String> dialog;
    TextField quanHeText;

    NhanKhauModel chuHo;
    NhanKhauModel chuHoMoi;

    ArrayList<String> quanHeChuHoList = new ArrayList<>();
    boolean daCoChuHo = false;
    public ArrayList<String> getQuanHeChuHoList() {
        return quanHeChuHoList;
    }

    public String getQuanHeBanDau() {
        return quanHeBanDau;
    }

    @FXML
    private TableView<HoKhauBean> tbvHoCanTach;

    @FXML
    private TableColumn<HoKhauBean, String> maHoKhauChuHo;

    @FXML
    private TableColumn<HoKhauBean, String> hoTenChuHo;

    @FXML
    private TableColumn<HoKhauBean, String> diaChiChuHo;

    @FXML
    private TableView<MemOfFamily> tbvNguoiSangHoMoi;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenNhanKhauCu;

    @FXML
    private TableColumn<MemOfFamily, Date> ngaySinhNhanKhauCu;

    @FXML
    private TableColumn<MemOfFamily, String> quanHeCu;

    @FXML
    private TextField tfChuHoHienTai;

    @FXML
    private TextField tfMaKhuVuc;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfMaHoKhauMoi;

    @FXML
    private TextField tfChuHoMoi;

    @FXML
    private Button btnChuyenSang;

    @FXML
    private Button btnChuyenVe;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private Button btnChonChuHoMoi;

    @FXML
    private TableView<MemOfFamily> tbvNguoiHoMoi;

    @FXML
    private TableColumn<MemOfFamily, String> hoTenNhanKhauMoi;

    @FXML
    private TableColumn<MemOfFamily, Date> ngaySinhMoi;

    @FXML
    private TableColumn<MemOfFamily, String> quanHeMoi;



    @FXML
    void chuyenSang(ActionEvent event) {
        MemOfFamily memOfFamily = tbvNguoiSangHoMoi.getSelectionModel().getSelectedItem();


        if(memOfFamily.getThanhVienCuaHoModel().getQuanHeVoiChuHo().equals("chuho")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("khong thể chuyển chủ hộ sang chủ hộ mới");
            alert.show();
            return;
        }
        quanHeBanDau = memOfFamily.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(quanHe -> {
            if(quanHeChuHoList.contains(quanHe) && daCoChuHo == false){
                daCoChuHo = true;
                memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
                memOfFamilies.remove(memOfFamily);
                memOfFamilies2.add(memOfFamily);
            }else if(quanHeChuHoList.contains(quanHe) && daCoChuHo == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("đã có chủ hộ");
                alert.show();
                return;
            }else{
                memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
                memOfFamilies.remove(memOfFamily);
                memOfFamilies2.add(memOfFamily);
            }
        });

        System.out.println("mem of families 2");
        for(MemOfFamily mem: memOfFamilies2){
            mem.getNhanKhau().getNhanKhauModel().getHo_ten();
            mem.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
        }
        System.out.println("mem of families 1");
        for(MemOfFamily mem: memOfFamilies){
            mem.getNhanKhau().getNhanKhauModel().getHo_ten();
            mem.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
        }
    }

    @FXML
    void chuyenVe(ActionEvent event) {
        MemOfFamily memOfFamily = tbvNguoiHoMoi.getSelectionModel().getSelectedItem();
        String chuho = memOfFamily.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
        if(quanHeChuHoList.contains(chuho)){
            daCoChuHo = false;
        }
        memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHeBanDau);
        memOfFamilies.add(memOfFamily);
        memOfFamilies2.remove(memOfFamily);

    }


    @FXML
    void xacNhanTachHoKHau(ActionEvent event) {
        if (isMissingField()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Hãy điền vào tất cả các trường thông tin cần thiết");
            alert.show();
            return;
        }
        ;
        if(daCoChuHo == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Phải có chủ hộ. Vui lòng thêm chủ hộ");
            alert.show();
            return;
        }
        for(MemOfFamily mem : memOfFamilies2){
            if(quanHeChuHoList.contains(mem.getThanhVienCuaHoModel().getQuanHeVoiChuHo())){
                chuHoMoi = mem.getNhanKhau().getNhanKhauModel();
                System.out.println("chu ho moi ");
                System.out.println(mem.getNhanKhau().getNhanKhauModel().getHo_ten());
            }
        }

        HoKhauBean hoKhauBean = new HoKhauBean();
//        Set HoKhauModel
        HoKhauModel hkmodel = new HoKhauModel();
        hkmodel.setMaHoKhau(tfMaHoKhauMoi.getText());
        hkmodel.setIdChuHo(chuHoMoi.getID());
        hkmodel.setDiaChi(tfDiaChi.getText());
        hkmodel.setMaKhuVuc(tfMaKhuVuc.getText());
//
        hoKhauBean.setHoKhauModel(hkmodel);

        //Set chu ho
        hoKhauBean.setChuHo(chuHoMoi);
        //Set list thanh vien ho

        List<MemOfFamily> memOfNewFamilyList = new ArrayList<>(memOfFamilies2);
        List<ThanhVienCuaHoModel> thanhVienCuaHoModelList = memOfNewFamilyList.stream().map(memOfNewFamily -> memOfNewFamily.getThanhVienCuaHoModel()).collect(Collectors.toList());
        hoKhauBean.setListThanhVienCuaHo(thanhVienCuaHoModelList);


        //sout thông tin để kiểm tra bean
        System.out.println("ho ten chu ho"+hoKhauBean.getChuHo().getHo_ten());
        for(ThanhVienCuaHoModel tvch: hoKhauBean.getListThanhVienCuaHo()){
            System.out.println("id nhan khau"+tvch.getIdNhanKhau());
            System.out.println("quan he chu ho"+tvch.getQuanHeVoiChuHo());
        }
        hoKhauService.tachHoKhau(hoKhauBean);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thêm thành công");
        alert.show();
        huyTachHoKhau(event);

    }




    @FXML
    void huyTachHoKhau(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainHoKhauController.frame.refreshScreen();
        stage.hide();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.quanHeChuHoList.add("chuho");
        this.quanHeChuHoList.add("Chủ hộ");
        this.quanHeChuHoList.add("chủ Hộ");
        this.quanHeChuHoList.add("ChuHo");


        hoKhauService = new HoKhauService();
        listHoKhauBeans = hoKhauService.getListHoKhau();

        ObservableList observableListHoKhau = FXCollections.observableList(listHoKhauBeans);
        maHoKhauChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getMaHoKhau()));
        diaChiChuHo.setCellValueFactory(hoKhauBean -> new ReadOnlyObjectWrapper<>(hoKhauBean.getValue().getHoKhauModel().getDiaChi()));
        hoTenChuHo.setCellValueFactory(hoKhau -> new ReadOnlyObjectWrapper<>(hoKhau.getValue().getChuHo().getHo_ten()));

        tbvHoCanTach.setItems(observableListHoKhau);
        tbvNguoiHoMoi.setItems(memOfFamilies2);
        tbvNguoiSangHoMoi.setItems(memOfFamilies);


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
            if (dialogButton == addButtonType) {
                return quanHeText.getText();
            }
            return null;
        });

    }

    public void chonHoCanTach(MouseEvent mouseEvent) {
        HoKhauBean hoKhauBean = tbvHoCanTach.getSelectionModel().getSelectedItem();
        List<ThanhVienCuaHoModel> thanhVienCuaHoModels = hoKhauBean.getListThanhVienCuaHo();
        List<NhanKhauModel> nhanKhauModels = hoKhauBean.getListNhanKhauModels();

        List<MemOfFamily> memOfFamilyList = new ArrayList<>();

        for(NhanKhauModel nk : nhanKhauModels){
            System.out.println(nk.getHo_ten());
        }
        for(ThanhVienCuaHoModel ho: thanhVienCuaHoModels){
            System.out.println(ho.getIdHoKhau()+"-"+ho.getIdNhanKhau());
        }
        tfChuHoHienTai.setText(hoKhauBean.getChuHo().getHo_ten());

        //Set thanh vien cua ho
        thanhVienCuaHoModels.stream().forEach(thanhVienCuaHoModel -> {
            MemOfFamily memOfFamily = new MemOfFamily();
            memOfFamily.setThanhVienCuaHoModel(thanhVienCuaHoModel);
            memOfFamilyList.add(memOfFamily);
        });

        System.out.println(memOfFamilyList.size());


        for (int i = 0; i < memOfFamilyList.size(); i++) {
            System.out.println(nhanKhauModels.get(i).getHo_ten());
            NhanKhauModel nk = nhanKhauModels.get(i);
            NhanKhauBean bean = new NhanKhauBean();
            bean.setNhanKhauModel(nk);
            memOfFamilyList.get(i).setNhanKhau(bean);
        }
//        for(int i =0;i <memOfFamilyList.size();i++){
//            System.out.println(memOfFamilyList.get(i).getNhanKhau().getNhanKhauModel());
//        }

//
//
        memOfFamilies = FXCollections.observableList(memOfFamilyList);
        hoTenNhanKhauCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHo_ten()));
        ngaySinhNhanKhauCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh()));
        quanHeCu.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        tbvNguoiSangHoMoi.setItems(memOfFamilies);

        memOfFamilies2 = FXCollections.observableList(new ArrayList<>());
        hoTenNhanKhauMoi.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getHo_ten()));
        ngaySinhMoi.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getNhanKhau().getNhanKhauModel().getNamSinh()));
        quanHeMoi.setCellValueFactory(memOfFamily -> new ReadOnlyObjectWrapper<>(memOfFamily.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
        tbvNguoiHoMoi.setItems(memOfFamilies2);

    }



    public boolean isMissingField() {
        if ( tfMaKhuVuc.getText().isBlank() || tfDiaChi.getText().isEmpty() || tfDiaChi.getText().isEmpty() )
            return true;
        return false;
    }
}

