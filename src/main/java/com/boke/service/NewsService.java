package com.boke.service;

import com.boke.dao.NewsMapper;
import com.boke.pojo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangzy on 2017/7/21.
 */
@Service
public class NewsService {

    @Autowired
    private NewsMapper newsDao;

    public void save(String v) {
      newsDao.save(v);
    }

    public News findOne(int a) {
       return newsDao.findOne(a);
    }

    public List<News> findAll() {
        return newsDao.findAll();
    }
}
