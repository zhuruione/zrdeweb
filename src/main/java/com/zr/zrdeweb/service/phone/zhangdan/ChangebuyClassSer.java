package com.zr.zrdeweb.service.phone.zhangdan;

import com.zr.zrdeweb.system.model.Userzhangdaninf;
import org.springframework.stereotype.Service;

public interface ChangebuyClassSer {
    public Userzhangdaninf getuzdi(Integer id);
    public int addbuyclass(Integer id,String buyclass);
}
