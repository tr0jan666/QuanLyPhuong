package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.enums.HoKhauFilterEnum;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.ThanhVienCuaHoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoKhauService {

    public ArrayList<HoKhauModel> getListHoKhau() {
        ArrayList<HoKhauModel> list = new ArrayList<>();

        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID ORDER BY ngayTao DESC";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                HoKhauModel hoKhauModel = new HoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idCHuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setLyDoChuyen(rs.getString("lyDoChuyen"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));


                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    ArrayList<NhanKhauModel> listNhanKhau = new ArrayList<>();
                    ArrayList<ThanhVienCuaHoModel> listThanhVienCuaHo = new ArrayList<>();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setHo_ten(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));

                        if(nhanKhauModel.getID() == hoKhauModel.getIdChuHo()){
                            hoKhauModel.setHoTenChuHo(nhanKhauModel.getHo_ten());
                        }
                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }
                    hoKhauModel.setThanhVienCuaHoModelArrayList(listThanhVienCuaHo);
                    hoKhauModel.setListNhanKhau(listNhanKhau);

                } catch (SQLException e) {
                    System.out.println("services.HoKhauService.getListHoKhau()");
                    System.out.println(e.getMessage());
                }
                list.add(hoKhauModel);
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }





    public ArrayList<HoKhauModel> timKiemHoKhau(String keyword, HoKhauFilterEnum filterType) {

        return null;
    }

    public SimpleResult taoHoKhau(HoKhauModel hoKhau) {
        return null;
    }

    public SimpleResult xoaHoKhau(int idHoKhau) {
        return null;
    }

    public SimpleResult suaHoKhau(HoKhauModel hoKhau) {
        return null;
    }

    public SimpleResult tachHoKhau(ArrayList<NhanKhauModel> nhanKhauMoi, int idChuHoMoi) {
        return null;
    }


}
