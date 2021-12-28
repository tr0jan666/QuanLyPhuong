module com.example.quanlyphuong {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires  java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.quanlyphuong to javafx.fxml;
    exports com.example.quanlyphuong;
    exports com.example.quanlyphuong.controllers;
    exports com.example.quanlyphuong.controllers.dich_te;
    exports com.example.quanlyphuong.controllers.nhan_khau;
    opens com.example.quanlyphuong.controllers to javafx.fxml;
    opens com.example.quanlyphuong.controllers.nhan_khau to javafx.fxml;
    opens com.example.quanlyphuong.controllers.dich_te to javafx.fxml;


    opens com.example.quanlyphuong.controllers.ho_khau to javafx.fxml;
}
