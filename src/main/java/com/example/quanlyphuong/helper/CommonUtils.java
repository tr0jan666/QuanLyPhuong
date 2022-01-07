package com.example.quanlyphuong.helper;

import com.example.quanlyphuong.helper.constants.MucDoCachLyConstant;

public class CommonUtils {
    public static String getMucDoString(int mucDo) {
        String mucDoString;
        switch (mucDo) {
            case MucDoCachLyConstant.F0:
                mucDoString = "F0";
                break;
            case MucDoCachLyConstant.F1:
                mucDoString = "F1";
                break;
            case MucDoCachLyConstant.F2:
                mucDoString = "F2";
                break;
            case MucDoCachLyConstant.F3:
                mucDoString = "F3";
                break;
            case MucDoCachLyConstant.F4:
                mucDoString = "F4";
                break;
            case MucDoCachLyConstant.F5:
                mucDoString = "F5";
                break;
            default:
                mucDoString = "Không cách ly";
                break;
        }
        return  mucDoString;
    }

    public static int getMucDoInt(String mucDo) {
        int mucDoInt;
        switch (mucDo) {
            case "F1":
                mucDoInt = MucDoCachLyConstant.F1;
                break;
            case "F2":
                mucDoInt = MucDoCachLyConstant.F2;
                break;
            case "F3":
                mucDoInt = MucDoCachLyConstant.F3;
                break;
            case "F4":
                mucDoInt = MucDoCachLyConstant.F4;
                break;
            case "F05":
                mucDoInt = MucDoCachLyConstant.F5;
                break;
            default:
                mucDoInt = MucDoCachLyConstant.F0;
                break;
        }
        return  mucDoInt;
    }
}
