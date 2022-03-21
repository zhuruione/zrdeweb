package com.zr.zrdeweb.control.vedio;

import com.zr.zrdeweb.system.dao.IplogMapper;
import com.zr.zrdeweb.system.model.Iplog;
import com.zr.zrdeweb.system.utils.impl.GetiplocationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
;import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Video {


    @Autowired
    IplogMapper iplogMapper;
    @Autowired
    GetiplocationImpl getiplocation;
    @Value("${zr.videopath}")
    String videopath;

    @GetMapping("/video/play")
    public String index(String videoname, String videodir, Model model, HttpServletRequest request) {

        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());
        String path=videopath.substring("file:/".length()-1);
        if(videodir!=null&&videoname!=null){
            if(!(videodir.indexOf("/")==-1||videoname.indexOf("/")==-1)){
                return null;
            }
        }else if (videodir==null){
            if(!(videoname.indexOf("/")==-1)){
                return null;
            }
        }
        File file=null;
        String videopath=null;
        if(videodir==null){
            file=new File(path+videoname+".mp4");
            videopath="/videopath/"+ videoname + ".mp4";
        }else {
            file=new File(path+videodir+File.separator+videoname+".mp4");
            videopath="/videopath/"+videodir+"/" + videoname + ".mp4";
        }

        if(!file.isFile()){
            return null;
        }
        //获取地区
        String ip = request.getRemoteAddr();
        String log="无";
        try {
            String url="https://api.ip138.com/ip/?ip="+ip+"&datatype=text";
            log=getiplocation.get(url,"3a0dd91b38b91f1aa8c798ac1bd94f59");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iplog iplog=new Iplog();
        iplog.setIp(ip);
        iplog.setLocal(log);
        iplog.setUseragint(request.getHeader("user-agent"));
        iplog.setTime(time);
        iplogMapper.insert(iplog);
        model.addAttribute("videopath", videopath);
        return "video/play";

    }
}
