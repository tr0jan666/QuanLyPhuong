package com.example.quanlyphuong.models;
public class UserMoldel {
    private int ID;
    private String userName;
    private String passwd;

    private boolean administrator;

    public UserMoldel(int ID, String userName, String passwd) {
        this.ID = ID;
        this.userName = userName;
        this.passwd = passwd;
    }

    public UserMoldel() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
}


