package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.user.UserData;
import java.util.List;

public interface UserDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserData record);

    UserData selectByPrimaryKey(Integer id);

    List<UserData> selectAll();

    int updateByPrimaryKey(UserData record);
}