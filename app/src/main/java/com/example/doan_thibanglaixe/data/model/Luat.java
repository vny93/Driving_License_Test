package com.example.doan_thibanglaixe.data.model;

import java.io.Serializable;

public class Luat implements Serializable {
    Integer maluat;
    String noidung, mucphat;

    public Luat() {
    }

    public Luat(Integer maluat, String noidung, String mucphat) {
        this.maluat = maluat;
        this.noidung = noidung;
        this.mucphat = mucphat;
    }

    public Integer getMaluat() {
        return maluat;
    }

    public void setMaluat(Integer maluat) {
        this.maluat = maluat;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getMucphat() {
        return mucphat;
    }

    public void setMucphat(String mucphat) {
        this.mucphat = mucphat;
    }
}
