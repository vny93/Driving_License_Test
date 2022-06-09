package com.example.doan_thibanglaixe.data.model;

import java.io.Serializable;

public class Meo implements Serializable {
    private Integer maMeo;
    private String noidung;

    public Meo() {
    }

    public Meo(Integer mameo, String noidung) {
        this.maMeo = mameo;
        this.noidung = noidung;
    }

    public Integer getMameo() {
        return maMeo;
    }

    public void setMameo(Integer mameo) {
        this.maMeo = mameo;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
