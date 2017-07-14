package com.boke.utils;


import com.boke.common.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FileUploadUtil {

	 private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);



	 public static final List<String> ALLOW_TYPES = Arrays.asList(
	            "image/jpg","image/jpeg","image/png","image/pjpeg","image/x-png"
	    );
	//文件重命名
	    public static String rename(String fileName){
			int end = fileName.lastIndexOf(".");
			if(end>50){
				end = 50;
			}
			return fileName.substring(0, end) + System.currentTimeMillis()+new Random().nextInt(99999999);
	    }
	//校验文件类型是否是被允许的
	    public static boolean allowUpload(String postfix){
	        return ALLOW_TYPES.contains(postfix);
	    }

	/**
	 * 如果能获得一个图片的宽、高，证明这是一个真正的图片
	 * @param imageFile
	 * @return
	 */
     public static boolean isImage(File imageFile) {
		if (!imageFile.exists()) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(imageFile);
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	 }

	/**
	 * 检查文件的合法性
	 * @param file
	 * @return
	 */
	 public static Boolean checkImageLegal(MultipartFile file, File fileSrcOnServer, BaseResult<String> resultErrorCheck){
	 	 //文件大小
		 if(!FileSizeUtil.getFileSizeSuiteFlag(file.getSize(),3L)){
			 logger.error("上传头像文件大小不符合要求:"+file.getSize());
			 resultErrorCheck.setErrorCode(-2);
			 resultErrorCheck.setResultMsg("文件大小不符合,上传失败");
			 return false;
		 }
		 if(!FileUploadUtil.allowUpload(file.getContentType())){
			 logger.error("上传头像文件后缀名不符合要求:"+file.getContentType());
			 resultErrorCheck.setErrorCode(-2);
			 resultErrorCheck.setResultMsg("文件格式错误,上传失败");
			 return false;
		 }
		 if(!FileUploadUtil.isImage(fileSrcOnServer)){
			 logger.error("上传头像文件后缀名符合要求,但内容不是一张图片,请注意:"+fileSrcOnServer.getName());
			 resultErrorCheck.setErrorCode(-2);
			 resultErrorCheck.setResultMsg("文件格式异常,上传失败");
			 return false;
		 }

	 	return true;
	 }
}
