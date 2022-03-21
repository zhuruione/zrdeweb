package com.zr.zrdeweb.service.phone.zhangdan.impl;


import com.zr.zrdeweb.system.dao.UserzhangdaninfMapper;
import com.zr.zrdeweb.system.dao.ZhangDanMapper;
import com.zr.zrdeweb.service.phone.zhangdan.GetMoneynf;
import com.zr.zrdeweb.system.model.ZhangDan;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetMoneynfImpl implements GetMoneynf {
    @Autowired
    ZhangDanMapper zhangDanMapper;
    @Autowired
    UserzhangdaninfMapper userzhangdaninfMapper;
    public Map<String, ZhangDan> getmoneyinf(Integer YEAR, Integer MONTH, Integer TODAY, UserInf user){
        List<ZhangDan> todayzd=zhangDanMapper.selectbyday(YEAR,MONTH,TODAY,user.getId());
        List<ZhangDan> tomonthzd=zhangDanMapper.selectbymonth(YEAR,MONTH,user.getId());
        List<ZhangDan> toyearzd=zhangDanMapper.selectbyyear(YEAR, user.getId());

        //日消费
        Iterator<ZhangDan> itdaymoney=todayzd.iterator();
        float money=0;
        while(itdaymoney.hasNext()){
            money=money+itdaymoney.next().getMoney();
        }

        //月消费
        Iterator<ZhangDan> itmonthmoney=tomonthzd.iterator();
        float monthmoney=0;
        while (itmonthmoney.hasNext()){
            monthmoney=monthmoney+itmonthmoney.next().getMoney();
        }

        //年消费
        Iterator<ZhangDan> ityearmoney=toyearzd.iterator();
        float yearmoney=0;
        while (ityearmoney.hasNext()){
            yearmoney=yearmoney+ityearmoney.next().getMoney();
        }


        //每日预算
        int budget=userzhangdaninfMapper.selectByPrimaryKey(user.getId()).getBudget();

        //今日剩余
        float daylastmoney=budget-money;

        //获取今天是星期几
        Date date=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w==0){
            w=7;
        }

        //本周剩余
        float weeklast=budget*7;
        float weekmoney=0;
        int month=MONTH;
        int day=TODAY;
        for(int i=0;i<w;i++){
            itdaymoney=todayzd.iterator();
            while(itdaymoney.hasNext()){
                weekmoney=weekmoney+itdaymoney.next().getMoney();
            }
            if(day==1){
                if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
                    day=31;
                }else if(month==2){
                    day=28;
                }else {
                    day=30;
                }
                month=month-1;
            }else {
                day=day-1;
            }
            todayzd=zhangDanMapper.selectbyday(YEAR,month,day,user.getId());
        }
        weeklast=weeklast-weekmoney;


        Map map=new  LinkedHashMap();
        map.put("money",Float.parseFloat(String.format("%.1f",money)));
        map.put("daylastmoney",Float.parseFloat(String.format("%.1f",daylastmoney)));
        map.put("monthmoney",Float.parseFloat(String.format("%.1f",monthmoney)));
        map.put("yearmoney",Float.parseFloat(String.format("%.1f",yearmoney)));
        map.put("weekmoney",Float.parseFloat(String.format("%.1f",weekmoney)));
        map.put("weeklast",Float.parseFloat(String.format("%.1f",weeklast)));

        return map;
    }

    @Override
    public List<ZhangDan> daymoneything(Integer year, Integer month, Integer day,UserInf user) {
        List<ZhangDan> zhangDans=zhangDanMapper.selectbyday(year,month,day, user.getId());
        return zhangDans;
    }
}
