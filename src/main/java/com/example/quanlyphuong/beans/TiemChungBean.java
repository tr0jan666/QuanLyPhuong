package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.TiemChungModel;

public class TiemChungBean {
    private NhanKhauBean nhanKhauBean;
    private TiemChungModel tiemChungModel;

    public NhanKhauBean getNhanKhauBean() {
        return nhanKhauBean;
    }

    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
    }

    public TiemChungBean() {
    }

    public TiemChungModel getTiemChungModel() {
        return tiemChungModel;
    }

    public void setTiemChungModel(TiemChungModel tiemChungModel) {
        this.tiemChungModel = tiemChungModel;
    }
}
