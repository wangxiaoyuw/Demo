package com.boke.controller;


import com.boke.pojo.Menu;
import com.boke.pojo.User;
import com.boke.service.IUserService;
import com.boke.utils.CryptographyUtil;
import com.boke.utils.MenuUtil;
import com.boke.utils.ValidateCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangzy on 2017/6/12.
 */
@Controller
public class LoginController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/login")
    public String a(){
        return "login";
    }

    @RequestMapping(value = "/echarts")
    public String aa(){
        return "echarts";
    }

    /**
     * 登录
     * @param user
     * @param session
     * @param rememberMe
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,User user, HttpSession session,String rememberMe, Model model){
        Subject subject = SecurityUtils.getSubject();
        String newPassword = CryptographyUtil.md5(user.getPassword(),"eson_15");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),newPassword);

        try {
          //  token.setRememberMe(true);
            subject.login(token);

            String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
            if (exceptionClassName!=null){
                if("randomCodeError".equals(exceptionClassName)) {

                    model.addAttribute("errorInfo","验证码错误");
                    return "login";
                }

            }

            User user1 = userService.getByName(user.getUsername());
            session.setAttribute("user", user1);
            //通过menu ,查出menu实体类，放到list中。在页面上循环获取菜单。
            List<Menu> menus = userService.findAllMenuById(user1.getId());


            Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
            model.addAttribute("map", map);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("errorInfo","用户名不存在");
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            model.addAttribute("errorInfo","用户名或密码错误");
            return "login";
        }

    }

    /**
     * 退出清楚缓存 或者在xml配置 /logout = logout
     * @return
     */

/*    @RequestMapping("/logout")
    public String b(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        return "login";
    }*/


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }



}
