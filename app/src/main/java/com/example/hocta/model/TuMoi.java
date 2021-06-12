package com.example.hocta.model;

import java.io.Serializable;

public class TuMoi implements Serializable {
    private int id;
    private String tu, loaiTu, nghia;

    public TuMoi() {
    }

    public TuMoi(int id, String tu, String loaiTu, String nghia) {
        this.id = id;
        this.tu = tu;
        this.loaiTu = loaiTu;
        this.nghia = nghia;
    }

    public TuMoi(String tu, String loaiTu, String nghia) {
        this.tu = tu;
        this.loaiTu = loaiTu;
        this.nghia = nghia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getLoaiTu() {
        return loaiTu;
    }

    public void setLoaiTu(String loaiTu) {
        this.loaiTu = loaiTu;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }
}
