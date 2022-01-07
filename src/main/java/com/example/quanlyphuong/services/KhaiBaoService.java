package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.KhaiBaoBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.KhaiBaoModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhaiBaoService {
    public List<KhaiBaoBean> getListNhanKhauKhaiBao() {
        List<KhaiBaoBean> list = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM khai_bao " +
                    "INNER JOIN nhan_khau ON khai_bao.idNhanKhau = nhan_khau.ID " +
                    "INNER JOIN chung_minh_thu ON khai_bao.idNhanKhau = chung_minh_thu.idNhanKhau";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                KhaiBaoBean khaiBaoBean = new KhaiBaoBean();
                KhaiBaoModel khaiBaoModel = new KhaiBaoModel();

                NhanKhauBean nhanKhauBean = new NhanKhauBean();

                NhanKhauModel nhanKhau = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();

               // System.out.println(rs.getString("hoTen"));
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


                khaiBaoModel.setID(rs.getInt("khai_bao.ID"));
                khaiBaoModel.setNgayKhaiBao(rs.getString("ngayKhaiBao"));
                khaiBaoModel.setBieuHien(rs.getString("bieuHien"));
                khaiBaoModel.setVungDich(rs.getString("vungDich"));


                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                khaiBaoBean.setNhanKhauBean(nhanKhauBean);
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

    public SimpleResult addKhaiBao(KhaiBaoBean bean) throws SQLException{
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO  khai_bao (`idNhanKhau`, `vungdich`, `ngayKhaiBao`,`bieuHien`) VALUES (?,?,?,?) ";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setInt(1,bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setString(2, bean.getKhaiBaoModel().getVungDich());
            preparedStatement.setString(3,  bean.getKhaiBaoModel().getNgayKhaiBao());
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
