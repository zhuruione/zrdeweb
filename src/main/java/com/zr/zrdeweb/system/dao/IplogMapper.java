package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.Iplog;
import java.util.List;

public interface IplogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Iplog record);

    Iplog selectByPrimaryKey(Integer id);

    List<Iplog> selectAll();

    int updateByPrimaryKey(Iplog record);

    Iplog selectByip(String ip);
}