package com.zr.zrdeweb.system.model;

import java.io.Serializable;

public class Iplog implements Serializable {
    private Integer id;

    private String ip;

    private String local;

    private String useragint;

    private String time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUseragint() {
        return useragint;
    }

    public void setUseragint(String useragint) {
        this.useragint = useragint;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}