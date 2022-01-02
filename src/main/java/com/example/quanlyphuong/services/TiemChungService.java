package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.beans.TiemChungBean;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.TiemChungModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TiemChungService {
    public List<TiemChungBean> getListTiemChung(){
        List<TiemChungBean> list = new ArrayList<>();
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "select * from `tiem_chung` as tc join `nhan_khau` as nk on nk.`ID` = tc.`idNhanKhau`" +
                    " join `chung_minh_thu` as cmt on cmt.`idNhanKhau` = td.`idNhanKhau`";
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){

                TiemChungBean bean = new TiemChungBean();
                TiemChungModel tiemChungModel = bean.getTiemChungModel();
                NhanKhauBean nhanKhauBean = bean.getNhanKhauBean();
                NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();

                nhanKhauModel.setHo_ten(resultSet.getString("hoTen"));
                nhanKhauModel.setNamSinh(resultSet.getDate("namSinh"));
                nhanKhauModel.setDiaChiHienNay(resultSet.getString("diaChiHienNay"));
                nhanKhauModel.setGioiTinh(resultSet.getString("gioiTinh"));
                nhanKhauModel.setID(resultSet.getInt("nhan_khau.ID"));
                nhanKhauModel.setNguyenQuan(resultSet.getString("nguyenQuan"));
                nhanKhauModel.setDanToc(resultSet.getString("danToc"));
                nhanKhauModel.setTonGiao(resultSet.getString("tonGiao"));
                nhanKhauModel.setQuocTich(resultSet.getString("quocTich"));
                nhanKhauModel.setMaNhanKhau(resultSet.getString("maNhanKhau"));

                chungMinhThuModel.setNoiCap(resultSet.getString("noiCap"));
                chungMinhThuModel.setNgayCap(resultSet.getDate("ngayCap"));
                chungMinhThuModel.setIdNhanKhau(resultSet.getInt("chung_minh_thu.idNhanKhau"));
                chungMinhThuModel.setSoCMT(resultSet.getString("soCMT"));

                tiemChungModel.setIdTiemChung(resultSet.getInt("idTiemChung"));
                tiemChungModel.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                tiemChungModel.setSoLanTiem(resultSet.getInt("soLanTiem"));
                tiemChungModel.setNgayTiem(resultSet.getDate("ngayTiem"));
                tiemChungModel.setVacxin(resultSet.getString("vacxin"));

                nhanKhauBean.setNhanKhauModel(nhanKhauModel);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                bean.setTiemChungModel(tiemChungModel);
                bean.setNhanKhauBean(nhanKhauBean);

                list.add(bean);
            }
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public TiemChungModel getTiemChungL1(int idNhanKhau){
        TiemChungModel tiemChungModel = new TiemChungModel();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "Select * from `tiem_chung` where `idNhanKhau` = " + idNhanKhau + "and `soLanTiem` = 1" ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            tiemChungModel.setSoLanTiem(1);
            tiemChungModel.setVacxin(resultSet.getString("vacxin"));
            tiemChungModel.setNgayTiem(resultSet.getDate("ngayTiem"));

            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return tiemChungModel;
    }

    public TiemChungModel getTiemChungL2(int idNhanKhau){
        TiemChungModel tiemChungModel = new TiemChungModel();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "Select * from `tiem_chung` where `idNhanKhau` = " + idNhanKhau + "and `soLanTiem` = 2" ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            tiemChungModel.setSoLanTiem(1);
            tiemChungModel.setVacxin(resultSet.getString("vacxin"));
            tiemChungModel.setNgayTiem(resultSet.getDate("ngayTiem"));

            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return tiemChungModel;
    }

    public void addTiemChung(TiemChungBean bean){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "insert into `tiem_chung` (`idNhanKhau`,`soLanTiem`,`ngayTiem`,'vacxin') value(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setInt(2,bean.getTiemChungModel().getSoLanTiem());
            preparedStatement.setDate(3, (java.sql.Date) bean.getTiemChungModel().getNgayTiem());
            preparedStatement.setString(4,bean.getTiemChungModel().getVacxin());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("them thanh cong");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTiemChung(TiemChungBean tiemChungBean,String loaiVacxin,String ngayTiem){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "update tiem_chung set vacxin = " + loaiVacxin + ",ngayTiem = " + ngayTiem+
                    "where idNhanKhau = " + tiemChungBean.getNhanKhauBean().getNhanKhauModel().getID();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
