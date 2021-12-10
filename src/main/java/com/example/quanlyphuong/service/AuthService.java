package com.example.quanlyphuong.service;

import com.example.quanlyphuong.models.SimpleResult;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    Map<String, String> accounts = new HashMap<>();

    public AuthService() {
        this.accounts.put("admin1", "admin1");
        this.accounts.put("admin2", "admin2");
        this.accounts.put("admin3", "admin3");

    }

    public SimpleResult login(String userName, String password) {
        //1. check db khong co user
        if (!accounts.containsKey(userName)) {
            return new SimpleResult(false, "Tai khoan khong ton tai");
        } else {
            if (!accounts.get(userName).equals(password)) {
                return new SimpleResult(false, "Sai mat khau!");

            } else
                return new SimpleResult(true, "Login thanh cong!");
        }
        // 2. check db, thay co user nhung sai pass
        //3. c
    }
}
