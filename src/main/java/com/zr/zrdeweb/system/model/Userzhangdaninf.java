package com.zr.zrdeweb.system.model;

import java.io.Serializable;

public class Userzhangdaninf implements Serializable {
    private Integer id;

    private Integer deposit;

    private String buyclass;

    private Integer budget;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getBuyclass() {
        return buyclass;
    }

    public void setBuyclass(String buyclass) {
        this.buyclass = buyclass;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }
}