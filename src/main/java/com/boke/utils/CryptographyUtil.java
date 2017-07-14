package com.boke.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by wangzy on 2017/6/13.
 */
public class CryptographyUtil {

    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args){
        String passwork="123";
        System.out.println("Md5加密："+CryptographyUtil.md5(passwork, "eson_15"));
    }
}
