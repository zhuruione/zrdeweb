package com.zr.zrdeweb.control.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class VideoMenu {
    @GetMapping("/ls/m")
    public static String ls(Model model){
        File file=new File("/home/data/root/ls");
        File[] files=file.listFiles();
        model.addAttribute("files",files);
        return "video/videomenu";
    }

    @GetMapping("/ls/play")
    public static String play(String name,Model model){
        String s="/rootpath/ls/"+name;
        model.addAttribute("videopath", s);
        return "video/play";
    }
}
