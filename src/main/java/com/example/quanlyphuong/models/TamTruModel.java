package com.example.quanlyphuong.models;

import java.util.Date;

public class TamTruModel {
    private int ID;
    private int idNhanKhau;
    private String maGiayTamTru;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;

    //getter, setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayTamTru() {
        return maGiayTamTru;
    }

    public void setMaGiayTamTru(String maGiayTamTru) {
        this.maGiayTamTru = maGiayTamTru;
    }


    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }


    //constructor
    public TamTruModel(int idNhanKhau, String maGiayTamTru, Date tuNgay, Date denNgay, String lyDo) {
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamTru = maGiayTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamTruModel(){

    }
}
