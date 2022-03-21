package com.zr.zrdeweb.service.phone.zhangdan.impl;

import com.zr.zrdeweb.service.phone.zhangdan.ChangebuyClassSer;
import com.zr.zrdeweb.system.dao.UserzhangdaninfMapper;
import com.zr.zrdeweb.system.model.Userzhangdaninf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChangebuyClassSerImpl implements ChangebuyClassSer {
    @Autowired
    UserzhangdaninfMapper userzhangdaninfMapper;
    @Override
    public Userzhangdaninf getuzdi(Integer id) {
        Userzhangdaninf inf =userzhangdaninfMapper.selectByPrimaryKey(id);
        return inf;
    }

    @Override
    public int addbuyclass(Integer id,String buyclass) {
        Userzhangdaninf inf =userzhangdaninfMapper.selectByPrimaryKey(id);
        String s=inf.getBuyclass();

        String[] buyclasses=s.split("%");
        for (int i=0;i<buyclasses.length;i++){
            if(buyclasses[i].equals(buyclass))
                return 0;
        }
        s=s+"%"+buyclass;
        inf.setBuyclass(s);
        int a=userzhangdaninfMapper.updateByPrimaryKey(inf);
        return a;
    }
}
