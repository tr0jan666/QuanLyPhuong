
package com.example.quanlyphuong.models;
import java.util.Date;
public class NhanKhauModel {

    private int ID;
    private String maNhanKhau;
    private String Ho_ten;
    private Date namSinh;
    private int gioiTinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String quocTich;
    private String noiThuongTru;
    private String diaChiHienNay;
    private String trinhDoHocVan;
    private String ngheNghiep;
    private String noiLamViec;
    private String tienAn;
    private Date ngayChuyenDen;
    private String lyDoChuyenDen;
    private Date ngayChuyenDi;
    private String lyDoChuyenDi;
    private String diaChiMoi;
    private Date ngayTao;
    private int idNguoiTao;
    private Date ngayXoa;
    private int idNguoiXoa;
    private String lyDoXoa;
    private String ghiChu;

    //construtor
    public NhanKhauModel(int ID, String maNhanKhau, String ho_ten, Date namSinh, int gioiTinh, String noiSinh, String nguyenQuan, String danToc, String tonGiao, String quocTich, String noiThuongTru, String soHoChieu, String diaChiHienNay, String trinhDoHocVan, String ngheNghiep, String noiLamViec, String tienAn, Date ngayChuyenDen, String lyDoChuyenDen, Date ngayChuyenDi, String lyDoChuyenDi, String diaChiMoi, Date ngayTao, int idNguoiTao, Date ngayXoa, int idNguoiXoa, String lyDoXoa, String ghiChu) {
        this.ID = ID;
        this.maNhanKhau = maNhanKhau;
        Ho_ten = ho_ten;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.noiThuongTru = noiThuongTru;
        this.diaChiHienNay = diaChiHienNay;
        this.trinhDoHocVan = trinhDoHocVan;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.tienAn = tienAn;
        this.ngayChuyenDen = ngayChuyenDen;
        this.lyDoChuyenDen = lyDoChuyenDen;
        this.ngayChuyenDi = ngayChuyenDi;
        this.lyDoChuyenDi = lyDoChuyenDi;
        this.diaChiMoi = diaChiMoi;
        this.ngayTao = ngayTao;
        this.idNguoiTao = idNguoiTao;
        this.ngayXoa = ngayXoa;
        this.idNguoiXoa = idNguoiXoa;
        this.lyDoXoa = lyDoXoa;
        this.ghiChu = ghiChu;
    }
    // Get + Set
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getHo_ten() {
        return Ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        Ho_ten = ho_ten;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

    public String getDiaChiHienNay() {
        return diaChiHienNay;
    }

    public void setDiaChiHienNay(String diaChiHienNay) {
        this.diaChiHienNay = diaChiHienNay;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public String getTienAn() {
        return tienAn;
    }

    public void setTienAn(String tienAn) {
        this.tienAn = tienAn;
    }

    public Date getNgayChuyenDen() {
        return ngayChuyenDen;
    }

    public void setNgayChuyenDen(Date ngayChuyenDen) {
        this.ngayChuyenDen = ngayChuyenDen;
    }

    public String getLyDoChuyenDen() {
        return lyDoChuyenDen;
    }

    public void setLyDoChuyenDen(String lyDoChuyenDen) {
        this.lyDoChuyenDen = lyDoChuyenDen;
    }

    public Date getNgayChuyenDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyenDi(Date ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public String getLyDoChuyenDi() {
        return lyDoChuyenDi;
    }

    public void setLyDoChuyenDi(String lyDoChuyenDi) {
        this.lyDoChuyenDi = lyDoChuyenDi;
    }

    public String getDiaChiMoi() {
        return diaChiMoi;
    }

    public void setDiaChiMoi(String diaChiMoi) {
        this.diaChiMoi = diaChiMoi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIdNguoiTao() {
        return idNguoiTao;
    }

    public void setIdNguoiTao(int idNguoiTao) {
        this.idNguoiTao = idNguoiTao;
    }

    public Date getNgayXoa() {
        return ngayXoa;
    }

    public void setNgayXoa(Date ngayXoa) {
        this.ngayXoa = ngayXoa;
    }

    public int getIdNguoiXoa() {
        return idNguoiXoa;
    }

    public void setIdNguoiXoa(int idNguoiXoa) {
        this.idNguoiXoa = idNguoiXoa;
    }

    public String getLyDoXoa() {
        return lyDoXoa;
    }

    public void setLyDoXoa(String lyDoXoa) {
        this.lyDoXoa = lyDoXoa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

