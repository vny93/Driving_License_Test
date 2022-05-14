package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Loaimeo implements Serializable {
    private Integer maLoaiMeo;
    private String tenLoaiMeo;

    public Loaimeo() {
    }

    public Loaimeo(Integer maloaimeo, String tenloaimeo) {
        this.maLoaiMeo = maloaimeo;
        this.tenLoaiMeo = tenloaimeo;
    }

    public Integer getMaloaimeo() {
        return maLoaiMeo;
    }

    public void setMaloaimeo(Integer maloaimeo) {
        this.maLoaiMeo = maloaimeo;
    }

    public String getTenloaimeo() {
        return tenLoaiMeo;
    }

    public void setTenloaimeo(String tenloaimeo) {
        this.tenLoaiMeo = tenloaimeo;
    }
}
