package com.zr.zrdeweb.system.dao;

import java.util.List;

public interface UserDataMapper {

    int insert(UserData record);

    UserData selectByPrimaryKey(Integer id);

    List<UserData> selectAll();

    int updateByPrimaryKey(UserData record);
}