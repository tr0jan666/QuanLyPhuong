package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.CachLyModel;
import com.example.quanlyphuong.models.ChungMinhThuModel;
import com.example.quanlyphuong.models.NhanKhauModel;
import com.example.quanlyphuong.models.TiemChungModel;


import java.util.ArrayList;
import java.util.List;


public class NhanKhauBean {
    private NhanKhauModel nhanKhauModel;
    private ChungMinhThuModel chungMinhThuModel;
    private CachLyModel cachLyModel;
    private TiemChungModel tiemChungModel;

    public CachLyModel getCachLyModel() {
        return cachLyModel;
    }

    public void setCachLyModel(CachLyModel cachLyModel) {
        this.cachLyModel = cachLyModel;
    }

    public TiemChungModel getTiemChungModel() {
        return tiemChungModel;
    }

    public void setTiemChungModel(TiemChungModel tiemChungModel) {
        this.tiemChungModel = tiemChungModel;
    }

    public NhanKhauBean(NhanKhauModel nhanKhauModel, ChungMinhThuModel chungMinhThuModel) {
        this.nhanKhauModel = nhanKhauModel;
        this.chungMinhThuModel = chungMinhThuModel;

    }

    public NhanKhauBean() {
    }

    public NhanKhauModel getNhanKhauModel() {
        return nhanKhauModel;
    }

    public void setNhanKhauModel(NhanKhauModel nhanKhauModel) {
        this.nhanKhauModel = nhanKhauModel;
    }

    public ChungMinhThuModel getChungMinhThuModel() {
        return chungMinhThuModel;
    }

    public void setChungMinhThuModel(ChungMinhThuModel chungMinhThuModel) {
        this.chungMinhThuModel = chungMinhThuModel;
    }



    @Override
    public String toString() {
        String res =  "<html><style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản:"
                + "<p>Họ tên: <b>" + nhanKhauModel.getHo_ten() + "</p>"
                + "<p>Năm sinh: <b>" + nhanKhauModel.getNamSinh() + "</p>"
                + "<p>Giới tính: <b>" + nhanKhauModel.getGioiTinh() + "</p>"
                + "<p>Nguyên Quán: <b>" + nhanKhauModel.getNguyenQuan()+ "</p>"
                + "<p>Dân tộc: <b>" + nhanKhauModel.getDanToc()+ "</p>"
                + "<p>Tôn giáo: <b>" + nhanKhauModel.getTonGiao()+ "</p>"
                + "<p>Quốc tịch: <b>" + nhanKhauModel.getQuocTich()+ "</p>"
                + "<p>Số CMT: <b>" + chungMinhThuModel.getSoCMT()+ "</p>"
                + "<h4>Tiểu sử<table>"
                + "<tr>"
                + "<th>Từ ngày</th>"
                + "<th>Đến ngày</th>"
                + "<th>Địa chỉ</th>"
                + "<th>Nơi làm việc</th>"
                + "</tr>";
        res +=  "</table>"
                + "</div></html>";
        return res;
    }
}
