package com.example.quanlyphuong.models;

import javafx.scene.Scene;

public class AppScreen {
    private String fxmlPath;
    private Scene scene;
    private Object controller;

    public AppScreen(String fxmlPath, Scene scene, Object controller) {
        this.fxmlPath = fxmlPath;
        this.scene = scene;
        this.controller = controller;
    }

    public AppScreen() {
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public <T> T getController() {
        return (T) this.controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
