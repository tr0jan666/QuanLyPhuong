package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.MySQLConnector;
import com.example.quanlyphuong.models.SimpleResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChungMinhThuService {
    private static final ChungMinhThuService INSTANCE = new ChungMinhThuService();

    private ChungMinhThuService() {

    }

    public static ChungMinhThuService getInstance() {
        return INSTANCE;
    }

    public SimpleResult checkCMTTonTai(String soCMT){
        String checkQuery = "select * from chung_minh_thu where soCMT = ?";
        try (Connection connection = MySQLConnector.getConnection()) {
            PreparedStatement selectUserSM = connection.prepareStatement(checkQuery);
            selectUserSM.setString(1, soCMT);
            ResultSet selectCMT = selectUserSM.executeQuery();
            if (selectCMT.next()) {
            // ton tai ban ghi cmt trong he thong
                return new SimpleResult(false, "Chứng minh thư này đã tồn tại trong hệ thống");
            }else  {
                return new SimpleResult(true, "Chứng minh thư này chưa tồn tại trong hệ thống, có thể tiếp tục!");

            }

            }catch (SQLException exception) {
            exception.printStackTrace();
            return new SimpleResult(false, exception.getMessage());
        }
    }
}
