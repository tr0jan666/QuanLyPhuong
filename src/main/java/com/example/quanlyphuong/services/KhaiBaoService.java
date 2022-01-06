package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.KhaiBaoBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhaiBaoService {
    public List<KhaiBaoBean> getListNhanKhauKhaiBao() {
        List<KhaiBaoBean> list = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM khai_bao " +
                    "INNER JOIN nhan_khau ON cach_ly.idNhanKhau = nhan_khau.ID " +
                    "INNER JOIN cach_ly ON khai_bao.idNhanKhau =cach_ly.idNhanKhau " +
                    "INNER JOIN chung_minh_thu ON cach_ly.idNhanKhau = chung_minh_thu.idNhanKhau";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                KhaiBaoBean khaiBaoBean = new KhaiBaoBean();
                KhaiBaoModel khaiBaoModel = new KhaiBaoModel();

                CachLyModel cachLyModel = new CachLyModel();

                NhanKhauBean nhanKhauBean = new NhanKhauBean();

                NhanKhauModel nhanKhau = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();

               // System.out.println(rs.getString("hoTen"));
                nhanKhau.setID(rs.getInt("nhan_khau.ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));


                chungMinhThuModel.setIdNhanKhau(rs.getInt("chung_minh_thu.idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));

                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));

                cachLyModel.setID(rs.getInt("cach_ly.ID"));
                cachLyModel.setDiaDiemCachLy(rs.getString("noiCachLy"));
                cachLyModel.setThoiGianBatDau(rs.getString("thoiGianBatDau"));
                cachLyModel.setThoiGianKetThuc(rs.getString("thoiGianKetThuc"));
                cachLyModel.setMucDo(rs.getInt("mucDoCachLy"));

                khaiBaoModel.setID(rs.getInt("khai_bao.ID"));
                khaiBaoModel.setNgayKhaiBao(rs.getDate("ngayKhaiBao"));
                khaiBaoModel.setBieuHien(rs.getString("bieuHien"));
                khaiBaoModel.setVungDich(rs.getString("vungDich"));


                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                khaiBaoBean.setNhanKhauBean(nhanKhauBean);
                khaiBaoBean.setCachLyModel(cachLyModel);
                khaiBaoBean.setKhaiBaoModel(khaiBaoModel);

                list.add(khaiBaoBean);

            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public SimpleResult deleteKhaiBao(KhaiBaoBean bean){
        int idKhaiBao= bean.getKhaiBaoModel().getID();
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM khai_bao WHERE khai_bao.ID =  "+ idKhaiBao;

            statement.executeUpdate(query);
            return new SimpleResult(true, "xóa thành công");

        }catch(Exception e){
//            this.exceptionHandle(mysqlException.getMessage());
            return new SimpleResult(false, e.getMessage());

        }
    }
//    public SimpleResult updateCachLy(int id, String noiCachLy, String start,  int mucdo) throws SQLException {
//        try {
//            Connection connection = MySQLConnector.getConnection();
//            Statement statement = connection.createStatement();
//
//
//            String query = "UPDATE cach_ly SET noiCachLy = '" + noiCachLy +
//                    "',thoiGianBatDau ='" + start + "'" +
//                    ",thoiGianKetThuc = '" +
//                    "',mucDoCachLy = " + String.valueOf(mucdo)
//                    + " WHERE idNhanKhau = " + id + "";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            connection.close();
//            return new SimpleResult(true, "cap nhập thành công");
//        }catch (Exception e){
//            return new SimpleResult(false, e.getMessage());
//        }
//    }
    public SimpleResult addKhaiBao(KhaiBaoBean bean) throws SQLException{
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO  Khai_bao (`idNhanKhau`, `vungdich`, `thoiGianKhaiBao`,`bieuHien`) VALUES (?,?,?,?) ";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setInt(1,bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setString(2, bean.getKhaiBaoModel().getVungDich());
            preparedStatement.setDate(3, (Date) bean.getKhaiBaoModel().getNgayKhaiBao());
            preparedStatement.setString(4, bean.getKhaiBaoModel().getBieuHien());


            preparedStatement.executeUpdate();
            //statement.executeUpdate(query);

            preparedStatement.close();
            connection.close();
            System.out.println("them thanh cong");
            return new SimpleResult(true, "thêm thành công");

        }catch(Exception e){
            return new SimpleResult(false, e.getMessage());

        }
    }
}
