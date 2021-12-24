package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class NhanKhauService {
    public NhanKhauModel getDetail(int idNhanKhau) {
        NhanKhauModel nhanKhauModel = new NhanKhauModel();
        ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();
        try{
            Connection connection = MySQLConnector.getConnection();
            String query = "Select * from nhan_khau nk join cmt/cccd cc on cc.idNhanKhau = nk.maNhanKhau where maNhanKhau =" + idNhanKhau;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                nhanKhauModel.setHo_ten(resultSet.getString("hoTen"));
                nhanKhauModel.setNamSinh(resultSet.getDate("namSinh"));
                nhanKhauModel.setGioiTinh(resultSet.getString("gioiTinh"));
                nhanKhauModel.setNoiSinh(resultSet.getString("noiSinh"));
                nhanKhauModel.setNguyenQuan(resultSet.getString("nguyenQuan"));
                nhanKhauModel.setDanToc(resultSet.getString("danToc"));
                nhanKhauModel.setQuocTich(resultSet.getString("quocTich"));
                nhanKhauModel.setNoiThuongTru(resultSet.getString("noiThuongTru"));
                nhanKhauModel.setDiaChiHienNay(resultSet.getString("diaChiHienNay"));
                nhanKhauModel.setTrinhDoHocVan(resultSet.getString("trinhDoHocVan"));
                nhanKhauModel.setNgheNghiep(resultSet.getString("ngheNghiep"));
                nhanKhauModel.setNoiLamViec(resultSet.getString("noiLamViec"));
                nhanKhauModel.setTienAn(resultSet.getString("tienAn"));
                nhanKhauModel.setNgayChuyenDen(resultSet.getDate("ngayChuyenDen"));
                nhanKhauModel.setLyDoChuyenDen(resultSet.getString("lyDoChuyenDen"));
                nhanKhauModel.setNgayChuyenDi(resultSet.getDate("ngayChuyenDi"));
                nhanKhauModel.setLyDoChuyenDi(resultSet.getString("lyDoChuyenDi"));
                nhanKhauModel.setDiaChiMoi(resultSet.getString("diaChiMoi"));
                nhanKhauModel.setNgayTao(resultSet.getDate("ngayTao"));
                nhanKhauModel.setIdNguoiTao(resultSet.getInt("gioiTinh"));
                nhanKhauModel.setNgayXoa(resultSet.getDate("ngayXoa"));
                nhanKhauModel.setIdNguoiXoa(resultSet.getInt("idNguoiXoa"));
                nhanKhauModel.setLyDoXoa(resultSet.getString("lyDoXoa"));
                nhanKhauModel.setGhiChu(resultSet.getString("ghiChu"));

                chungMinhThuModel.setID(resultSet.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(resultSet.getString("soCMT"));
                chungMinhThuModel.setNgayCap(resultSet.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(resultSet.getString("noiCap"));
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public SimpleResult xoaNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult khaiTuNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult taoNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult suaNhanKhau(int idNhanKhau) {
        return null;
    }

    public ArrayList<NhanKhauModel> timKiemNhanKhau(String keyword, NhanKhauFilterEnum filterType) {
        return null;
    }

    public ArrayList<NhanKhauModel> filterNhanKhau(Map<NhanKhauFilterEnum, String> filterOptions) {
        return null;
    }

    public String countTiLeTiem1Vacxin() {
        return null;
    }

    public String countTiLeTiem2Vacxin() {
        return null;
    }

    public String countTiLeTuVong() {
        return null;
    }

    public int soNguoiCachLy() {
        return 0;
    }
}
