package com.example.hocta.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String hoten, username, password, ngaysinh;
    private String gioitinh;

    public User() {
    }

    public User(int id,String hoten, String username, String password, String ngaysinh, String gioitinh) {
        this.id = id;
        this.hoten = hoten;
        this.username = username;
        this.password = password;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }

    public User(String hoten, String username, String password, String ngaysinh, String gioitinh) {
        this.hoten = hoten;
        this.username = username;
        this.password = password;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
}
