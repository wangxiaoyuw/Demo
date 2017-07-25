package com.boke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wangzy on 2017/7/14.
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @RequestMapping("/show")
    public String show(Model model){
        model.addAttribute("aa",11);
        return "echarts";
    }


}
