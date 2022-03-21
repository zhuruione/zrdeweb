package com.zr.zrdeweb.system.utils.impl;


import org.springframework.stereotype.Service;

@Service
public class GetRedomeEcodeimpl implements com.zr.zrdeweb.system.utils.GetRedomeEcode {
    public  String ecodes() {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<6;i++) {
            builder.append(returnchar());
        }
        return builder.toString();
    }
    public  char aChar(){
        return (char) ('a' + Math.random() * ('z' - 'a' + 1));
    }
    public  char num(){
        return (char) ('0' + Math.random() * ('9' - '0' + 1));
    }
    public  char returnchar(){
        char num=(char) ('0' + Math.random() * ('1' - '0' + 1));
        if(num=='0'){
            return aChar();
        }else {
            return num();
        }
    }
}
