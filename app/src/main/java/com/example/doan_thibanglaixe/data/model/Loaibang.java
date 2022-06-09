package com.example.doan_thibanglaixe.data.model;

import java.io.Serializable;

public class Loaibang implements Serializable {
    private Integer maloaibang;
    private String tenloaibang;

    public Loaibang() {
    }

    public Loaibang(Integer maloaibang, String tenloaibang) {
        this.maloaibang = maloaibang;
        this.tenloaibang = tenloaibang;
    }

    public Integer getMaloaibang() {
        return maloaibang;
    }

    public void setMaloaibang(Integer maloaibang) {
        this.maloaibang = maloaibang;
    }

    public String getTenloaibang() {
        return tenloaibang;
    }

    public void setTenloaibang(String tenloaibang) {
        this.tenloaibang = tenloaibang;
    }
}
