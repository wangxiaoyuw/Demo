package com.boke.realm;

import com.boke.pojo.User;
import com.boke.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by wangzy on 2017/6/13.
 */

public class MyRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    /**
     * 为当前登陆的用户授予角色和权限
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       String username = (String) principals.getPrimaryPrincipal();
        User user = userService.getByName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRolesByUserId(user.getId()));
        authorizationInfo.setStringPermissions(userService.findMenuByUserId(user.getId()));
        return authorizationInfo;
    }

    /**
     * 对前登陆的用户进行身份认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); // 根据刚刚传过来的获取用户名
        User user = userService.getByName(username); // 根据用户名从数据库中查询出信息
        if (user==null){
            throw new UnknownAccountException("msg:用户不存在");
        }
        if (user != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);//把当前用户存到session中
            //把从数据库中查询出来的信息放到authcInfo中返回给Shiro
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                    user.getUsername(), user.getPassword(), "MyRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
