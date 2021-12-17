package com.example.quanlyphuong.models;

public class SimpleResult {
    public static String DEFAULT_SUCCESS_MESSAGE = "Thành công!";
    public static String DEFAULT_FAILED_MESSAGE = "Thất bại! Vui lòng thử lại sau.";

    private  boolean success;
    private String message;

    public SimpleResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public SimpleResult() {};

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
