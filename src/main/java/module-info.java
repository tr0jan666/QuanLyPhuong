module com.example.quanlyphuong {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.quanlyphuong to javafx.fxml;
    exports com.example.quanlyphuong;
    exports com.example.quanlyphuong.controller;
    opens com.example.quanlyphuong.controller to javafx.fxml;
}
