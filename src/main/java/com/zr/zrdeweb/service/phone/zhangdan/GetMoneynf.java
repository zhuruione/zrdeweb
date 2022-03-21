package com.zr.zrdeweb.service.phone.zhangdan;

import com.zr.zrdeweb.system.model.ZhangDan;
import com.zr.zrdeweb.system.model.user.UserInf;

import java.util.List;
import java.util.Map;

public interface GetMoneynf {
    public Map<String, ZhangDan> getmoneyinf(Integer year, Integer month, Integer day, UserInf user);
    public List<ZhangDan> daymoneything(Integer year,Integer month,Integer day,UserInf user);
}
