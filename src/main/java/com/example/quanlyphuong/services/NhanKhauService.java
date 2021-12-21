package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TiemChungModel;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public SimpleResult taoNhanKhau(NhanKhauModel nhanKhauMoi) {

        // lay thong tin input
        int ID = nhanKhauMoi.getID();
        String maNhanKhau = nhanKhauMoi.getMaNhanKhau();
        String Ho_ten = nhanKhauMoi.getHo_ten();
        Date namSinh = nhanKhauMoi.getNamSinh();
        String gioiTinh = nhanKhauMoi.getGioiTinh();
        String noiSinh = nhanKhauMoi.getNoiSinh();
        String nguyenQuan = nhanKhauMoi.getNguyenQuan();
        String danToc = nhanKhauMoi.getDanToc();
        String tonGiao = nhanKhauMoi.getTonGiao();
        String quocTich= nhanKhauMoi.getQuocTich();
        String noiThuongTru= nhanKhauMoi.getNoiThuongTru();
        String soHoChieu= nhanKhauMoi.getSoHoChieu();
        String diaChiHienNay= nhanKhauMoi.getDiaChiHienNay();
        String trinhDoHocVan= nhanKhauMoi.getTrinhDoHocVan();
        String ngheNghiep= nhanKhauMoi.getNgheNghiep();
        String noiLamViec= nhanKhauMoi.getNoiLamViec();
        String tienAn= nhanKhauMoi.getTienAn();
        Date ngayChuyenDen= nhanKhauMoi.getNgayChuyenDen();
        String lyDoChuyenDen= nhanKhauMoi.getLyDoChuyenDen();
        Date ngayChuyenDi= nhanKhauMoi.getNgayChuyenDi();
        String lyDoChuyenDi= nhanKhauMoi.getLyDoChuyenDi();
        String diaChiMoi= nhanKhauMoi.getDiaChiMoi();
        Date ngayTao= nhanKhauMoi.getNgayTao();
        int idNguoiTao= nhanKhauMoi.getIdNguoiTao();
        Date ngayXoa= nhanKhauMoi.getNgayXoa();
        int idNguoiXoa= nhanKhauMoi.getIdNguoiXoa();
        String lyDoXoa= nhanKhauMoi.getLyDoXoa();
        String ghiChu= nhanKhauMoi.getGhiChu();

        try{
            // ket noi voi data_base
            Connection connection = MySQLConnector.getConnection();
            String query = "INSERT INTO nhan_khau VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, maNhanKhau);
            preparedStatement.setString(3, Ho_ten);
            preparedStatement.setDate(4, namSinh);
            preparedStatement.setString(5, gioiTinh);
            preparedStatement.setString(6, noiSinh );
            preparedStatement.setString(7, nguyenQuan);
            preparedStatement.setString(8, danToc );
            preparedStatement.setString(9, tonGiao);
            preparedStatement.setString(10, quocTich );
            preparedStatement.setString(11, noiThuongTru);
            preparedStatement.setString(12, soHoChieu );
            preparedStatement.setString(13, diaChiHienNay );
            preparedStatement.setString(14, trinhDoHocVan);
            preparedStatement.setString(15, ngheNghiep );
            preparedStatement.setString(16, noiLamViec);
            preparedStatement.setString(17, tienAn );
            preparedStatement.setDate(18, ngayChuyenDen);
            preparedStatement.setString(19, lyDoChuyenDen );
            preparedStatement.setDate(20, ngayChuyenDi);
            preparedStatement.setString(21, lyDoChuyenDi);
            preparedStatement.setString(22, diaChiMoi );
            preparedStatement.setDate(23, ngayTao);
            preparedStatement.setInt(24, idNguoiTao);
            preparedStatement.setDate(25, ngayXoa);
            preparedStatement.setInt(26, idNguoiXoa);
            preparedStatement.setString(27, lyDoXoa);
            preparedStatement.setString(28, ghiChu);

            // dong co so du lieu
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
    }

    public SimpleResult suaNhanKhau(NhanKhauModel nhanKhauMoi) {

        // lay thong tin input
        int ID = nhanKhauMoi.getID();
        String maNhanKhau = nhanKhauMoi.getMaNhanKhau();
        String Ho_ten = nhanKhauMoi.getHo_ten();
        Date namSinh = nhanKhauMoi.getNamSinh();
        String gioiTinh = nhanKhauMoi.getGioiTinh();
        String noiSinh = nhanKhauMoi.getNoiSinh();
        String nguyenQuan = nhanKhauMoi.getNguyenQuan();
        String danToc = nhanKhauMoi.getDanToc();
        String tonGiao = nhanKhauMoi.getTonGiao();
        String quocTich= nhanKhauMoi.getQuocTich();
        String noiThuongTru= nhanKhauMoi.getNoiThuongTru();
        String soHoChieu= nhanKhauMoi.getSoHoChieu();
        String diaChiHienNay= nhanKhauMoi.getDiaChiHienNay();
        String trinhDoHocVan= nhanKhauMoi.getTrinhDoHocVan();
        String ngheNghiep= nhanKhauMoi.getNgheNghiep();
        String noiLamViec= nhanKhauMoi.getNoiLamViec();
        String tienAn= nhanKhauMoi.getTienAn();
        Date ngayChuyenDen= nhanKhauMoi.getNgayChuyenDen();
        String lyDoChuyenDen= nhanKhauMoi.getLyDoChuyenDen();
        Date ngayChuyenDi= nhanKhauMoi.getNgayChuyenDi();
        String lyDoChuyenDi= nhanKhauMoi.getLyDoChuyenDi();
        String diaChiMoi= nhanKhauMoi.getDiaChiMoi();
        Date ngayTao= nhanKhauMoi.getNgayTao();
        int idNguoiTao= nhanKhauMoi.getIdNguoiTao();
        Date ngayXoa= nhanKhauMoi.getNgayXoa();
        int idNguoiXoa= nhanKhauMoi.getIdNguoiXoa();
        String lyDoXoa= nhanKhauMoi.getLyDoXoa();
        String ghiChu= nhanKhauMoi.getGhiChu();

        try{
            Connection connection = MySQLConnector.getConnection();
            String query = "UPDATE nhan_khau SET "
                    + "maNhanKhau = " + maNhanKhau
                    + "Ho_ten = " + Ho_ten
                    + "namSinh = " + namSinh
                    + "gioiTinh = " + gioiTinh
                    + "noiSinh = " + noiSinh
                    + "nguyenQuan = " + nguyenQuan
                    + "danToc = " + danToc
                    + "tonGiao = " + tonGiao
                    + "quocTich = " + quocTich
                    + "noiThuongTru = " + noiThuongTru
                    + "soHoChieu = " + soHoChieu
                    + "diaChiHienNay = " + diaChiHienNay
                    + "trinhDoHocVan = " + trinhDoHocVan
                    + "ngheNghiep = " + ngheNghiep
                    + "noiLamViec = " + noiLamViec
                    + "tienAn = " + tienAn
                    + "ngayChuyenDen = " + ngayChuyenDen
                    + "lyDoChuyenDen = " + lyDoChuyenDen
                    + "ngayChuyenDi = " + ngayChuyenDi
                    + "lyDoChuyenDi = " + lyDoChuyenDi
                    + "diaChiMoi = " + diaChiMoi
                    + "ngayTao = " + ngayTao
                    + "idNguoiTao = " + idNguoiTao
                    + "ngayXoa = " + ngayXoa
                    + "idNguoiXoa = " + idNguoiXoa
                    + "lyDoXoa = " + lyDoXoa
                    + "ghiChu = " + ghiChu
                    + "WHERE ID =" + ID;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
    }

    public ArrayList<NhanKhauModel> timKiemNhanKhau(String keyword, NhanKhauFilterEnum filterType) {

        // lay thong tin input
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
        try{
            Connection connection = MySQLConnector.getConnection();
            String query = " ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new SimpleResult(false, ex.getMessage());
        }
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
