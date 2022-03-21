package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.ZhangDan;

import java.util.List;

public interface ZhangDanMapper {
    int insert(ZhangDan record);

    List<ZhangDan> selectAll();

    List<ZhangDan> selectbyday(Integer year,Integer month,Integer day,Integer id);

    List<ZhangDan> selectbymonth(Integer year,Integer month,Integer id);

    List<ZhangDan> selectbyyear(Integer year,Integer id);

    Integer delectbyid(Integer mainid);
}