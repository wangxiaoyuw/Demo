package com.boke.dao;

import com.boke.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzy on 2017/7/21.
 */
public interface NewsMapper {

    void save(@Param("v") String v);

   News findOne(@Param("a")int a);

    List<News> findAll();
}
