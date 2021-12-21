package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanKhauService {
    public NhanKhauModel getDetail(int idNhanKhau) {
        //write code here
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

        //extract_info
        Map<Integer, String> map = new HashMap<Integer, String>();
            map.put(1, "maNhanKhau");
            map.put(2, "Ho_ten");
            map.put(3, "namSinh");
            map.put(4, "diaChiHienNay");
            map.put(5, "ngheNghiep");
            map.put(6, "noiLamViec");

        String column = map.get(filterType);
        String query = "SELECT * FROM nhan_khau WHERE " + column +" LIKE '%" + keyword + "%'";

        // truy van tren database
        try {

            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<NhanKhauModel> list = new ArrayList<>();

            while (rs.next()) {

                NhanKhauModel nhanKhau = new NhanKhauModel();

                nhanKhau.setMaNhanKhau(rs.getInt("maNhanKhau"));
                nhanKhau.setHo_ten(rs.getString("Ho_ten"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                nhanKhau.setNgheNghiep(rs.getInt("ngheNghiep"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));

                list.add(nhanKhau);
            }
            preparedStatement.close();
            return list;
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new ArrayList<>();
        }

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
