package com.example.quanlyphuong.models;

public class KhaiBaoCachLyModels {
    private int ID;
    private String hoTen;
    private String thoiGianBatDau;
    private String thoiGianKetThuc;
    private String mucDo;
    private String diaDiemCachLy;
    private Boolean hetBenh;

    public KhaiBaoCachLyModels(){
    }

    public KhaiBaoCachLyModels(int ID, String hoTen, String thoiGianBatDau, String thoiGianKetThuc, String mucDo, String diaDiemCachLy, Boolean hetBenh) {
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

    public String getMucDo() {
        return mucDo;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getDiaDiemCachLy() {
        return diaDiemCachLy;
    }

    public void setDiaDiemCachLy(String diaDiemCachLy) {
        this.diaDiemCachLy = diaDiemCachLy;
    }

    public Boolean getHetBenh() {
        return hetBenh;
    }

    public void setHetBenh(Boolean hetBenh) {
        this.hetBenh = hetBenh;
    }
}
