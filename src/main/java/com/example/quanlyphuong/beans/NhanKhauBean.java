package com.example.quanlyphuong.beans;

import com.example.quanlyphuong.models.*;


import java.util.ArrayList;
import java.util.List;


public class NhanKhauBean {
    private NhanKhauModel nhanKhauModel;
    private ChungMinhThuModel chungMinhThuModel;
    private CachLyModel cachLyModel;
    private TiemChungModel tiemChungModel;
    private KhaiBaoModel khaiBaoModel ;

    public KhaiBaoModel getKhaiBaoModel() {
        return khaiBaoModel;
    }

    public void setKhaiBaoModel(KhaiBaoModel khaiBaoModel) {
        this.khaiBaoModel = khaiBaoModel;
    }

    public TestCovidModel getTestCovidModel() {
        return testCovidModel;
    }

    public void setTestCovidModel(TestCovidModel testCovidModel) {
        this.testCovidModel = testCovidModel;
    }

    private TestCovidModel testCovidModel;

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
                + "<h3>Th??ng tin c?? b???n:"
                + "<p>H??? t??n: <b>" + nhanKhauModel.getHo_ten() + "</p>"
                + "<p>N??m sinh: <b>" + nhanKhauModel.getNamSinh() + "</p>"
                + "<p>Gi???i t??nh: <b>" + nhanKhauModel.getGioiTinh() + "</p>"
                + "<p>Nguy??n Qu??n: <b>" + nhanKhauModel.getNguyenQuan()+ "</p>"
                + "<p>D??n t???c: <b>" + nhanKhauModel.getDanToc()+ "</p>"
                + "<p>T??n gi??o: <b>" + nhanKhauModel.getTonGiao()+ "</p>"
                + "<p>Qu???c t???ch: <b>" + nhanKhauModel.getQuocTich()+ "</p>"
                + "<p>S??? CMT: <b>" + chungMinhThuModel.getSoCMT()+ "</p>"
                + "<h4>Ti???u s???<table>"
                + "<tr>"
                + "<th>T??? ng??y</th>"
                + "<th>?????n ng??y</th>"
                + "<th>?????a ch???</th>"
                + "<th>N??i l??m vi???c</th>"
                + "</tr>";
        res +=  "</table>"
                + "</div></html>";
        return res;
    }
}
