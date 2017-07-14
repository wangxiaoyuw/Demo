package com.boke.utils;

import com.boke.pojo.Menu;
import com.boke.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * Created by wangzy on 2017/6/20.
 */
public class PermissionTag extends TagSupport {

    private String module;
    private String code;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
