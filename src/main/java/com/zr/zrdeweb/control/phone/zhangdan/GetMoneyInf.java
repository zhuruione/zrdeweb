package com.zr.zrdeweb.control.phone.zhangdan;


import com.zr.zrdeweb.service.phone.zhangdan.impl.GetMoneynfImpl;
import com.zr.zrdeweb.system.model.ZhangDan;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/phone/zhangdan")
public class GetMoneyInf {
    @Autowired
    GetMoneynfImpl moneynf;


    @GetMapping("/daylastmoney")
    @ResponseBody
    public Map getdaylastmoney() {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        Integer TODAY = calendar.get(Calendar.DATE);
        Integer MONTH = calendar.get(Calendar.MONTH) + 1;
        Integer YEAR = calendar.get(Calendar.YEAR);
        Map map = moneynf.getmoneyinf(YEAR, MONTH, TODAY, user);
        return map;
    }

    @GetMapping("/getonedaymoney")
    @ResponseBody
    public Map getonedaymoney(Integer year, Integer month, Integer day) {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();
        Map map = moneynf.getmoneyinf(year, month, day, user);
        return map;
    }

    @GetMapping("/daymoneythings")
    @ResponseBody
    public List<ZhangDan> daymoneythings(Integer year, Integer month, Integer day) {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();
        List list = moneynf.daymoneything(year, month, day, user);
        return list;
    }

    //账单主页
    @GetMapping("/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();
        Calendar calendar = Calendar.getInstance();
        Integer TODAY = calendar.get(Calendar.DATE);
        Integer MONTH = calendar.get(Calendar.MONTH) + 1;
        Integer YEAR = calendar.get(Calendar.YEAR);
        Map moneymap = moneynf.getmoneyinf(YEAR, MONTH, TODAY, user);
        //已支出占比
        float moneyproportion = (float) moneymap.get("money") / ((float) (moneymap.get("money")) + (float) moneymap.get("daylastmoney"));
        moneymap.put("moneyproportion", Float.parseFloat(String.format("%.1f", moneyproportion * 100)));
        if ((float) moneymap.get("daylastmoney") < 0) {
            float overdraw = (float) moneymap.get("daylastmoney");
            moneymap.put("daylastmoney", 0);
            moneymap.put("overdraw", overdraw);
        }
        model.addAttribute("moneymap", moneymap);
        return "phone/zhangdan/index";
    }

    //获取历史信息
    @GetMapping("/historymap")
    public String historymap(Model model, String data) {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();
        Integer TODAY = null;
        Integer MONTH = null;
        Integer YEAR = null;
        if (data == null) {
            Calendar calendar = Calendar.getInstance();
            TODAY = calendar.get(Calendar.DATE);
            MONTH = calendar.get(Calendar.MONTH) + 1;
            YEAR = calendar.get(Calendar.YEAR);
            if(MONTH<10){
                data=YEAR+"年0"+MONTH+"月"+TODAY+"日记录";
            }else {
                data=YEAR+"年"+MONTH+"月"+TODAY+"日记录";
            }

        } else {
            TODAY=Integer.valueOf(data.substring(data.indexOf("月")+1,data.indexOf("日")));
            MONTH=Integer.valueOf(data.substring(data.indexOf("年")+1,data.indexOf("月")));
            YEAR=Integer.valueOf(data.substring(0,data.indexOf("年")));
        }
        List<ZhangDan> zhangDan = (List<ZhangDan>) moneynf.daymoneything(YEAR, MONTH, TODAY, user);
        model.addAttribute("data",data);
        model.addAttribute("zhangdanlist", zhangDan);
        return "phone/zhangdan/historymap";
    }

}
