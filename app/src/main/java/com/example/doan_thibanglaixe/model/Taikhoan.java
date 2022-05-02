package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Taikhoan implements Serializable {
    private String tendangnhap;
    private String matkhau;
    private String trangthai;

    public Taikhoan() {
    }

    public Taikhoan(String tendangnhap, String matkhau, String trangthai) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.trangthai = trangthai;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
