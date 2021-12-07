package com.example.quanlyphuong.service;

import com.example.quanlyphuong.models.SimpleResult;

public class UserService {
    public SimpleResult login(String userName, String password) {
        // connect db
        // query
        if(userName == "admin" && password == "admin") {
            return  new SimpleResult(true, "Login success!");
        }
        return  new SimpleResult(false, "Login failed!");
    }
}
