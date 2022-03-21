package com.zr.zrdeweb.service.phone.zhangdan.impl;

import com.zr.zrdeweb.service.phone.zhangdan.PutMoneyInf;
import com.zr.zrdeweb.system.dao.ZhangDanMapper;
import com.zr.zrdeweb.system.model.ZhangDan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PutMoneyInfImpl implements PutMoneyInf {

    @Autowired
    ZhangDanMapper zhangDanMapper;
    @Override
    public boolean putdaymoney( ZhangDan zhangDan) {
        int a=zhangDanMapper.insert(zhangDan);
        if(a>0){
            return true;
        }else {
            return false;
        }
    }
}
