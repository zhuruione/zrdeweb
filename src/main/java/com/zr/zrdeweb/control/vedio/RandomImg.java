package com.zr.zrdeweb.control.vedio;


import com.zr.zrdeweb.service.file.FileService;
import com.zr.zrdeweb.system.dao.IplogMapper;
import com.zr.zrdeweb.system.model.Iplog;
import com.zr.zrdeweb.system.utils.impl.GetiplocationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
public class RandomImg {
    @Value("${zr.rootpath}")
    String rootpath;
    @Autowired
    IplogMapper iplogMapper;
    @Autowired
    GetiplocationImpl getiplocation;
    //文件下载接口
    @Autowired
    FileService fileService;


    //随机图片
    @GetMapping("/video/img")
    public String img(Model model, HttpServletRequest request){
//        //统计信息
//        String ip = request.getHeader("X-Forwarded-For");
//
//        if(ip==null){
//            ip=request.getRemoteAddr();
//        } else
//            ip=ip.substring(0,ip.indexOf(","));
//        String log = "无";
//        if (iplogMapper.selectByip(ip) == null) {
//            try {
//                String url = "https://api.ip138.com/ip/?ip=" + ip + "&datatype=text";
//                log = getiplocation.get(url, "3a0dd91b38b91f1aa8c798ac1bd94f59");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Iplog iplog = new Iplog();
//            iplog.setTime("1");
//            iplog.setUseragint(request.getHeader("user-agent"));
//            iplog.setLocal(log);
//            iplog.setIp(ip);
//            iplogMapper.insert(iplog);
//        } else {
//            Iplog userip = iplogMapper.selectByip(ip);
//            String time = userip.getTime();
//            Integer t = Integer.valueOf(time);
//            t = t + 1;
//            userip.setTime(t + "");
//            iplogMapper.updateByPrimaryKey(userip);
//        }


        //随机视频或者图片
        Random random=new Random();
        int a=random.nextInt(10000);
        if(a<500){
            //视频
            String path = rootpath.substring("file:/".length() - 1);
            File videodir=new File(path+File.separator+"video");
            File[] videos = videodir.listFiles();
            int size = videos.length;
            random = new Random();
            String s = null;
            a = random.nextInt(size);
            if (videos[a].isFile()) {
                s = videos[a].getName();
            }
            s="/rootpath/video/" + s;
            model.addAttribute("videopath", s);
            return "video/play";
        }else {
            //图片
            String path = rootpath.substring("file:/".length() - 1);
            File imgdir = new File(path+File.separator+"img");
            File[] imgs = imgdir.listFiles();
            int size = imgs.length;
            random = new Random();
            String s = null;
            a = random.nextInt(size);
            if (imgs[a].isFile()) {
                s = imgs[a].getName();
            }
            s = "/rootpath/img/" + s;
            model.addAttribute("name", s);
            model.addAttribute("file",imgs[a]);
            return "video/img";
        }
    }
    //下载图片
    @GetMapping("/video/imgdown")
    public void imgdown(String path, String name, HttpServletResponse response){
        path = path.substring(0, path.indexOf(name) - 1);
        try {
            fileService.filedownroot(name,response,path);
        } catch (Exception e) {
            return;
        }
    }
}
