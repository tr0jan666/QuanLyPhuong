package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.CachLyBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CachLyService {

    public List<CachLyBean> getListNhanKhauCachLy() {
        List<CachLyBean> list = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM cach_ly INNER JOIN nhan_khau ON cach_ly.idNhanKhau = nhan_khau.ID INNER JOIN chung_minh_thu ON cach_ly.idNhanKhau = chung_minh_thu.idNhanKhau";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("pn check");

            while (rs.next()) {
                CachLyBean cachLyBean = new CachLyBean();

                CachLyModel cachLyModel = new CachLyModel();

                NhanKhauBean nhanKhauBean = new NhanKhauBean();

                NhanKhauModel nhanKhau = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();

                System.out.println(rs.getString("hoTen"));
                nhanKhau.setID(rs.getInt("nhan_khau.ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
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

                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                cachLyBean.setNhanKhauBean(nhanKhauBean);
                cachLyBean.setCachLyModel(cachLyModel);

                list.add(cachLyBean);

            }


            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public SimpleResult deleteCachLy(CachLyBean bean){
        int idCachLy= bean.getCachLyModel().getID();
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM cach_ly WHERE cach_ly.ID =  "+ idCachLy;

            statement.executeUpdate(query);
            return new SimpleResult(true, "xóa thành công");

        }catch(Exception e){
//            this.exceptionHandle(mysqlException.getMessage());
            return new SimpleResult(false, e.getMessage());

        }
    }
    public SimpleResult updateCachLy(int id, String noiCachLy, String start, String end, int mucdo) throws SQLException {
        try {
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();


            String query = "UPDATE cach_ly SET noiCachLy = '" + noiCachLy +
                    "',thoiGianBatDau ='" + start + "'" +
                    ",thoiGianKetThuc = '" + end +
                    "',mucDoCachLy = " + String.valueOf(mucdo)
                    + " WHERE idNhanKhau = " + id + "";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return new SimpleResult(true, "cap nhập thành công");
        }catch (Exception e){
            return new SimpleResult(false, e.getMessage());
        }
    }
    public SimpleResult addCachLy(CachLyBean bean) throws SQLException{
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO  cach_ly (`idNhanKhau`, `noiCachLy`, `thoiGianBatDau`,`mucDoCachLy`,`thoiGianKetThuc`) VALUES (?,?,?,?,?) ";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setInt(1,bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setString(2, bean.getCachLyModel().getDiaDiemCachLy());
            preparedStatement.setString(3, bean.getCachLyModel().getThoiGianBatDau());
            preparedStatement.setInt(4, bean.getCachLyModel().getMucDo());
            preparedStatement.setString(5, bean.getCachLyModel().getThoiGianKetThuc());

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
