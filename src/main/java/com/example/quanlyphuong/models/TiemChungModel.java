package com.example.quanlyphuong.models;

import java.util.Date;

public class TiemChungModel {
    private int idTiemChung;
    private int idNhanKhau;
    private int soLanTiem;
    private Date ngayTiem;
    private String vacxin;
    public  TiemChungModel(){
    }
    public TiemChungModel(int idTiemChung, int idNhanKhau, int soLanTiem, Date ngayTiem, String vacxin) {
        this.idTiemChung = idTiemChung;
        this.idNhanKhau = idNhanKhau;
        this.soLanTiem = soLanTiem;
        this.ngayTiem = ngayTiem;
        this.vacxin = vacxin;
    }

    public int getIdTiemChung() {
        return idTiemChung;
    }

    public void setIdTiemChung(int idTiemChung) {
        this.idTiemChung = idTiemChung;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public int getSoLanTiem() {
        return soLanTiem;
    }

    public void setSoLanTiem(int soLanTiem) {
        this.soLanTiem = soLanTiem;
    }

    public Date getNgayTiem() {
        return ngayTiem;
    }

    public void setNgayTiem(Date ngayTiem) {
        this.ngayTiem = ngayTiem;
    }

    public String getVacxin() {
        return vacxin;
    }

    public void setVacxin(String vacxin) {
        this.vacxin = vacxin;
    }
}
