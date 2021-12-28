package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.*;
import com.example.quanlyphuong.models.KhaiTuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.time.ZoneId;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class NhanKhauService {

    private static final NhanKhauService INSTANCE = new NhanKhauService();

    public static NhanKhauService getInstance() {
        return INSTANCE;
    }

    public NhanKhauModel getDetail(int idNhanKhau) {
        //write code here

        return null;
    }

    public SimpleResult xoaNhanKhau(int idNhanKhau) {
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `quan_ly_nhan_khau`.`nhan_khau` WHERE (`ID` = '" + idNhanKhau + "');";
            statement.executeUpdate(query);
        }catch(Exception mysqlException){
//            this.exceptionHandle(mysqlException.getMessage());
            System.out.println(mysqlException);
        }

        return null;
    }

    public SimpleResult khaiTuNhanKhau(int idNhanKhau) {
//        try {
//
//            NhanKhauModel nguoiChet = getDetail(idNhanKhau);
//
//
//
//            int ID = .getID();
//            String soCMTnguoiChet = .getSoCMTnguoiMat();
//            String soCMTnguoiKhai = .getSoCMTnguoiKhai();
//            String tenNguoiKhai = .getTenNguoiKhai();
//            String soGiayKhaiTu = .getSoGiayKhaiTu();
//            String ngayKhai = .getNgayKhai();
//            String ngayMat = .getNgayMat();
//            String lyDoChet = nguoiChet.getLyDoChet();
//            Connection connection = MysqlConnection.getMysqlConnection();
//            Statement statement = connection.createStatement();
//            String query = "INSERT INTO `QuanLyNhanKhau`.`khai_tu` (`ID`, `soCMTnguoiChet`, `soCMTnguoiKhai`, `tenNguoiKhai`, `soGiayKhaiTu`, `ngayKhai`, `ngayMat`, `lyDoChet`) VALUES (null, '" + soCMTnguoiChet + "', '" + soCMTnguoiKhai + "', '" + tenNguoiKhai + "', '" + soGiayKhaiTu + "', '" + ngayKhai + "', '" + ngayMat + "', '" + lyDoChet + "');";
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public SimpleResult khaiTuNhanKhau(KhaiTuModel khaiTuModel) {
        try {
            int ID = khaiTuModel.getID();
            String soGiayKhaiTu = khaiTuModel.getSoGiayKhaiTu();
            int idNguoiKhai = khaiTuModel.getIdNguoiKhai();
            int idNguoiChet = khaiTuModel.getIdNguoiChet();
            Date ngayKhai = khaiTuModel.getNgayKhai();
            Date ngayChet = khaiTuModel.getNgayChet();
            String lyDoChet = khaiTuModel.getLyDoChet();
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO `quan_ly_nhan_khau`.`khai_tu` (`ID`, `soGiayKhaiTu`, `idNguoiKhai`, `idNguoiChet`, `ngayKhai`, `ngayChet`, `lyDoChet`) VALUES (null, '" + ID + "', '" + soGiayKhaiTu + "', '" + idNguoiKhai + "', '" + idNguoiChet + "', '" + ngayKhai + "', '" + ngayChet + "', '" + lyDoChet + "');";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public SimpleResult taoNhanKhau(NhanKhauModel nhanKhau){
//        nhanKhau.setHo_ten(tf_ten.getText());
//        nhanKhau.setNamSinh(java.sql.Date.from(dp_ngaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        nhanKhau.setGioiTinh(cb_gioiTinh.getValue().toString());
//        nhanKhau.setQuocTich(tf_quocTich.getText());
//        nhanKhau.setNoiSinh(tf_noiSinh.getText());
//        nhanKhau.setNguyenQuan(tf_nguyenQuan.getText());
//        nhanKhau.setDanToc(tf_danToc.getText());
//        nhanKhau.setTonGiao(tf_tonGiao.getText());
//        nhanKhau.setTienAn(tf_tienAn.getText());
//        nhanKhau.setTrinhDoHocVan(tf_hocVan.getText());
//        nhanKhau.setDiaChiHienNay(tf_diaChiHienTai.getText());
//        nhanKhau.setNgheNghiep(tf_ngheNghiep.getText());
//        nhanKhau.setNoiLamViec(tf_noiLamViec.getText());
//        nhanKhau.setNoiThuongTru(tf_thuongTru.getText());
//        nhanKhau.setIdNguoiTao(AuthService.getInstance().getCurrentUser().getID());
//
//
//
//    }

    public SimpleResult taoNhanKhau(NhanKhauModel nhanKhauMoi) {
        // lay thong tin input
        String maNhanKhau = nhanKhauMoi.getMaNhanKhau();
        String Ho_ten = nhanKhauMoi.getHo_ten();
        java.sql.Date namSinh =  new java.sql.Date(nhanKhauMoi.getNamSinh().getTime());
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
        int status = nhanKhauMoi.getStatus();
        int lastUpadate = nhanKhauMoi.getLastUpadate();

        try(Connection connection = MySQLConnector.getConnection()){
            // ket noi voi data_base
            String query = "INSERT INTO `quan_ly_nhan_khau_new`.`nhan_khau` (`maNhanKhau`, `hoTen`, `namSinh`, `gioiTinh`, `noiSinh`, `nguyenQuan`," +
                    " `danToc`, `tonGiao`, `quocTich`, `soHoChieu`, `noiThuongTru`, `diaChiHienNay`," +
                    " `trinhDoHocVan`, `ngheNghiep`, `noiLamViec`, `tienAn`, `ngayChuyenDen`, `lyDoChuyenDen`, `ngayChuyenDi`," +
                    " `lyDoChuyenDi`, `diaChiMoi`,`ngayTao`, `idNguoiTao`, `ngayXoa`, `idNguoiXoa`, `lyDoXoa`, `ghiChu`, `status`, `lastUpdate`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setString(1, maNhanKhau);
            preparedStatement.setString(2, Ho_ten);
            preparedStatement.setDate(3, namSinh);
            preparedStatement.setString(4, gioiTinh);
            preparedStatement.setString(5, noiSinh );
            preparedStatement.setString(6, nguyenQuan);
            preparedStatement.setString(7, danToc );
            preparedStatement.setString(8, tonGiao);
            preparedStatement.setString(9, quocTich );
            preparedStatement.setString(10, soHoChieu );
            preparedStatement.setString(11, noiThuongTru);
            preparedStatement.setString(12, diaChiHienNay );
            preparedStatement.setString(13, trinhDoHocVan);
            preparedStatement.setString(14, ngheNghiep );
            preparedStatement.setString(15, noiLamViec);
            preparedStatement.setString(16, tienAn );
            preparedStatement.setDate(17, (java.sql.Date) ngayChuyenDen);
            preparedStatement.setString(18, lyDoChuyenDen );
            preparedStatement.setDate(19, (java.sql.Date) ngayChuyenDi);
            preparedStatement.setString(20, lyDoChuyenDi);
            preparedStatement.setString(21, diaChiMoi );
            preparedStatement.setDate(22, (java.sql.Date) ngayTao);
            preparedStatement.setInt(23, idNguoiTao);
            preparedStatement.setDate(24, (java.sql.Date) ngayXoa);
            preparedStatement.setInt(25, idNguoiXoa);
            preparedStatement.setString(26, lyDoXoa);
            preparedStatement.setString(27, ghiChu);
            preparedStatement.setInt(28, idNguoiXoa);
            preparedStatement.setInt(29, idNguoiXoa);

            preparedStatement.executeQuery();
            // dong co so du lieu
            preparedStatement.close();
            connection.close();

            return new SimpleResult(true, "Tao thong tin thanh cong");
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
        int idNguoiTao = 1;
        Date ngayXoa= nhanKhauMoi.getNgayXoa();
        int idNguoiXoa= nhanKhauMoi.getIdNguoiXoa();
        String lyDoXoa= nhanKhauMoi.getLyDoXoa();
        String ghiChu= nhanKhauMoi.getGhiChu();

        try(Connection connection = MySQLConnector.getConnection()){
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
            connection.close();

            return new SimpleResult(true, "Sua thong tin thanh cong");
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

        // truy van id trong tabel nhan_khau
        try (Connection connection = MySQLConnector.getConnection()){
            String query = "SELECT * FROM ((nhan_khau "
                    + "INNER JOIN thanh_vien_cua_ho ON nhan_khau.maNhanKhau = thanh_vien_cua_ho.idNhanKhau ) "
                    + "INNER JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.maHoKhau ) "
                    + "WHERE " + column +" LIKE '%" + keyword + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<NhanKhauModel> list_nhanKhau = new ArrayList<>();

            while (rs.next()) {
                NhanKhauModel nhanKhau = new NhanKhauModel();
                HoKhauModel hoKhau = new HoKhauModel();

                nhanKhau.setHo_ten(rs.getString("Ho_ten"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                hoKhau.setIdChuHo(rs.getInt("idChuHo"));
                hoKhau.setMaHoKhau(rs.getString("maHoKhau"));
                nhanKhau.setThongTinHoKhau(hoKhau);

                list_nhanKhau.add(nhanKhau);
            }

            // dong co so du lieu
            preparedStatement.close();
            connection.close();

            return list_nhanKhau;
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<NhanKhauModel> filterNhanKhau(Map<NhanKhauFilterEnum, String> filterOptions) {

        // Lay du lieu input
        String columns = "";
        for (Map.Entry<NhanKhauFilterEnum, String> option : filterOptions.entrySet()){
            columns = columns + ',' +option;
        }

        try(Connection connection = MySQLConnector.getConnection()){
            String query = "SELECT "+columns+" FROM ((nhan_khau"
                    + "INNER JOIN thanh_vien_cua_ho ON nhan_khau.maNhanKhau = thanh_vien_cua_ho.idNhanKhau ) "
                    + "INNER JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.maHoKhau ) ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<NhanKhauModel> list_nhanKhau = new ArrayList<>();

            while (rs.next()) {
                NhanKhauModel nhanKhau = new NhanKhauModel();
                HoKhauModel hoKhau = new HoKhauModel();

                nhanKhau.setHo_ten(rs.getString("Ho_ten"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                hoKhau.setIdChuHo(rs.getInt("idChuHo"));
                hoKhau.setMaHoKhau(rs.getString("maHoKhau"));
                nhanKhau.setThongTinHoKhau(hoKhau);

                list_nhanKhau.add(nhanKhau);
            }

            // dong co so du lieu
            preparedStatement.close();
            connection.close();

            return list_nhanKhau;
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String countTiLeTiem1Vacxin() {
        return null;
    }

    public String countTiLeTiem2Vacxin() {
        return null;
    }

    public String countTiLeTuVong() {
//        try {
//
//            Connection connection = MySQLConnector.getConnection();
//            Statement statement = connection.createStatement();
//            String query = "";
//            statement.executeUpdate(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return null;
    }

    public int soNguoiCachLy() {
        try {

            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "select count('ID') from `quan_ly_nhan_khau`.`cach_ly`  ;";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


}
