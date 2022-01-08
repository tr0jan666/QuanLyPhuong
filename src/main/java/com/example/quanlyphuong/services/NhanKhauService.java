package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.constants.GioiTinhConstant;
import com.example.quanlyphuong.helper.constants.NhanKhauConstant;
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

    public List<NhanKhauBean> getListNhanKhau(){
        List<NhanKhauBean> list = new ArrayList<>();
        try{
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau ORDER BY ngayTao DESC";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau = new NhanKhauModel();
                nhanKhauBean.setNhanKhauModel(nhanKhau);
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHo_ten(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getInt("gioiTinh"));
                if(nhanKhau.getGioiTinh() == GioiTinhConstant.NAM){
                    nhanKhau.setGioiTinhString("Nam");
                }else{
                    nhanKhau.setGioiTinhString("Nữ");
                }

                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
                nhanKhau.setStatus(rs.getInt("status"));
                int status = nhanKhau.getStatus();

                if(status==NhanKhauConstant.THUONG_TRU_STATUS){
                    nhanKhau.setStatusString("Thường trú");
                }else if(status==NhanKhauConstant.TAM_TRU_STATUS){
                    nhanKhau.setStatusString("Tạm trú");
                }else if(status==NhanKhauConstant.TAM_VANG_STATUS){
                    nhanKhau.setStatusString("Tạm vắng");
                }else{
                    continue;
                }
                nhanKhau.setNamSinh(new java.util.Date(rs.getDate("namSinh").getTime()));
                nhanKhau.setNoiSinh(rs.getString("noiSinh"));
                nhanKhau.setTienAn(rs.getString("tienAn"));
                nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhau.setMaNhanKhau(rs.getString("maNhanKhau"));

                list.add(nhanKhauBean);
            }
            preparedStatement.close();
            connection.close();
        }catch(Exception mysqlException){
            System.out.println(mysqlException.getMessage());
        }
        return list;
    }

    public SimpleResult xoaTamTru(NhanKhauBean nhanKhauBean) {
        try{
            NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
            ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `tam_tru` WHERE (`idNhanKhau` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,  cmt.getIdNhanKhau());
            preparedStatement.execute();

            //xoa trong cmt
            String query0 = "delete from chung_minh_thu  " +
                    "where idNhanKhau = ?";

            PreparedStatement preparedStatement0 = connection.prepareStatement(query0);

            // cai dat gia tri
            preparedStatement0.setInt(1, cmt.getIdNhanKhau());
            preparedStatement0.execute();

            //xoa trong nhan khau
            String query1 = "delete from nhan_khau  " +
                    "where ID = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

            // cai dat gia tri
            preparedStatement1.setInt(1, cmt.getIdNhanKhau());
            preparedStatement1.execute();

            connection.close();

            return new SimpleResult(true, "Xoa tam tru thanh cong");
        }catch(Exception mysqlException){
            mysqlException.printStackTrace();
            return new SimpleResult(false, mysqlException.getMessage());
        }
    }

    public SimpleResult xoaTamVang(NhanKhauBean nhanKhauBean) {
        try{
            NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
            ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `tam_vang` WHERE (`idNhanKhau` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,  cmt.getIdNhanKhau());

            preparedStatement.execute();

            //update trong nhan khau
            String query1 = "update nhan_khau set  `status` = ? " +
                    "where ID = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

            // cai dat gia tri
            preparedStatement1.setInt(1, NhanKhauConstant.THUONG_TRU_STATUS);
            preparedStatement1.setInt(2, cmt.getIdNhanKhau());
            preparedStatement1.execute();

            connection.close();

            return new SimpleResult(true, "Xóa tạm vắng thanh cong");
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

    public SimpleResult khaiTuNhanKhau(NhanKhauBean nhanKhauBean) {
        try{
            NhanKhauModel nhanKhauModel = nhanKhauBean.getNhanKhauModel();
            ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM `tam_tru` WHERE (`idNhanKhau` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,  cmt.getIdNhanKhau());
            preparedStatement.execute();

            //xoa trong cmt
            String query0 = "delete from chung_minh_thu  " +
                    "where idNhanKhau = ?";

            PreparedStatement preparedStatement0 = connection.prepareStatement(query0);

            // cai dat gia tri
            preparedStatement0.setInt(1, cmt.getIdNhanKhau());
            preparedStatement0.execute();

            //xoa trong nhan khau
            String query1 = "delete from nhan_khau  " +
                    "where ID = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

            // cai dat gia tri
            preparedStatement1.setInt(1, cmt.getIdNhanKhau());
            preparedStatement1.execute();

            connection.close();

            return new SimpleResult(true, "Xoa tam tru thanh cong");
        }catch(Exception mysqlException){
            mysqlException.printStackTrace();
            return new SimpleResult(false, mysqlException.getMessage());
        }
    }

    public SimpleResult taoTamTru(NhanKhauBean nhanKhauBean, TamTruModel tamTruModel){
        taoNhanKhau(nhanKhauBean);

        String maGiayTamTru = tamTruModel.getMaGiayTamTru();
        String sdt = tamTruModel.getSoDienThoaiNguoiDangKy();
        java.sql.Date tuNgay =  new java.sql.Date(tamTruModel.getTuNgay().getTime());
        java.sql.Date denNgay =  new java.sql.Date(tamTruModel.getDenNgay().getTime());
        String lydo = tamTruModel.getLyDo();
        int idNhanKhau = nhanKhauBean.getChungMinhThuModel().getIdNhanKhau();

        try(Connection connection = MySQLConnector.getConnection()) {
            // ket noi voi data_base
            String query = "INSERT INTO tam_tru (`idNhanKhau`, `maGiayTamTru`, `soDienThoaiNguoiDangKy`, `tuNgay`, `denNgay`,`lyDo`) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, idNhanKhau);
            preparedStatement.setString(2, maGiayTamTru);
            preparedStatement.setString(3, sdt);
            preparedStatement.setDate(4, tuNgay);
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, lydo);

            preparedStatement.execute();
            connection.close();

            return new SimpleResult(true, "Tạo tạm trú thành công");
        }
        catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();

            return new SimpleResult(false, ex.getMessage());
        }
    }

    public SimpleResult taoTamVang(NhanKhauBean nhanKhauBean, TamVangModel tamVangModel){
        String maGiayTamVang = tamVangModel.getMaGiayTamVang();
        java.sql.Date tuNgay =  new java.sql.Date(tamVangModel.getTuNgay().getTime());
        java.sql.Date denNgay =  new java.sql.Date(tamVangModel.getDenNgay().getTime());
        String lydo = tamVangModel.getLyDo();
        String noiTamTru = tamVangModel.getNoiTamTru();
        int idNhanKhau = nhanKhauBean.getChungMinhThuModel().getIdNhanKhau();

        try(Connection connection = MySQLConnector.getConnection()) {
            // ket noi voi data_base
            String query = "INSERT INTO tam_vang (`idNhanKhau`, `maGiayTamVang`, `noiTamTru`, `tuNgay`, `denNgay`,`lyDo`) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, idNhanKhau);
            preparedStatement.setString(2, maGiayTamVang);
            preparedStatement.setString(3, noiTamTru);
            preparedStatement.setDate(4, tuNgay);
            preparedStatement.setDate(5, denNgay);
            preparedStatement.setString(6, lydo);

            preparedStatement.execute();

            //update trong nhan khau
            String query1 = "update nhan_khau set  `status` = ? " +
                    "where ID = ?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);

            // cai dat gia tri
//            preparedStatement.setInt(1, ID);
            preparedStatement1.setInt(1, NhanKhauConstant.TAM_VANG_STATUS);
            preparedStatement1.setInt(2, idNhanKhau);
            preparedStatement1.execute();

            connection.close();

            return new SimpleResult(true, "Tạo tạm vắng thành công ");
        }
        catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();

            return new SimpleResult(false, ex.getMessage());
        }
    }

    public SimpleResult taoNhanKhau(NhanKhauBean nhanKhauBean) {
        NhanKhauModel nhanKhauMoi = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();
        // lay thong tin input
//        int ID = nhanKhauMoi.getID();
        String maNhanKhau = nhanKhauMoi.getMaNhanKhau();
        String Ho_ten = nhanKhauMoi.getHo_ten();
        java.sql.Date namSinh =  new java.sql.Date(nhanKhauMoi.getNamSinh().getTime());
        int gioiTinh = nhanKhauMoi.getGioiTinh();
        String quocTich= nhanKhauMoi.getQuocTich();
        String noiSinh = nhanKhauMoi.getNoiSinh();
        String nguyenQuan = nhanKhauMoi.getNguyenQuan();
        String danToc = nhanKhauMoi.getDanToc();
        String tonGiao = nhanKhauMoi.getTonGiao();
        String tienAn= nhanKhauMoi.getTienAn();
        String trinhDoHocVan= nhanKhauMoi.getTrinhDoHocVan();
        String diaChiHienNay= nhanKhauMoi.getDiaChiHienNay();
        String ngheNghiep= nhanKhauMoi.getNgheNghiep();
        String noiLamViec= nhanKhauMoi.getNoiLamViec();
        String noiThuongTru= nhanKhauMoi.getNoiThuongTru();
        int idNguoiTao= nhanKhauMoi.getIdNguoiTao();
        int status = nhanKhauMoi.getStatus();
//        Date ngayXoa= nhanKhauMoi.getNgayXoa();
//        int idNguoiXoa= nhanKhauMoi.getIdNguoiXoa();
//        String lyDoXoa= nhanKhauMoi.getLyDoXoa();
//        String ghiChu= nhanKhauMoi.getGhiChu();

        try(Connection connection = MySQLConnector.getConnection()){
            // ket noi voi data_base
            String query = "INSERT INTO nhan_khau (`maNhanKhau`, `hoTen`, `namSinh`, `gioiTinh`, `quocTich`,`noiSinh`, `nguyenQuan`," +
                    " `danToc`, `tonGiao`,`tienAn`, `trinhDoHocVan`,`diaChiHienNay`,`ngheNghiep`,`noiLamViec`, `noiThuongTru`, " +
                    "  `idNguoiTao`, `ngayTao`, `status`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            // cai dat gia tri
//            preparedStatement.setInt(1, ID);
            preparedStatement.setString(1, maNhanKhau);
            preparedStatement.setString(2, Ho_ten);
            preparedStatement.setDate(3, namSinh);

            preparedStatement.setInt(4, gioiTinh);
            preparedStatement.setString(5, quocTich );

            preparedStatement.setString(6, noiSinh );
            preparedStatement.setString(7, nguyenQuan);
            preparedStatement.setString(8, danToc );
            preparedStatement.setString(9, tonGiao);
            preparedStatement.setString(10, tienAn );
            preparedStatement.setString(11, trinhDoHocVan);
            preparedStatement.setString(12, diaChiHienNay );
            preparedStatement.setString(13, ngheNghiep );
            preparedStatement.setString(14, noiLamViec);
            preparedStatement.setString(15, noiThuongTru);
            preparedStatement.setInt(16, idNguoiTao);


            Calendar calendar = Calendar.getInstance();
            java.sql.Date createDate = new java.sql.Date(calendar.getTime().getTime());

            preparedStatement.setDate(17, createDate);
            preparedStatement.setInt(18, status);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            // dong co so du lieu
//            preparedStatement.close();

            if(rs.next()) {
                int genID = rs.getInt(1);
                cmt.setIdNhanKhau(genID);
                String query2 = "insert into chung_minh_thu(`idNhanKhau`,`soCMT`) values (?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
                preparedStatement1.setInt(1, genID );
                preparedStatement1.setString(2, cmt.getSoCMT());
                preparedStatement1.execute();
                connection.close();

                return new SimpleResult(true, "Tao thong tin thanh cong");

            }else{
                return new SimpleResult(true, "Tao thong tin thanh cong");
            }
        } catch (SQLException ex) {// thong bao loi
            ex.printStackTrace();

            return new SimpleResult(false, ex.getMessage());
        }
    }

    public SimpleResult suaNhanKhau(NhanKhauBean nhanKhauBean) {
        NhanKhauModel nhanKhauMoi = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel cmt = nhanKhauBean.getChungMinhThuModel();

        // lay thong tin input
        int IDNhanKhau = cmt.getIdNhanKhau();
        String maNhanKhau = nhanKhauMoi.getMaNhanKhau();
        String Ho_ten = nhanKhauMoi.getHo_ten();

        java.sql.Date namSinh =  new java.sql.Date(nhanKhauMoi.getNamSinh().getTime());

        int gioiTinh = nhanKhauMoi.getGioiTinh();
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

            String query = "update nhan_khau set  `hoTen`= ? , `namSinh` = ?, `gioiTinh` = ?, `quocTich`=?,`noiSinh` =?, `nguyenQuan`=?," +
                    " `danToc` =?, `tonGiao` = ?,`tienAn` =?, `trinhDoHocVan`=?,`diaChiHienNay` = ?,`ngheNghiep` =? ,`noiLamViec`=?, `noiThuongTru`=?,`status`=? " +
                    "where ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
//            preparedStatement.setInt(1, ID);
            preparedStatement.setString(1, Ho_ten);
            preparedStatement.setDate(2, namSinh);

            preparedStatement.setInt(3, gioiTinh);
            preparedStatement.setString(4, quocTich );

            preparedStatement.setString(5, noiSinh );
            preparedStatement.setString(6, nguyenQuan);
            preparedStatement.setString(7, danToc );
            preparedStatement.setString(8, tonGiao);
            preparedStatement.setString(9, tienAn );
            preparedStatement.setString(10, trinhDoHocVan);
            preparedStatement.setString(11, diaChiHienNay );
            preparedStatement.setString(12, ngheNghiep );
            preparedStatement.setString(13, noiLamViec);
            preparedStatement.setString(14, noiThuongTru);
            preparedStatement.setInt(15, NhanKhauConstant.THUONG_TRU_STATUS);
            preparedStatement.setInt(16, IDNhanKhau);

            preparedStatement.executeUpdate();

            connection.close();

            return new SimpleResult(true, "Sửa thông tin nhân khẩu thành công");
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
//                nhanKhau.setThongTinHoKhau(hoKhau);

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
//                nhanKhau.setThongTinHoKhau(hoKhau);

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
