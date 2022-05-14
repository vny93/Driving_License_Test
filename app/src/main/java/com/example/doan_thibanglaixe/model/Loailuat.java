package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Loailuat implements Serializable {
    Integer maLoaiLuatGt;
    String tenLoaiLuatGt;

    public Loailuat() {
    }

    public Loailuat(Integer maLoaiLuatGt, String tenLoaiLuatGt) {
        this.maLoaiLuatGt = maLoaiLuatGt;
        this.tenLoaiLuatGt = tenLoaiLuatGt;
    }

    public Integer getMaLoaiLuatGt() {
        return maLoaiLuatGt;
    }

    public void setMaLoaiLuatGt(Integer maLoaiLuatGt) {
        this.maLoaiLuatGt = maLoaiLuatGt;
    }

    public String getTenLoaiLuatGt() {
        return tenLoaiLuatGt;
    }

    public void setTenLoaiLuatGt(String tenLoaiLuatGt) {
        this.tenLoaiLuatGt = tenLoaiLuatGt;
    }
}
