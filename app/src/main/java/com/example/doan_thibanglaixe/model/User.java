package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String hoten;
    private String sdt;

    public User() {
    }

    public User(String email, String hoten, String sdt) {
        this.email = email;
        this.hoten = hoten;
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
