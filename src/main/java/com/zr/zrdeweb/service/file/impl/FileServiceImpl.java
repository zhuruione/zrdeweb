package com.zr.zrdeweb.service.file.impl;

import com.zr.zrdeweb.service.file.FileService;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


@Service
public class FileServiceImpl implements FileService {

    @Value("${zr.loadlocation}")
    String loadlocation;

    //用户文件下载
    @Override
    public boolean filedown(String name, HttpServletResponse response,String path) throws Exception {
        UserInf userInf = (UserInf) SecurityUtils.getSubject().getPrincipal();
        File file = new File(loadlocation + File.separator + userInf.getUsername()+File.separator+path + File.separator + name);
        if(file.isDirectory()){
            return false;
        }
        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes(StandardCharsets.UTF_8), "iso-8859-1"));
        response.setContentLength((int) file.length());
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        return true;
    }
    //资源文件下载


    @Override
    public String fileup() {
        return null;
    }

    @Override
    public boolean filedownroot(String name, HttpServletResponse response, String path) throws Exception {
        File file = new File(loadlocation + File.separator +"root"+File.separator+path +File.separator + name);
        if(file.isDirectory()){
            return false;
        }
        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes(StandardCharsets.UTF_8), "iso-8859-1"));
        response.setContentLength((int) file.length());
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        return true;
    }
}
