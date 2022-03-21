package com.zr.zrdeweb.system.dao;

import com.zr.zrdeweb.system.model.City;

import java.util.List;

public interface CityMapper {

     List<City> getprovince(Integer pid);

     List<City> getcitys(Integer id);

     List<City> getareas(Integer id);

     List<City> getareas(String id);

}