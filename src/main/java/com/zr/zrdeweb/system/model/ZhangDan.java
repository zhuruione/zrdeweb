package com.zr.zrdeweb.system.model;

import java.io.Serializable;

public class ZhangDan implements Serializable {
    private Integer mainid;

    private String remark;

    private Integer userid;

    private Float money;

    private String type;

    private Integer month;

    private Integer day;

    private Integer year;

    private static final long serialVersionUID = 1L;

    public Integer getMainid() {
        return mainid;
    }

    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}