package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.KhaiTuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class NhanKhauService {
    public NhanKhauModel getDetail(int idNhanKhau) {
        //write code here

        return null;
    }

    public SimpleResult xoaNhanKhau(int idNhanKhau) {
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `quan_ly_nhan_khau`.`nhan_khau` WHERE (`ID` = '" + idNhanKhau + "');";
            statement.executeUpdate(query);
        }catch(Exception mysqlException){
//            this.exceptionHandle(mysqlException.getMessage());
            System.out.println(mysqlException);
        }

        return null;
    }

    public SimpleResult khaiTuNhanKhau(int idNhanKhau) {
//        try {
//
//            NhanKhauModel nguoiChet = getDetail(idNhanKhau);
//
//
//
//            int ID = .getID();
//            String soCMTnguoiChet = .getSoCMTnguoiMat();
//            String soCMTnguoiKhai = .getSoCMTnguoiKhai();
//            String tenNguoiKhai = .getTenNguoiKhai();
//            String soGiayKhaiTu = .getSoGiayKhaiTu();
//            String ngayKhai = .getNgayKhai();
//            String ngayMat = .getNgayMat();
//            String lyDoChet = nguoiChet.getLyDoChet();
//            Connection connection = MysqlConnection.getMysqlConnection();
//            Statement statement = connection.createStatement();
//            String query = "INSERT INTO `QuanLyNhanKhau`.`khai_tu` (`ID`, `soCMTnguoiChet`, `soCMTnguoiKhai`, `tenNguoiKhai`, `soGiayKhaiTu`, `ngayKhai`, `ngayMat`, `lyDoChet`) VALUES (null, '" + soCMTnguoiChet + "', '" + soCMTnguoiKhai + "', '" + tenNguoiKhai + "', '" + soGiayKhaiTu + "', '" + ngayKhai + "', '" + ngayMat + "', '" + lyDoChet + "');";
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public SimpleResult khaiTuNhanKhau(KhaiTuModel khaiTuModel) {
        try {
            int ID = khaiTuModel.getID();
            String soGiayKhaiTu = khaiTuModel.getSoGiayKhaiTu();
            int idNguoiKhai = khaiTuModel.getIdNguoiKhai();
            int idNguoiChet = khaiTuModel.getIdNguoiChet();
            Date ngayKhai = khaiTuModel.getNgayKhai();
            Date ngayChet = khaiTuModel.getNgayChet();
            String lyDoChet = khaiTuModel.getLyDoChet();
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO `quan_ly_nhan_khau`.`khai_tu` (`ID`, `soGiayKhaiTu`, `idNguoiKhai`, `idNguoiChet`, `ngayKhai`, `ngayChet`, `lyDoChet`) VALUES (null, '" + ID + "', '" + soGiayKhaiTu + "', '" + idNguoiKhai + "', '" + idNguoiChet + "', '" + ngayKhai + "', '" + ngayChet + "', '" + lyDoChet + "');";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
//        try {
//
//            Connection connection = MySQLConnector.getConnection();
//            Statement statement = connection.createStatement();
//            String query = "";
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return null;
    }

    public int soNguoiCachLy() {
        try {

            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "select count('ID') from `quan_ly_nhan_khau`.`cach_ly`  ;";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
