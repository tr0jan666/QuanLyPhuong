package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.CachLyModel;

public class CachLyBean {
    private NhanKhauBean nhanKhauBean;
    private CachLyModel cachLyModel;

    public NhanKhauBean getNhanKhauBean() {
        return nhanKhauBean;
    }

    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
    }

    public CachLyModel getCachLyModel() {
        return cachLyModel;
    }

    public void setCachLyModel(CachLyModel cachLyModel) {
        this.cachLyModel = cachLyModel;
    }

    public CachLyBean() {
    }
}
