package com.boke.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by wangzy on 2017/7/13.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/gotoAction",method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){

        String result;
        if (!file.isEmpty()){
            String contextPath = request.getContextPath();
            String servletPath = request.getServletPath();
            String scheme = request.getScheme();

            String storePath = "E:\\project\\images";
            String fineName = file.getOriginalFilename();
            File filepath = new File(servletPath,fineName);
            if (!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            try {
                file.transferTo(new File(storePath+File.separator+fineName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "success";
        }else {
            result = "error";
        }
        return result;
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam("name") String name,
                                           @RequestParam("filePath") String path) throws IOException{
        File file = new File(path);

        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = new String(name.getBytes("UTF-8"),"iso-8859-1");
        httpHeaders.setContentDispositionFormData("attachment",fileName);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders, HttpStatus.CREATED);
    }

}
