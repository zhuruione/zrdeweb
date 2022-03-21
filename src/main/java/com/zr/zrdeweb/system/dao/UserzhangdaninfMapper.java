package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.Userzhangdaninf;
import java.util.List;

public interface UserzhangdaninfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userzhangdaninf record);

    Userzhangdaninf selectByPrimaryKey(Integer id);

    List<Userzhangdaninf> selectAll();

    int updateByPrimaryKey(Userzhangdaninf record);
}