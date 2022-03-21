package com.zr.zrdeweb.system.model.user;

import java.io.Serializable;

public class UserInf implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String perms;

    private String email;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInf( String username, String password, String perms, String email) {

        this.username = username;
        this.password = password;
        this.perms = perms;
        this.email = email;
    }
}