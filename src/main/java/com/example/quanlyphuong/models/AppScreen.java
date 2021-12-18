package com.example.quanlyphuong.models;

import javafx.scene.Scene;

public class AppScreen<T> {
    private String fxmlPath;
    private Scene scene;
    private T controller;

    public AppScreen(String fxmlPath, Scene scene, T controller) {
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

    public T getController() {
        return controller;
    }

    public void setController(T controller) {
        this.controller = controller;
    }
}
