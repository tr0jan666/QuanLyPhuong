package com.example.quanlyphuong.models;

public class CachLyModel {
    private int ID;
    private String hoTen;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private int mucDo;
    private String diaDiemCachLy;
    private int hetBenh;

    public CachLyModel() {
    }

    public CachLyModel(int ID, String hoTen, String thoiGianBatDau, String thoiGianKetThuc, int mucDo, String diaDiemCachLy, int hetBenh) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.mucDo = mucDo;
        this.diaDiemCachLy = diaDiemCachLy;
        this.hetBenh = hetBenh;
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

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public int getMucDo() {
        return mucDo;
    }

    public void setMucDo(int mucDo) {
        this.mucDo = mucDo;
    }

    public String getDiaDiemCachLy() {
        return diaDiemCachLy;
    }

    public void setDiaDiemCachLy(String diaDiemCachLy) {
        this.diaDiemCachLy = diaDiemCachLy;
    }

    public int getHetBenh() {
        return hetBenh;
    }

    public void setHetBenh(int hetBenh) {
        this.hetBenh = hetBenh;
    }
}
