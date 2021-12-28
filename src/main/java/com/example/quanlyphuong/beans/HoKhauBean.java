package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.ThanhVienCuaHoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class HoKhauBean {
    public Connection connection;
    private HoKhauModel hoKhauModel;
    private NhanKhauModel chuHo;
    private List<NhanKhauModel> listNhanKhauModels;
    private List<ThanhVienCuaHoModel> listThanhVienCuaHo;

    public HoKhauModel getHoKhauModel() {
        return hoKhauModel;
    }

    public void setHoKhauModel(HoKhauModel hoKhauModel) {
        this.hoKhauModel = hoKhauModel;
    }

    public NhanKhauModel getChuHo() {
        return chuHo;
    }

    public void setChuHo(NhanKhauModel chuHo) {
        this.chuHo = chuHo;
    }

    public List<NhanKhauModel> getListNhanKhauModels() {
        return listNhanKhauModels;
    }

    public void setListNhanKhauModels(List<NhanKhauModel> listNhanKhauModels) {
        this.listNhanKhauModels = listNhanKhauModels;
    }

    public List<ThanhVienCuaHoModel> getListThanhVienCuaHo() {
        return listThanhVienCuaHo;
    }

    public void setListThanhVienCuaHo(List<ThanhVienCuaHoModel> listThanhVienCuaHo) {
        this.listThanhVienCuaHo = listThanhVienCuaHo;
    }

    public HoKhauBean() {
        this.connection = MySQLConnector.getConnection();
    }
    public void delete(HoKhauModel hoKhauInfo){
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE from HoKhauBean where ID = ? and maHoKhau = ? and idChuho = ? and maKhuVuc = ? and diachi = ? and ngayLap = ? and ngayChuyDi = ? and lyDoChuyen = ? and nguoiThucHien = ?");
            stmt.setInt(1, hoKhauInfo.getID());
            stmt.setString(2, hoKhauInfo.getMaHoKhau());
            stmt.setInt(3, hoKhauInfo.getIdChuHo());
            stmt.setString(4, hoKhauInfo.getMaKhuVuc());
            stmt.setString(5, hoKhauInfo.getDiaChi());
            //stmt.setString(6, hoKhauInfo.getNgayLap());
            //stmt.setString(7, hoKhauInfo.getNgayChuyDi());
            stmt.setString(8, hoKhauInfo.getLyDoChuyen());
            stmt.setInt(9, hoKhauInfo.getNguoiThucHien());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
