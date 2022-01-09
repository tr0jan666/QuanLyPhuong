package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.beans.TiemChungBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.TiemChungModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TiemChungService {
    public List<TiemChungBean> getListTiemChung(){
        List<TiemChungBean> list = new ArrayList<>();
        try{
            Connection connection = MySQLConnector.getConnection();
            String query = "select * from `tiem_chung` as tc join `nhan_khau` as nk on nk.`ID` = tc.`idNhanKhau`" +
                    " join `chung_minh_thu` as cmt on cmt.`idNhanKhau` = tc.`idNhanKhau`";
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){

                TiemChungBean bean = new TiemChungBean();
                TiemChungModel tiemChungModel = new TiemChungModel();
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhauModel = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new  ChungMinhThuModel();

                nhanKhauModel.setHo_ten(resultSet.getString("hoTen"));
                nhanKhauModel.setNamSinh(resultSet.getDate("namSinh"));
                nhanKhauModel.setDiaChiHienNay(resultSet.getString("diaChiHienNay"));
                nhanKhauModel.setGioiTinh(resultSet.getInt("gioiTinh"));
                nhanKhauModel.setID(resultSet.getInt("ID"));
                nhanKhauModel.setNguyenQuan(resultSet.getString("nguyenQuan"));
                nhanKhauModel.setDanToc(resultSet.getString("danToc"));
                nhanKhauModel.setTonGiao(resultSet.getString("tonGiao"));
                nhanKhauModel.setQuocTich(resultSet.getString("quocTich"));
                nhanKhauModel.setMaNhanKhau(resultSet.getString("maNhanKhau"));

                chungMinhThuModel.setNoiCap(resultSet.getString("noiCap"));
                chungMinhThuModel.setNgayCap(resultSet.getDate("ngayCap"));
                chungMinhThuModel.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(resultSet.getString("soCMT"));

                tiemChungModel.setIdTiemChung(resultSet.getInt("idTiemChung"));
                tiemChungModel.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                tiemChungModel.setSoLanTiem(resultSet.getInt("soLanTiem"));
                tiemChungModel.setNgayTiem(resultSet.getString("ngayTiem"));
                tiemChungModel.setVacxin(resultSet.getString("vacxin"));
                tiemChungModel.setDiaDiem(resultSet.getString("diaDiem"));

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

    public int getTiemChung(String cccd){
        List<TiemChungBean> list = new ArrayList<>();
        int sum =0 ;
        try{
            Connection connection = MySQLConnector.getConnection();
            String query = "select * from `tiem_chung` as tc join `nhan_khau` as nk on nk.`ID` = tc.`idNhanKhau`" +
                    " join `chung_minh_thu` as cmt on cmt.`idNhanKhau` = tc.`idNhanKhau` where cmt.soCMT = " + cccd;
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){

                TiemChungBean bean = new TiemChungBean();
                TiemChungModel tiemChungModel = new TiemChungModel();
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhauModel = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();

                nhanKhauModel.setHo_ten(resultSet.getString("hoTen"));
                nhanKhauModel.setNamSinh(resultSet.getDate("namSinh"));
                nhanKhauModel.setDiaChiHienNay(resultSet.getString("diaChiHienNay"));
                nhanKhauModel.setGioiTinh(resultSet.getInt("gioiTinh"));
                nhanKhauModel.setID(resultSet.getInt("ID"));
                nhanKhauModel.setNguyenQuan(resultSet.getString("nguyenQuan"));
                nhanKhauModel.setDanToc(resultSet.getString("danToc"));
                nhanKhauModel.setTonGiao(resultSet.getString("tonGiao"));
                nhanKhauModel.setQuocTich(resultSet.getString("quocTich"));
                nhanKhauModel.setMaNhanKhau(resultSet.getString("maNhanKhau"));

                chungMinhThuModel.setNoiCap(resultSet.getString("noiCap"));
                chungMinhThuModel.setNgayCap(resultSet.getDate("ngayCap"));
                chungMinhThuModel.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(resultSet.getString("soCMT"));

                tiemChungModel.setIdTiemChung(resultSet.getInt("idTiemChung"));
                tiemChungModel.setIdNhanKhau(resultSet.getInt("idNhanKhau"));
                tiemChungModel.setSoLanTiem(resultSet.getInt("soLanTiem"));
                tiemChungModel.setNgayTiem(resultSet.getString("ngayTiem"));
                tiemChungModel.setVacxin(resultSet.getString("vacxin"));
                tiemChungModel.setDiaDiem(resultSet.getString("diaDiem"));

                nhanKhauBean.setNhanKhauModel(nhanKhauModel);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                bean.setTiemChungModel(tiemChungModel);
                bean.setNhanKhauBean(nhanKhauBean);

                list.add(bean);
                sum++;
            }
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sum;
    }

    public TiemChungModel getTiemChungL1(int idNhanKhau){
        TiemChungModel tiemChungModel = new TiemChungModel();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "Select * from `tiem_chung` where `idNhanKhau` = " + idNhanKhau  ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            tiemChungModel.setSoLanTiem(1);
            tiemChungModel.setVacxin(resultSet.getString("vacxin"));
            tiemChungModel.setNgayTiem(resultSet.getString("ngayTiem"));

            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return tiemChungModel;
    }

//    public TiemChungModel getTiemChungL2(int idNhanKhau){
//        TiemChungModel tiemChungModel = new TiemChungModel();
//        try {
//            Connection connection = MysqlConnection.getMysqlConnection();
//            String query = "Select * from `tiem_chung` where `idNhanKhau` = " + idNhanKhau + "and `soLanTiem` = 2" ;
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            tiemChungModel.setSoLanTiem(1);
//            tiemChungModel.setVacxin(resultSet.getString("vacxin"));
//            tiemChungModel.setNgayTiem(resultSet.getDate("ngayTiem"));
//
//            preparedStatement.close();
//            connection.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return tiemChungModel;
//    }

    public void addTiemChung(TiemChungBean bean){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "insert into `tiem_chung` (`idNhanKhau`,`soLanTiem`,`ngayTiem`,`vacxin`, `diaDiem`) value(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setInt(2,bean.getTiemChungModel().getSoLanTiem());
            preparedStatement.setString(3,bean.getTiemChungModel().getNgayTiem() );
            preparedStatement.setString(4,bean.getTiemChungModel().getVacxin());
            preparedStatement.setString(5, bean.getTiemChungModel().getDiaDiem());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("them thanh cong");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTiemChung(int idNhanKhau, String loaiVacxin, String ngayTiem,String diaDiem,int tiemLan){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "update tiem_chung set vacxin = '" + loaiVacxin + "',ngayTiem = '" + ngayTiem+
                    "' , diaDiem = '"+ diaDiem +"' where idNhanKhau = " + idNhanKhau +" and soLanTiem = " + tiemLan + "";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTiemChung2(int idNhanKhau,String loaiVacxin,String ngayTiem){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "update tiem_chung set vacxin = " + loaiVacxin + ",ngayTiem = " + ngayTiem+
                    "where idNhanKhau = " + idNhanKhau +"and soLanTiem = 2";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getIDfromCCCD(int cccd){
        int ID = 0;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "select ID from nhan_khau nk,chung_minh_thu cmt where cmt.idNhanKhau = nk.ID and cmt.soCMT = " + cccd;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ID = rs.getInt("ID");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ID;
    }
}
