package com.zr.zrdeweb.control.user.userinf;


import com.zr.zrdeweb.system.dao.CityMapper;
import com.zr.zrdeweb.system.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public class AreaControl {
    @Autowired
    CityMapper cityMapper;
    @GetMapping("/getprovince")
    public List<City> getprovince(){
        List<City> list=cityMapper.getprovince(0);
        return list;
    }
    @GetMapping("/getcitys")
    public List<City> getcitys(Integer id){
        List<City> cities=cityMapper.getcitys(id);
        return cities;
    }
    @GetMapping("getareas")
    public List<City> getareas(Integer id){
        String cityid= id.toString().substring(0,4);
        cityid=cityid+"__";
        List<City> cities =cityMapper.getareas(cityid);
        Iterator<City> it=cities.iterator();
        while (it.hasNext()){
            if((((int)it.next().getId())==id)){
                it.remove();
                break;
            }
        }
        return cities;
    }
}
