package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Cauhoi implements Serializable {
    private Integer macauhoi;
    private String noidung,a,b,c,d,dapan,hinhanh;

    public Cauhoi() {
    }

    public Cauhoi(Integer macauhoi, String noidung, String a, String b, String c, String d, String dapan, String hinhanh) {
        this.macauhoi = macauhoi;
        this.noidung = noidung;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dapan = dapan;
        this.hinhanh = hinhanh;
    }

    public Integer getMacauhoi() {
        return macauhoi;
    }

    public void setMacauhoi(Integer macauhoi) {
        this.macauhoi = macauhoi;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDapan() {
        return dapan;
    }

    public void setDapan(String dapan) {
        this.dapan = dapan;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
