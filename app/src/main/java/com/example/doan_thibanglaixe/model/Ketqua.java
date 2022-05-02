package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Ketqua implements Serializable {
    private Integer id;
    private String phuongan;
    private Integer luotthi;
    private String mauser;
    private Integer macauhoi;

    public Ketqua() {
    }

    public Ketqua(Integer id, String phuongan, Integer luotthi, String mauser, Integer macauhoi) {
        this.id = id;
        this.phuongan = phuongan;
        this.luotthi = luotthi;
        this.mauser = mauser;
        this.macauhoi = macauhoi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhuongan() {
        return phuongan;
    }

    public void setPhuongan(String phuongan) {
        this.phuongan = phuongan;
    }

    public Integer getLuotthi() {
        return luotthi;
    }

    public void setLuotthi(Integer luotthi) {
        this.luotthi = luotthi;
    }

    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }

    public Integer getMacauhoi() {
        return macauhoi;
    }

    public void setMacauhoi(Integer macauhoi) {
        this.macauhoi = macauhoi;
    }
}
