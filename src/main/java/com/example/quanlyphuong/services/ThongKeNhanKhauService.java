package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.beans.TestCovidBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ThongKeNhanKhauService {
    //TODO @Vuong clean code: move all function to NhanKhauService
    /*
     * Ham lay ra 1 nhan khau trong db bang chung minh thu
     *
     */
    public NhanKhauBean getNhanKhau(String cmt) {
        NhanKhauBean nhanKhauBean = new NhanKhauBean();
        // truy cap db
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = " + cmt;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()) {
                NhanKhauModel nhanKhau = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
                idNhanKhau = rs.getInt("idNhanKhau");
                nhanKhau.setID(idNhanKhau);

                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setSoHoChieu(rs.getString("soHoChieu"));
                nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);

                nhanKhauBean.setNhanKhauModel(nhanKhau);
            }
            preparedStatement.close();
//            if (idNhanKhau > 0) {
////                query = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
////                preparedStatement = (PreparedStatement) connection.prepareStatement(query);
////                rs = preparedStatement.executeQuery();
////                List<TieuSuModel> listTieuSuModels = new ArrayList<>();
////                while (rs.next()) {
////                    TieuSuModel tieuSuModel = new TieuSuModel();
////                    tieuSuModel.setID(rs.getInt("ID"));
////                    tieuSuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
////                    tieuSuModel.setTuNgay(rs.getDate("tuNgay"));
////                    tieuSuModel.setDenNgay(rs.getDate("denNgay"));
////                    tieuSuModel.setDiaChi(rs.getString("diaChi"));
////                    tieuSuModel.setNgheNghiep(rs.getString("ngheNghiep"));
////                    tieuSuModel.setNoiLamViec(rs.getString("noiLamViec"));
////                    listTieuSuModels.add(tieuSuModel);
////                }
////                nhanKhauBean.setListTieuSuModels(listTieuSuModels);
////                preparedStatement.close();
////
////                query = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
////                preparedStatement = (PreparedStatement) connection.prepareStatement(query);
////                rs = preparedStatement.executeQuery();
////                List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
////                while (rs.next()) {
////                    GiaDinhModel giaDinhModel = new GiaDinhModel();
////                    giaDinhModel.setID(rs.getInt("ID"));
////                    giaDinhModel.setHoTen(rs.getString("hoTen"));
////                    giaDinhModel.setNamSinh(rs.getDate("namSinh"));
////                    giaDinhModel.setGioiTinh(rs.getString("gioiTinh"));
////                    giaDinhModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
////                    giaDinhModel.setDiaChiHienTai(rs.getString("diaChiHienTai"));
////                    giaDinhModel.setNgheNghiep(rs.getString("ngheNghiep"));
////                    giaDinhModel.setQuanHeVoiNhanKhau(rs.getString("quanHeVoiNhanKhau"));
////                    listGiaDinhModels.add(giaDinhModel);
////                }
////                nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
////                preparedStatement.close();
//            }
            connection.close();
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return nhanKhauBean;
    }

    // lay danh sach 10 nhan khau moi duoc them vao
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


                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                if(nhanKhau.getGioiTinh()==1){
                    nhanKhau.setGioiTinhString("Nam");
                }
                else{
                    nhanKhau.setGioiTinhString("Nữ");
                }
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));

                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));

                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));


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

    public List<NhanKhauBean> statisticNhanKhau(int TuTuoi, int denTuoi, String gender, String Status, int tuNam, int denNam) {
        List<NhanKhauBean> list = new ArrayList<>();

        String query = "SELECT * FROM nhan_khau "
                + " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
                + " LEFT JOIN tam_tru ON nhan_khau.ID = tam_tru.idNhanKhau "
                + " LEFT JOIN tam_vang ON nhan_khau.ID = tam_vang.idNhanKhau "
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
        if (Status.equalsIgnoreCase("Toan bo")) {
            query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
                    + " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
        } else if (Status.equalsIgnoreCase("Thuong tru")) {
            query += " AND tam_tru.denNgay IS NULL";

        } else if (Status.equalsIgnoreCase("Tam tru")) {
            query += " AND (YEAR(tam_tru.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        } else if (Status.equalsIgnoreCase("Tam vang")) {
            query += " AND (YEAR(tam_vang.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        }
        query += " ORDER BY ngayTao DESC";
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()) {
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau =  new NhanKhauModel() ;
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();
                idNhanKhau = rs.getInt("idNhanKhau");
                nhanKhau.setID(idNhanKhau);

                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                if(nhanKhau.getGioiTinh()==1){
                    nhanKhau.setGioiTinhString("Nam");
                }
                else{
                    nhanKhau.setGioiTinhString("Nữ");
                }
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setSoHoChieu(rs.getString("soHoChieu"));
                nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                // con nhieu nua
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));

