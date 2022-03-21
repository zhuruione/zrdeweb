package com.zr.zrdeweb.system.model.user;

import java.io.Serializable;

public class UserData implements Serializable {
    private Integer id;

    private String user;

    private String phone;

    private Integer area;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public UserData(Integer id, String user, String phone, Integer area) {
        this.id = id;
        this.user = user;
        this.phone = phone;
        this.area = area;
    }
}