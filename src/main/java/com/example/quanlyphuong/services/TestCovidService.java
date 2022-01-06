package com.example.quanlyphuong.services;

import com.example.quanlyphuong.beans.TestCovidBean;
import com.example.quanlyphuong.beans.NhanKhauBean;
import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.SimpleResult;
import com.example.quanlyphuong.models.TestCovidModel;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TestCovidService {
    public List<TestCovidBean> getListNhanKhauTestCovid(){
        List<TestCovidBean> list = new ArrayList<>();
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "SELECT * FROM test INNER JOIN nhan_khau ON test.idNhanKhau = nhan_khau.ID INNER JOIN chung_minh_thu ON test.idNhanKhau = chung_minh_thu.idNhanKhau";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("pn check");

            while (rs.next()) {

                TestCovidBean testCovidBean = new TestCovidBean();
                TestCovidModel testCovidModel = new TestCovidModel();
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhauModel = new NhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = new ChungMinhThuModel();

                System.out.println(rs.getString("hoTen"));
                testCovidModel.setIDTest(rs.getInt("test.ID"));

                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                nhanKhauBean.setNhanKhauModel(nhanKhauModel);
                nhanKhauBean.setChungMinhThuModel(chungMinhThuModel);

                nhanKhauModel.setHo_ten(rs.getString("hoTen"));
                testCovidModel.setKetQua(rs.getInt("ketQua"));
                testCovidModel.setThoiDiemTest(rs.getString("thoiDiemTest"));
                testCovidModel.setDiaDiemTest(rs.getString("diaDiemTest"));




                testCovidBean.setNhanKhauBean(nhanKhauBean);
                testCovidBean.setTestCovidModel(testCovidModel);

                list.add(testCovidBean);
            }


            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void deleteTestCovid( TestCovidBean bean){
        int idTestCovid= bean.getTestCovidModel().getIDTest();
        try{
            Connection connection = MySQLConnector.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM test WHERE test.ID =  "+ idTestCovid;

            statement.executeUpdate(query);
        }catch(Exception mysqlException){
//            this.exceptionHandle(mysqlException.getMessage());
            System.out.println(mysqlException);
        }
    }

    public SimpleResult addTestCovid(TestCovidBean bean) throws SQLException {
        try {
            Connection connection = MySQLConnector.getConnection();
            String query = "INSERT INTO  test (`idNhanKhau`, `thoiDiemTest`, `ketQua`,`diaDiemTest`) VALUES (?,?,?,?) ";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // cai dat gia tri
            preparedStatement.setInt(1, bean.getNhanKhauBean().getNhanKhauModel().getID());
            preparedStatement.setString(2, bean.getTestCovidModel().getThoiDiemTest());
            preparedStatement.setInt(3, bean.getTestCovidModel().getKetQua());
            preparedStatement.setString(4, bean.getTestCovidModel().getDiaDiemTest());

            preparedStatement.executeUpdate();
            //statement.executeUpdate(query);

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            System.out.println("them thanh cong");
            return new SimpleResult(true, "thêm thành công");

        } catch (Exception e) {
            return new SimpleResult(false, e.getMessage());

        }
    }
}
