package com.boke.utils;

public class FileSizeUtil {

	public static Boolean getFileSizeSuiteFlag(Long fileSize,Long maxFileSize){
		maxFileSize = maxFileSize * 1024 * 1024;
		if(fileSize>maxFileSize){
			return false;
		}else{
			return true;
		}
	}

}
