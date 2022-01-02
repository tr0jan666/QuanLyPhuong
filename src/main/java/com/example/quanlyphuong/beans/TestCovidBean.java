package com.example.quanlyphuong.beans;
import com.example.quanlyphuong.models.TestCovidModel;

public class TestCovidBean {
    private NhanKhauBean nhanKhauBean;
    private TestCovidModel testCovidModel;

    public TestCovidBean() {
    }

    public NhanKhauBean getNhanKhauBean() {
        return nhanKhauBean;
    }

    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
    }

    public TestCovidModel getTestCovidModel() {
        return testCovidModel;
    }

    public void setTestCovidModel(TestCovidModel testCovidModel) {
        this.testCovidModel = testCovidModel;
    }

}


