package com.ly.admin.service;

import com.ly.admin.bean.City;
import com.ly.admin.mapperInterface.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FileName:CityService.class
 * Author:ly
 * Date:2022/9/13 0013
 * Description:
 */
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    public City getCityById(Long id) {
        return cityMapper.getCItyById(id);
    }

    public void insertOne(City city) {
        int insert = cityMapper.insert(city);
        System.out.println("insert " + insert);
    }
}
