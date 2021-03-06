package com.example.quanlyphuong.models;

import com.example.quanlyphuong.beans.NhanKhauBean;

import java.util.Date;

public class KhaiBaoModel {
    private int ID;
    private String hoTen;
    private String vungDich;
    private String bieuHien;
    private String ngayKhaiBao;

    public KhaiBaoModel() {
    }

    public KhaiBaoModel(int ID, String hoTen, String vungDich, String bieuHien, String ngayKhaiBao) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.vungDich = vungDich;
        this.bieuHien = bieuHien;
        this.ngayKhaiBao = ngayKhaiBao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getVungDich() {
        return vungDich;
    }

    public void setVungDich(String vungDich) {
        this.vungDich = vungDich;
    }

    public String getBieuHien() {
        return bieuHien;
    }

    public void setBieuHien(String bieuHien) {
        this.bieuHien = bieuHien;
    }

    public String getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(String ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }




}
