package com.example.quanlyphuong.models;


public class ThanhVienCuaHoModel {
    private int idNhanKhau;
    private int idHoKhau;
    private String quanHeVoiChuHo;

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setQuanHeVoiChuHo(String quanHeVoiChuHo) {
        this.quanHeVoiChuHo = quanHeVoiChuHo;
    }

    public String getQuanHeVoiChuHo() {
        return quanHeVoiChuHo;
    }
}
