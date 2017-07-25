package com.boke.controller;

import com.boke.pojo.News;
import com.boke.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzy on 2017/7/20.
 */
@Controller
@RequestMapping("/news")
public class UeditorController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/list")
    public String list(Model model){
      List<News> list =  newsService.findAll();
      model.addAttribute("list",list);
      return "news/list";
    }

    @RequestMapping("/ueditor")
    public String add(){
        return "ueditor";
    }

    /**
     * 保存
     * @param v
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String show(@RequestParam("value1")String v, Model model){
        newsService.save(v);
        return "success";
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/show/{id}")
    public String ss(@PathVariable("id")Integer id, Model model){
        News news = newsService.findOne(id);
        model.addAttribute("p",news.getContent());
        return "success";
    }
}
