package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.user.UserInf;
import java.util.List;

public interface UserInfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInf record);

    UserInf selectByPrimaryKey(Integer id);

    UserInf selectByUsername(String name);

    UserInf selectByEmail(String email);

    List<UserInf> selectAll();

    int updateByPrimaryKey(UserInf record);


}