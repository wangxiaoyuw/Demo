package com.boke.utils;

/**
 * Created by wangxy on 2017/4/7.
 */

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
    public String getAvatarUrl() {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            //读取属性文件config.properties
            in = new BufferedInputStream(new FileInputStream(this.getClass().getResource("/").getPath()+"config.properties"));
            prop.load(in);
            return prop.get("avatar").toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String setAvatarUrl(String avatarUrl) {
        FileOutputStream oFile  = null;
        try {
            Properties prop = new Properties();
            //读取属性文件config.properties
            oFile = new FileOutputStream(this.getClass().getResource("/").getPath()+"config.properties", false);//true表示追加打开
            prop.setProperty("avatar", avatarUrl);
            prop.store(oFile, "The New properties file");
            return "1";
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}