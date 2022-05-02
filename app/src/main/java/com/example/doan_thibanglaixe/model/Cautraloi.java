package com.example.doan_thibanglaixe.model;

import java.io.Serializable;

public class Cautraloi implements Serializable {
    private int stt;
    private String traloi;

    public Cautraloi() {
    }

    public Cautraloi(int stt, String traloi) {
        this.stt = stt;
        this.traloi = traloi;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }
}
