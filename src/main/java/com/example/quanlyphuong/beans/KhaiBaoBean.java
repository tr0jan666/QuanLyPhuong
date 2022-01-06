package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.KhaiBaoModel;

public class KhaiBaoBean {
    private NhanKhauBean nhanKhauBean;
    private CachLyModel cachLyModel;
    private KhaiBaoModel khaiBaoModel;

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

    public KhaiBaoModel getKhaiBaoModel() {
        return khaiBaoModel;
    }

    public void setKhaiBaoModel(KhaiBaoModel khaiBaoModel) {
        this.khaiBaoModel = khaiBaoModel;
    }

    public KhaiBaoBean() {
    }

}
