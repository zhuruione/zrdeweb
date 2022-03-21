package com.zr.zrdeweb.control.phone.zhangdan;

import com.zr.zrdeweb.service.phone.zhangdan.ChangebuyClassSer;
import com.zr.zrdeweb.system.model.Userzhangdaninf;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phone/zhangdan")
public class Changebuyclass {

    @Autowired
    ChangebuyClassSer changebuyClassSer;


    @GetMapping("/getbuyclasses")
    @ResponseBody
    public List<String> getbuyclasses(HttpServletRequest request){
        UserInf userInf=(UserInf) SecurityUtils.getSubject().getPrincipal();
        Userzhangdaninf inf =changebuyClassSer.getuzdi(userInf.getId());
        String buyclass=inf.getBuyclass();
        String[] buyclasses=buyclass.split("%");
        List<String> bc=new ArrayList<>();
        for (int i=0;i<buyclasses.length;i++){
            bc.add(buyclasses[i]);
        }
        return bc;
    }
    @GetMapping("/addbuyclass")
    @ResponseBody
    public String addbuyclass(String bc,HttpServletRequest request){
        UserInf userInf=(UserInf) request.getSession().getAttribute("user");
        Userzhangdaninf inf =changebuyClassSer.getuzdi(userInf.getId());
        return ""+changebuyClassSer.addbuyclass(inf.getId(), bc);
    }

}
