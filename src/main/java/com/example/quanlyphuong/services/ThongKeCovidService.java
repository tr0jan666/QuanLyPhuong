package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeCovidService {

    public List<NhanKhauBean> statisticNhanKhau(int TuTuoi ,int denTuoi ,String gender ,int cly ,int dtiemmui1,int dtiemmui2 ) {
        List<NhanKhauBean> list = new ArrayList<>();

        String query = "SELECT * FROM nhan_khau"
                + " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
                + " LEFT JOIN cach_ly ON nhan_khau.ID = cach_ly.idNhanKhau "
                + " LEFT JOIN tiem_chung ON nhan_khau.ID = tiem_chung.idNhanKhau "
                + " WHERE ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) >= "
                + TuTuoi
                + " AND ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) <= "
                + denTuoi;




        if(gender.equalsIgnoreCase("Nam")){
            int gt = 1 ;
            query += " AND nhan_khau.gioiTinh = '" + gt + "'";
        }
        if(gender.equalsIgnoreCase("Nu")){
            int gt = 0 ;
            query += " AND nhan_khau.gioiTinh = '" + gt + "'";
        }
// them cach ly va tiem mui 1,2
        if(cly==1){
            query+=" AND cach_ly.mucDoCachLy = '" + cly + "'";
        }
        if(dtiemmui1==1){
            query+=" AND tiem_chung.soLanTiem = '" + dtiemmui1 + "'";
        }
        if(dtiemmui2==2){
            query+=" AND tiem_chung.soLanTiem = '" + dtiemmui2 + "'";
        }

        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()) {
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau =  new NhanKhauModel() ;
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();
                //them cach ly tiem chung
                CachLyModel cachLyModel = new CachLyModel();
                TiemChungModel tiemChungModel = new TiemChungModel();
                cachLyModel.setMucDo(rs.getInt("mucDoCachLy"));
                tiemChungModel.setSoLanTiem(rs.getInt("soLanTiem"));

                idNhanKhau = rs.getInt("idNhanKhau");
                nhanKhau.setID(idNhanKhau);
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                if(nhanKhau.getGioiTinh() == 1){
                    nhanKhau.setGioiTinhString("Nam");
                }else{
                    nhanKhau.setGioiTinhString("Nữ");
                }
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));

                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));

                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
               // them cach ly tiem chung
                nhanKhauBean.setCachLyModel(cachLyModel);
                nhanKhauBean.setTiemChungModel(tiemChungModel);

                list.add(nhanKhauBean);
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
    public List<NhanKhauBean> getListNhanKhau() {
        List<NhanKhauBean> list = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            // String query = "SELECT * FROM nhan_khau ";
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau ORDER BY ngayTao DESC";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println("get list nhk bean");



            while (rs.next()) {
                NhanKhauBean nhanKhauBean = new NhanKhauBean();

                NhanKhauModel nhanKhau = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();
                CachLyModel cachLyModel = new CachLyModel() ;
                TiemChungModel tiemChungModel = new TiemChungModel();
                TestCovidModel testCovidModel = new TestCovidModel();


                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                if(nhanKhau.getGioiTinh() == 1){
                    nhanKhau.setGioiTinhString("Nam");
                }else{
                    nhanKhau.setGioiTinhString("Nữ");
                }
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));

                cachLyModel.setMucDo(rs.getInt("mucDoCachLy"));
                testCovidModel.setKetQua(rs.getBoolean("ketQua"));


                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);

                list.add(nhanKhauBean);

            }


            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }



}
