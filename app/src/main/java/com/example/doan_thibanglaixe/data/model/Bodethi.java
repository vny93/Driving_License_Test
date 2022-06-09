package com.example.doan_thibanglaixe.data.model;

import java.io.Serializable;

public class Bodethi implements Serializable {
    Integer mabodethi;

    public Bodethi(Integer mabodethi) {
        this.mabodethi = mabodethi;
    }

    public Bodethi() {
    }

    public Integer getMabodethi() {
        return mabodethi;
    }

    public void setMabodethi(Integer mabodethi) {
        this.mabodethi = mabodethi;
    }
}
