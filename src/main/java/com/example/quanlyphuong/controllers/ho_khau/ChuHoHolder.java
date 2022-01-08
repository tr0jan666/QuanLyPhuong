package com.example.quanlyphuong.controllers.ho_khau;

import com.example.quanlyphuong.beans.NhanKhauBean;

public class ChuHoHolder {
    private NhanKhauBean nhanKhauBean;
    private final static ChuHoHolder INSTANCE = new ChuHoHolder();

    private ChuHoHolder(){}

    public static ChuHoHolder getInstance(){
        return INSTANCE;
    }

    public void setData(NhanKhauBean nhanKhauBean){
        this.nhanKhauBean = nhanKhauBean;
    }

    public NhanKhauBean getNhanKhauBean(){
        return this.nhanKhauBean;
    }
}
