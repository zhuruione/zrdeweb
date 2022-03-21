package com.zr.zrdeweb.control.phone.zhangdan;

import com.zr.zrdeweb.service.phone.zhangdan.PutMoneyInf;
import com.zr.zrdeweb.system.dao.ZhangDanMapper;
import com.zr.zrdeweb.system.model.ZhangDan;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/phone/zhangdan/")
public class SetMoneyInf {

    @Autowired
    PutMoneyInf putMoneyInf;
    @PostMapping("/putnewdate")
    public String putnewdate(String type,String moneydata,String money,String remark){
        Subject subject=SecurityUtils.getSubject();
        ZhangDan newzhangdan=new ZhangDan();
        UserInf data=(UserInf)subject.getPrincipal();
        float m=0;
        if(money.indexOf(',')>0){
            String[] moneys=money.split(",");
            for(String moneyString:moneys){
                m=m+Float.parseFloat(moneyString);
            }
        }else if(money.indexOf('，')>0){
            String[] moneys=money.split("，");
            for(String moneyString:moneys){
                m=m+Float.parseFloat(moneyString);
            }
        }else if(money.indexOf('/')>0){
            String[] moneys=money.split("/");
            for(String moneyString:moneys){
                m=m+Float.parseFloat(moneyString);
            }
        } else {
            m=Float.parseFloat(money);
        }
        newzhangdan.setMoney(m);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        try {
            date=simpleDateFormat.parse(moneydata);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newzhangdan.setDay(date.getDate());
        newzhangdan.setMonth(date.getMonth()+1);
        newzhangdan.setYear(date.getYear()+1900);
        newzhangdan.setType(type);
        newzhangdan.setRemark(remark);
        newzhangdan.setUserid(data.getId());
        boolean ok=putMoneyInf.putdaymoney(newzhangdan);
        return "redirect:/phone/zhangdan/index";
    }

    @Autowired
    ZhangDanMapper mapper;
    @GetMapping("/delect")
    public String delectzd(int mainid){
        mapper.delectbyid(mainid);
        return "redirect:/phone/zhangdan/historymap";
    }
}
