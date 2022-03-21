package com.zr.zrdeweb.service.user;

import com.zr.zrdeweb.system.model.user.UserInf;

public interface UserinfService {
    public UserInf getUserInfdByUsername(String name);
    public UserInf getUserInfByEmail(String email);
}
