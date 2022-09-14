package com.ly.admin.mapperInterface;

import com.ly.admin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * FileName:CityMapper.class
 * Author:ly
 * Date:2022/9/13 0013
 * Description:
 */
@Mapper
public interface CityMapper {

    @Select("select * from city where id=#{id};")
    public City getCItyById(Long id);

    //@Insert("insert into city values(null,#{name},#{state},#{country});")
    //@Options(useGeneratedKeys = true, keyColumn = "id")
    public int insert(City city);
}
