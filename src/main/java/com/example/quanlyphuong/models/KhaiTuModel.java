package com.example.quanlyphuong.models;
    public class KhaiTuModel {
        private int ID;
        private String soCMTnguoiKhai;
        private String soCMTnguoiMat;
        private String tenNguoiKhai;
        private String soGiayKhaiTu;
        private String ngayKhai;
        private String ngayMat;
        private String lyDoChet;

        public KhaiTuModel(int ID,String soCMTnguoiKhai,String tenNguoiKhai,String soCMTnguoiMat,
                           String soGiayKhaiTu,String ngayMat,String ngayKhai,String lyDoChet){
            this.ID = ID;
            this.soCMTnguoiKhai = soCMTnguoiKhai;
            this.soCMTnguoiMat = soCMTnguoiMat;
            this.tenNguoiKhai = tenNguoiKhai;
            this.soGiayKhaiTu = soGiayKhaiTu;
            this.ngayKhai = ngayKhai;
            this.ngayMat = ngayMat;
            this.lyDoChet = lyDoChet;
        }

        public String getSoCMTnguoiKhai() {
            return soCMTnguoiKhai;
        }

        public void setSoCMTnguoiKhai(String soCMTnguoiKhai) {
            this.soCMTnguoiKhai = soCMTnguoiKhai;
        }

        public String getSoCMTnguoiMat() {
            return soCMTnguoiMat;
        }

        public void setSoCMTnguoiMat(String soCMTnguoiMat) {
            this.soCMTnguoiMat = soCMTnguoiMat;
        }

        public String getTenNguoiKhai() {
            return tenNguoiKhai;
        }

        public void setTenNguoiKhai(String tenNguoiKhai) {
            this.tenNguoiKhai = tenNguoiKhai;
        }

        public String getSoGiayKhaiTu() {
            return soGiayKhaiTu;
        }

        public void setSoGiayKhaiTu(String soGiayKhaiTu) {
            this.soGiayKhaiTu = soGiayKhaiTu;
        }

        public String getNgayKhai() {
            return ngayKhai;
        }

        public void setNgayKhai(String ngayKhai) {
            this.ngayKhai = ngayKhai;
        }

        public String getNgayMat() {
            return ngayMat;
        }

        public void setNgayMat(String ngayMat) {
            this.ngayMat = ngayMat;
        }

        public String getLyDoChet() {
            return lyDoChet;
        }

        public void setLyDoChet(String lyDoChet) {
            this.lyDoChet = lyDoChet;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }