package com.example.quanlyphuong.models;

import java.util.Date;

public class TestCovidModel {
    private int IDTest;
    private int idNhanKhau;
    private Date thoiDiemTest;
    private String hinhThucTest;
    private boolean ketQua;

    public TestCovidModel(int IDTest, int idNhanKhau, Date thoiDiemTest, String hinhThucTest, boolean ketQua) {
        this.IDTest = IDTest;
        this.idNhanKhau = idNhanKhau;
        this.thoiDiemTest = thoiDiemTest;
        this.hinhThucTest = hinhThucTest;
        this.ketQua = ketQua;
    }

    public int getIDTest() {
        return IDTest;
    }

    public void setIDTest(int IDTest) {
        this.IDTest = IDTest;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public Date getThoiDiemTest() {
        return thoiDiemTest;
    }

    public void setThoiDiemTest(Date thoiDiemTest) {
        this.thoiDiemTest = thoiDiemTest;
    }

    public String getHinhThucTest() {
        return hinhThucTest;
    }

    public void setHinhThucTest(String hinhThucTest) {
        this.hinhThucTest = hinhThucTest;
    }

    public boolean getKetQua() {
        return ketQua;
    }

    public void setKetQua(boolean ketQua) {
        this.ketQua = ketQua;
    }
}
