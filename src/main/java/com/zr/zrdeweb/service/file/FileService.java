package com.zr.zrdeweb.service.file;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    public boolean filedown(String name, HttpServletResponse response,String path) throws Exception;
    public String fileup();
    public boolean filedownroot(String name, HttpServletResponse response,String path) throws Exception;
}
