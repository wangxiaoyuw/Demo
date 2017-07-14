package com.boke.controller;

import com.boke.pojo.Record;
import com.boke.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by wangzy on 2017/7/14.
 */
@Controller
public class RoecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/record")
    public String show(Model model){
        List<Record> records = recordService.findList();
        model.addAttribute("list",records);
        return "record/list";
    }

    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public String importFile(Model model,@RequestParam("uploadFile")MultipartFile mFile, HttpServletRequest request, HttpServletResponse response){
        String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
        List<Record> recordList = recordService.importFile(mFile,rootPath);
        model.addAttribute("list",recordList);
        return "redirect:record";
    }

    @RequestMapping("/export")
    public String exportFile(HttpServletResponse response) throws IOException {
        recordService.exportFile(response);

        return "record/list";
    }
}
