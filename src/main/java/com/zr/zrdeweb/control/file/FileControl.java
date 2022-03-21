package com.zr.zrdeweb.control.file;

import com.zr.zrdeweb.service.file.FileService;
import com.zr.zrdeweb.system.model.file.Dir;
import com.zr.zrdeweb.system.model.user.UserInf;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileControl {
    @Autowired
    FileService fileService;

    @Value("${zr.loadlocation}")
    String loadlocation;

    //显示文件
    @GetMapping("/file/userfiles")
    public String userfiles(Model model, String dir) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        UserInf user = (UserInf) subject.getPrincipal();
        String username = user.getUsername();
        File file = null;
        if (dir == null) {
            file = new File(loadlocation + File.separator + username);
        } else {
            file = new File(loadlocation + File.separator + username + File.separator + dir);
            file = file.getCanonicalFile();
            String canonicalpath = file.toString();
            String fathpath = new File(loadlocation + File.separator + username).toString();
            if (!canonicalpath.startsWith(fathpath))
                return "file/Error";
        }
        File[] files = file.listFiles();
        File[] newfilses = new File[files.length];
        int n = 0;
        for (File file1 : files) {
            if (file1.isDirectory()) {
                newfilses[n] = file1;
                n++;
            }
        }
        for (File file1 : files) {
            if (file1.isFile()) {
                newfilses[n] = file1;
                n++;
            }
        }
        model.addAttribute("files", newfilses);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("dir", dir);
        List list = new ArrayList();
        if (dir != null) {
            if (dir.indexOf("/") < 0) {
                Dir dirclass = new Dir();
                dirclass.setDirname(dir);
                dirclass.setDirpath(dir);
                list.add(dirclass);
            } else {
                String[] dirname;
                dirname = dir.split("/");
                for (String s : dirname) {
                    Dir dirclass = new Dir();
                    dirclass.setDirname(s);
                    dirclass.setDirpath(dir.substring(0, dir.indexOf(s) + s.length()));
                    list.add(dirclass);
                }
            }
        }
        model.addAttribute("dirlist", list);
        return "file/index";
    }

    //文件下载或打开文件夹
    @GetMapping("/file/filedown")
    public String filedown(HttpServletResponse response, String name, String path) throws Exception {
        UserInf user = (UserInf) SecurityUtils.getSubject().getPrincipal();
        if (path.indexOf(name) == 0) {
            path = "";
        } else
            //文件路径去尾巴
            path = path.substring(0, path.indexOf(name) - 1);
        if (!fileService.filedown(name, response, path)) {
            File file = new File(loadlocation + File.separator + user.getUsername() + File.separator + path + File.separator + name);
            if (!path.equals("")) {
                name = path + File.separator + name;
                name = name.replaceAll("/", "%2F");
                name = name.replaceAll("\\\\", "%2F");
            }
            return "redirect:/file/userfiles?dir=" + new String(name.getBytes(StandardCharsets.UTF_8), "iso-8859-1");
        }
        return null;
    }

    //文件上传
    @PostMapping(value = "/file/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFiles(HttpServletRequest request, HttpServletResponse response, String name, String dir) {
        UserInf user = (UserInf) SecurityUtils.getSubject().getPrincipal();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile pic = multipartRequest.getFile("file");
        InputStream io = null;
        try {
            io = pic.getInputStream();
            File file = new File(loadlocation + File.separator + user.getUsername() + File.separator + dir + File.separator + name);
            FileOutputStream op = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 8];
            int zijie;
            while ((zijie = io.read(bytes)) != -1) {
                op.write(bytes, 0, zijie);
                op.flush();
            }
            io.close();
            op.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除文件
    @PostMapping("/file/delect")
    @ResponseBody
    public String delectfile(String path) throws UnsupportedEncodingException {
        File file = new File(path);
        boolean a = file.delete();
        return a + "";
    }

    //文件重命名
    @PostMapping("/file/rename")
    @ResponseBody
    public Map<String, String> rename(String name, String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String newpath = path.substring(0, path.lastIndexOf('\\'));
            newpath=newpath+File.separator+name;
            File newdir=new File(newpath);
            boolean a = file.renameTo(newdir);
           if(a){
               Map map = new HashMap();
               map.put("name", newdir.getName());
               map.put("path", newdir.toString());
               return map;
           }else {
               return null;
           }
        } else {
            String filetype = path.substring(path.lastIndexOf('.') + 1, path.length());
            String newnamepath = path.replace(file.getName(), name + '.' + filetype);
            File newnamefile = new File(newnamepath);
            boolean a = file.renameTo(newnamefile);
            if (a) {
                Map map = new HashMap();
                map.put("name", newnamefile.getName());
                map.put("path", newnamefile.toString());
                return map;
            } else {
                return null;
            }
        }
    }
}
