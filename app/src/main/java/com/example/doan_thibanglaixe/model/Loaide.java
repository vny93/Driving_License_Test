package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Loaide implements Serializable {
    private Integer maloaide,socau,thoigian;
    private String tenloaide;

    public Loaide() {
    }

    public Loaide(Integer maloaide, Integer socau, Integer thoigian, String tenloaide) {
        this.maloaide = maloaide;
        this.socau = socau;
        this.thoigian = thoigian;
        this.tenloaide = tenloaide;
    }

    public Integer getMaloaide() {
        return maloaide;
    }

    public void setMaloaide(Integer maloaide) {
        this.maloaide = maloaide;
    }

    public Integer getSocau() {
        return socau;
    }

    public void setSocau(Integer socau) {
        this.socau = socau;
    }

    public Integer getThoigian() {
        return thoigian;
    }

    public void setThoigian(Integer thoigian) {
        this.thoigian = thoigian;
    }

    public String getTenloaide() {
        return tenloaide;
    }

    public void setTenloaide(String tenloaide) {
        this.tenloaide = tenloaide;
    }
}
