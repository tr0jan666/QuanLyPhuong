package com.example.quanlyphuong.models;

import java.time.LocalDate;
import java.util.Date;

public class TiemChungModel {
    private String hoTen;
    private String CCCD;
    private int idTiemChung;
    private int idNhanKhau;
    private int soLanTiem;
    private Date ngayTiem;
    private String vacxin;
    private String diaDiem;

    public TiemChungModel() {
    }

    public TiemChungModel(String hoTen,String CCCD,String diaDiem,int idTiemChung,int idNhanKhau,int soLanTiem,Date ngayTiem,String vacxin){
        this.hoTen = hoTen;
        this.CCCD = CCCD;
        this.diaDiem = diaDiem;
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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
