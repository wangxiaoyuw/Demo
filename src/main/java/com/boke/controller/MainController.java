package com.boke.controller;

import com.alibaba.fastjson.JSON;

import com.boke.common.BaseResult;
import com.boke.common.CommonCode;
import com.boke.utils.FileSizeUtil;
import com.boke.utils.FileUploadUtil;
import com.boke.utils.ImgCut;
import com.boke.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by wangxy on 2017/4/6.
 */
@Controller
public class MainController {

    private String MAX_FILE_SIZE= "3";//最大文件限制 单位:M

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/look", method = RequestMethod.GET)
    public String index(ModelMap map, HttpServletResponse response, HttpSession session, HttpServletRequest request) {
        /**
         * 当前头像的地址放在config.properties里，每次上传也会更新
         */
        PropertiesUtil util = new PropertiesUtil();
        session.setAttribute("avatarUrl", util.getAvatarUrl());
        map.put("avatarUrl", util.getAvatarUrl());
        return "personalPhoto";
    }

    @RequestMapping(value = "/personalPhoto", method = RequestMethod.GET)
    public String personalPhoto(ModelMap map, HttpServletResponse response, HttpServletRequest request) {
        /**
         * 当前头像的地址放在config.properties里，每次上传也会更新
         */
        PropertiesUtil util = new PropertiesUtil();
        map.put("avatarUrl", util.getAvatarUrl());
        return "personalPhoto";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String uploadImgs(
            @RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "h") String h,
            @RequestParam(value = "w") String w,
            HttpServletResponse response,
            HttpServletRequest request,@RequestParam(value = "file") MultipartFile file) {
        if(!FileSizeUtil.getFileSizeSuiteFlag(file.getSize(),Long.parseLong(MAX_FILE_SIZE))){
            logger.error("上传头像文件大小不符合要求");
            BaseResult<String> resultError = new BaseResult<String>();
            resultError.setErrorCode(-2);
            resultError.setResultMsg("文件大小不符合,上传失败");
            return JSON.toJSONString(resultError);
        }
        String fileName = file.getOriginalFilename();
        //IE上传进来的头像可能是全路径，这里只要文件名
        if(fileName.indexOf("\\")>0){
            fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        }
        BaseResult<String> resultUpdate = new BaseResult<String>();
        File fileAfterCut = null;
        File fileSrc = null;
        try {
            logger.info("==========StartCutAndUploadPhoto=============");
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String resourcePath = "resources/uploadImages/";
            String saveName = "";
            int imageX = Integer.parseInt(new BigDecimal(x).setScale(0, BigDecimal.ROUND_DOWN).toString());
            int imageY = Integer.parseInt(new BigDecimal(y).setScale(0, BigDecimal.ROUND_DOWN).toString());
            int imageH = Integer.parseInt(new BigDecimal(h).setScale(0, BigDecimal.ROUND_DOWN).toString());
            int imageW = Integer.parseInt(new BigDecimal(w).setScale(0, BigDecimal.ROUND_DOWN).toString());
            if (file != null) {
                if (FileUploadUtil.allowUpload(file.getContentType())) {
                    saveName = FileUploadUtil.rename(fileName);
                    File dir = new File(realPath + resourcePath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    fileSrc = new File(dir, saveName + "_src.jpg");
                    file.transferTo(fileSrc);
                    logger.info("fileSrc是否存在:"+fileSrc.exists());
                    BaseResult<String> resultErrorCheck = new BaseResult<String>();
                    if(!FileUploadUtil.checkImageLegal(file,fileSrc,resultErrorCheck)){
                        //验证图片的合法性，之所以不在开头处验证，是因为验证需要生成一张新的图片
                        return JSON.toJSONString(resultErrorCheck);
                    }
                    String srcImagePath = realPath + resourcePath + saveName;
                    //这里开始截取操作
                    if(imageX==-1||imageY==-1||imageH==-1||imageW==-1||imageH==0||imageW==0){
                        //来自IE8、9的上传图片请求,取消截图功能,将来再考虑
                        //or  用户在图片上做了特殊操作（例如右击图片），导致截图框消失，截图坐标变为零

                    }else{
                        logger.info("==========imageCutStart=============");
                        ImgCut.imgCut(srcImagePath, imageX, imageY, imageW, imageH);
                        logger.info("==========imageCutEnd===============");
                        request.getSession().setAttribute("imgSrc", resourcePath + saveName + "_src.jpg");//成功之后显示用
                        request.getSession().setAttribute("imgCut", resourcePath + saveName + "_cut.jpg");//成功之后显示用
                    }
                }else{
                    logger.error("上传头像格式不符合要求");
                    BaseResult<String> resultError = new BaseResult<String>();
                    resultError.setErrorCode(-2);
                    resultError.setResultMsg("文件格式不符合,上传失败");
                    return JSON.toJSONString(resultError);
                }
            }
            fileAfterCut = new File(realPath + resourcePath + saveName + "_cut.jpg");
            logger.info("fileAfterCut是否存在:"+fileAfterCut.exists());
            if(imageX==-1||imageY==-1||imageH==-1||imageW==-1||imageH==0||imageW==0){
                fileAfterCut = new File(realPath + resourcePath + saveName + "_src.jpg");
            }
            PropertiesUtil util = new PropertiesUtil();
            util.setAvatarUrl(fileAfterCut.getCanonicalPath().substring(fileAfterCut.getCanonicalPath().indexOf("resources")));

        } catch (Exception e) {
            logger.error("图片上传出错", e);
            BaseResult<String> resultError = new BaseResult<String>();
            resultError.setErrorCode(-3);
            return JSON.toJSONString(resultError);
        }finally {
            /**
             * 这里请根据自己的需求删除文件
             * 1.如果你需要把文件再上传到其他地方，那么src和cut都应该删除
             * 2.如果不需要上传到其他地方，可以删除掉src
             */
            //删除临时文件
//            if(null!=fileAfterCut&&fileAfterCut.exists()){
//                fileAfterCut.delete();
//            }
//            if(null!=fileSrc&&fileSrc.exists()){
//                fileSrc.delete();
//            }
        }
        logger.info("==========EndCutAndUploadPhoto=============");
        resultUpdate.setResultEnum(CommonCode.SUCCESS);
        return JSON.toJSONString(resultUpdate);
    }
}
