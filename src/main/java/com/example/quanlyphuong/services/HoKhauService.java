package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.HoKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.helper.enums.HoKhauFilterEnum;
import com.example.quanlyphuong.models.HoKhauModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.ThanhVienCuaHoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoKhauService {

    private static final HoKhauService INSTANCE = new HoKhauService();

    private HoKhauService () {

    }
    public static HoKhauService getInstance() {
        return INSTANCE;
    }

    public ArrayList<HoKhauBean> getListHoKhau() {
        ArrayList<HoKhauBean> list = new ArrayList<>();

        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID ";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                HoKhauBean temp = new HoKhauBean();

                HoKhauModel hoKhauModel = new HoKhauModel();

                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idCHuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = new NhanKhauModel();
                chuHo.setID(rs.getInt("ID"));
                chuHo.setHo_ten(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getInt("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                temp.setChuHo(chuHo);
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    ArrayList<NhanKhauModel> listNhanKhau = new ArrayList<>();
                    ArrayList<ThanhVienCuaHoModel> listThanhVienCuaHo = new ArrayList<>();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setHo_ten(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getInt("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));

                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }

                    temp.setListNhanKhauModels(listNhanKhau);
                    temp.setListThanhVienCuaHo(listThanhVienCuaHo);
                } catch (Exception e) {
                    System.out.println("services.HoKhauService.getListHoKhau()");
                    System.out.println(e.getMessage());
                }
                temp.setHoKhauModel(hoKhauModel);
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }





    public ArrayList<HoKhauModel> timKiemHoKhau(String keyword, HoKhauFilterEnum filterType) {

        return null;
    }

    public SimpleResult taoHoKhau(HoKhauModel hoKhau) {
        return null;
    }

    public SimpleResult xoaHoKhau(int idHoKhau) {
        String xoaHoKhauQuery = "delete from ho_khau where Id = ?";
        String xoaThanhVienHoQuery = "delete from thanh_vien_cua_ho where idHoKhau = ?";

        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement xoaHoKhauStatement = connection.prepareStatement(xoaHoKhauQuery);
            PreparedStatement xoaThanhVienHoStatement = connection.prepareStatement(xoaThanhVienHoQuery);

            xoaHoKhauStatement.setInt(1, idHoKhau);
            xoaThanhVienHoStatement.setInt(1, idHoKhau);

            int countDeleteThanhVienHo = xoaThanhVienHoStatement.executeUpdate();
            if(countDeleteThanhVienHo > 0) {
                int countDeleteHoKhau = xoaHoKhauStatement.executeUpdate();
                if(countDeleteHoKhau > 0) {
                    return new SimpleResult(true, SimpleResult.DEFAULT_SUCCESS_MESSAGE);
                }else {
                    return  new SimpleResult(false, SimpleResult.DEFAULT_FAILED_MESSAGE);
                }
            }else {
                return  new SimpleResult(false, SimpleResult.DEFAULT_FAILED_MESSAGE);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
            return new SimpleResult(false, exception.getMessage());
        }
    }

    public SimpleResult suaHoKhau(HoKhauModel hoKhau) {
        return null;
    }

    public ArrayList<NhanKhauModel> listNhanKhauCuaHoTruChuHo(HoKhauModel hoKhau){
        return hoKhau.getListNhanKhau();
    }

    public boolean addNew(HoKhauBean hoKhauBean) throws ClassNotFoundException, SQLException{
        Connection connection = MySQLConnector.getConnection();
        String query = "INSERT INTO ho_khau(maHoKhau, idChuHo, maKhuVuc, diaChi, ngayLap,nguoiThucHien)"
                + " values (?, ?, ?, ?, NOW(), 1)";
        System.out.println(hoKhauBean.getHoKhauModel().getMaHoKhau());

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hoKhauBean.getHoKhauModel().getMaHoKhau());
        preparedStatement.setInt(2, hoKhauBean.getChuHo().getID());
        preparedStatement.setString(3, hoKhauBean.getHoKhauModel().getMaKhuVuc());
        preparedStatement.setString(4, hoKhauBean.getHoKhauModel().getDiaChi());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int genID = rs.getInt(1);
            String sql = "INSERT INTO thanh_vien_cua_ho(idNhanKhau, idHoKhau, quanHeVoiChuHo)"
                    + " values (?, ?, ?)";
            hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel thanhVien) -> {
                try {
                    PreparedStatement preStatement = connection.prepareStatement(sql);
                    preStatement.setInt(1, thanhVien.getIdNhanKhau());
                    preStatement.setInt(2, genID);
                    preStatement.setString(3, thanhVien.getQuanHeVoiChuHo());
                    preStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(HoKhauService.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
        preparedStatement.close();
        connection.close();
        return true;
    }

    public void tachHoKhau(HoKhauBean hoKhauBean) {
        /**
         * xoa cac thanh vien co trong moi ra khoi bang thanh_vien_cua_ho
         */

        // xoa chu ho
//        String query = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + hoKhauBean.getChuHo().getID();
//        try {
//            Connection connection = MysqlConnection.getMysqlConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            int rs = preparedStatement.executeUpdate();
//            System.out.println("xoa thanh cong");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        // xoa cac thanh vien

        hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel item) -> {
            String sql = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + item.getIdNhanKhau();
            try {
                Connection connection = MySQLConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                int rs = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        /**
         * tao ho khau moi voi hoKhauBean
         */
        try {
            this.addNew(hoKhauBean);
//            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (Exception e) {
            System.out.println("services.HoKhauService.tachHoKhau()");
        }
    }

}
