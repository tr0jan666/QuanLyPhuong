package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.KhaiBaoModel;
import com.example.quanlyphuong.beans.NhanKhauBean;

public class KhaiBaoBean {
    private NhanKhauBean nhanKhauBean;

    private KhaiBaoModel khaiBaoModel;

    public NhanKhauBean getNhanKhauBean() {
        return nhanKhauBean;
    }

    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
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
