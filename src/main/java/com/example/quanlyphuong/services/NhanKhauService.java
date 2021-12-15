package com.example.quanlyphuong.services;

import com.example.quanlyphuong.helper.enums.NhanKhauFilterEnum;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.SimpleResult;

import java.util.ArrayList;
import java.util.Map;

public class NhanKhauService {
    public NhanKhauModel getDetail(int idNhanKhau) {
        //write code here
        return null;
    }

    public SimpleResult xoaNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult khaiTuNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult taoNhanKhau(int idNhanKhau) {
        return null;
    }

    public SimpleResult suaNhanKhau(int idNhanKhau) {
        return null;
    }

    public ArrayList<NhanKhauModel> timKiemNhanKhau(String keyword, NhanKhauFilterEnum filterType) {
        return null;
    }

    public ArrayList<NhanKhauModel> filterNhanKhau(Map<NhanKhauFilterEnum, String> filterOptions) {
        return null;
    }

    public String countTiLeTiem1Vacxin() {
        return null;
    }

    public String countTiLeTiem2Vacxin() {
        return null;
    }

    public String countTiLeTuVong() {
        return null;
    }

    public int soNguoiCachLy() {
        return 0;
    }
}