//                if (idNhanKhau > 0) {
////                    String sql = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
////                    PreparedStatement prst = (PreparedStatement) connection.prepareStatement(sql);
////                    ResultSet rs_temp = prst.executeQuery();
////                    List<TieuSuModel> listTieuSuModels = new ArrayList<>();
////                    while (rs_temp.next()) {
////                        TieuSuModel tieuSuModel = new TieuSuModel();
////                        tieuSuModel.setID(rs_temp.getInt("ID"));
////                        tieuSuModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
////                        tieuSuModel.setTuNgay(rs_temp.getDate("tuNgay"));
////                        tieuSuModel.setDenNgay(rs_temp.getDate("denNgay"));
////                        tieuSuModel.setDiaChi(rs_temp.getString("diaChi"));
////                        tieuSuModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
////                        tieuSuModel.setNoiLamViec(rs_temp.getString("noiLamViec"));
////                        listTieuSuModels.add(tieuSuModel);
////                    }
////                    nhanKhauBean.setListTieuSuModels(listTieuSuModels);
////                    prst.close();
////
////                    sql = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
////                    prst = (PreparedStatement) connection.prepareStatement(sql);
////                    rs_temp = prst.executeQuery();
////                    List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
////                    while (rs_temp.next()) {
////                        GiaDinhModel giaDinhModel = new GiaDinhModel();
////                        giaDinhModel.setID(rs_temp.getInt("ID"));
////                        giaDinhModel.setHoTen(rs_temp.getString("hoTen"));
////                        giaDinhModel.setNamSinh(rs_temp.getDate("namSinh"));
////                        giaDinhModel.setGioiTinh(rs_temp.getString("gioiTinh"));
////                        giaDinhModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
////                        giaDinhModel.setDiaChiHienTai(rs_temp.getString("diaChiHienTai"));
////                        giaDinhModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
////                        giaDinhModel.setQuanHeVoiNhanKhau(rs_temp.getString("quanHeVoiNhanKhau"));
////                        listGiaDinhModels.add(giaDinhModel);
////                    }
////                    nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
////                    prst.close();
//                }
                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                list.add(nhanKhauBean);
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }


    /*
     * ham tim kiem nhan khau theo ten
     */
    public List<NhanKhauBean> search(String keyword) {
        List<NhanKhauBean> list = new ArrayList<>();
        String query;
        if (keyword.trim().isEmpty()) {
            return this.getListNhanKhau();
        }
        // truy cap db
        // create query
        try {
            long a = Long.parseLong(keyword);
            query = "SELECT * "
                    + "FROM nhan_khau "
                    + "INNER JOIN chung_minh_thu "
                    + "ON nhan_khau.ID = chung_minh_thu.idNhanKhau "
                    + "WHERE chung_minh_thu.soCMT LIKE '%"
                    + keyword
                    + "%'";
        } catch (Exception e) {
            query = "SELECT * "
                    + "FROM nhan_khau "
                    + "INNER JOIN chung_minh_thu "
                    + "ON nhan_khau.ID = chung_minh_thu.idNhanKhau "
                    + "WHERE MATCH(hoTen, bietDanh) AGAINST ('"
                    + keyword
                    + "' IN NATURAL LANGUAGE MODE);";
        }

        // execute query
        try {
            Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NhanKhauBean temp = new NhanKhauBean();
                NhanKhauModel nhanKhau = temp.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));

                ChungMinhThuModel chungMinhThuModel = temp.getChungMinhThuModel();
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
        return list;
    }


    public void khaiTu(int idNguoiChet) {
        try {
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `QuanLyNhanKhau`.`nhan_khau` WHERE (`ID` = '" + idNguoiChet + "');";
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void themKhaiTu(KhaiTuModel khaiTuModel) {
//        try {
//            int ID = khaiTuModel.getID();
//            String soCMTnguoiChet = khaiTuModel.getSoCMTnguoiMat();
//            String soCMTnguoiKhai = khaiTuModel.getSoCMTnguoiKhai();
//            String tenNguoiKhai = khaiTuModel.getTenNguoiKhai();
//            String soGiayKhaiTu = khaiTuModel.getSoGiayKhaiTu();
//            String ngayKhai = khaiTuModel.getNgayKhai();
//            String ngayMat = khaiTuModel.getNgayMat();
//            String lyDoChet = khaiTuModel.getLyDoChet();
//            Connection connection = MySQLConnector.getConnection();
//            Statement statement = connection.createStatement();
//            String query = "INSERT INTO `QuanLyNhanKhau`.`khai_tu` (`ID`, `soCMTnguoiChet`, `soCMTnguoiKhai`, `tenNguoiKhai`, `soGiayKhaiTu`, `ngayKhai`, `ngayMat`, `lyDoChet`) VALUES (null, '" + soCMTnguoiChet + "', '" + soCMTnguoiKhai + "', '" + tenNguoiKhai + "', '" + soGiayKhaiTu + "', '" + ngayKhai + "', '" + ngayMat + "', '" + lyDoChet + "');";
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /*
     * Ham xử lý ngoại lệ : thông báo ra lỗi nhận được
     */
    private void exceptionHandle(String message) {
//         JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
    }
}
