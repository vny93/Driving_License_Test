package com.example.doan_thibanglaixe.data.model;

import java.io.Serializable;

public class Loailithuyet implements Serializable {
    private Integer maLoaiLiThuyet;
    private String tenloai;

    public Loailithuyet(Integer maLoaiLiThuyet, String tenloai) {
        this.maLoaiLiThuyet = maLoaiLiThuyet;
        this.tenloai = tenloai;
    }

    public Loailithuyet() {
    }

    public Integer getMaLoaiLiThuyet() {
        return maLoaiLiThuyet;
    }

    public void setMaLoaiLiThuyet(Integer maLoaiLiThuyet) {
        this.maLoaiLiThuyet = maLoaiLiThuyet;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
