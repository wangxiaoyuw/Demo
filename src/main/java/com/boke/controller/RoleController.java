package com.boke.controller;

import com.alibaba.fastjson.JSONArray;
import com.boke.pojo.Menu;
import com.boke.pojo.Role;
import com.boke.pojo.RoleAndPrem;
import com.boke.pojo.User;
import com.boke.service.IUserService;

import com.boke.service.MenuService;
import com.boke.service.RoleService;
import com.boke.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangzy on 2017/6/20.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private IUserService userService;

    @Resource
    private MenuService menuService;

    @RequestMapping("/list")
    public String list(Model model,HttpSession session){

        User user = (User) session.getAttribute("user");
        List<Menu> menus = userService.findAllMenuById(user.getId());
        Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
        model.addAttribute("map", map);
        List<Role> roleList = roleService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "role/list";
    }


    @RequestMapping("/add")
    public String add(){
        return "role/add";
    }

    /**
     * 新增保存
     * @param rolename
     * @param roledesc
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saverole", method = RequestMethod.POST)
    public String addsave(@RequestParam("rolename")String rolename,@RequestParam("roledesc")String roledesc){
        int a = 0;
        roleService.saveRole(rolename,roledesc,a);
        return "success";
    }


    /**
     * 更新
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id")Integer id, Model model){
        Role role = roleService.findRolesByRoleId(id);
        model.addAttribute("role",role);
        return "role/update";
    }

    /**
     * 修改
     * @param id
     * @param rolename
     * @param roledesc
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/usave", method = RequestMethod.POST)
    public String updatesave(@RequestParam("id")String id,@RequestParam("rolename")String rolename,@RequestParam("roledesc")String roledesc){
        int a = 0;
        roleService.updateRole(id,rolename,roledesc,a);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id,Model model){
        roleService.deleteByRoleId(id);
        roleService.deleteByRoleId2(id);
        roleService.deleteRoleByRoleId(id);
        return "success";
    }

    @RequestMapping("/tree/{id}")
    public String tree(@PathVariable("id")Integer id,Model model){

        model.addAttribute("id",id);
        return "/role/tree";
    }

    /**
     * 展示权限列
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/treedata",method = RequestMethod.POST)
    public JSONArray treedata(){
        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        List<Menu> list = menuService.findAllMenu();
        for (int i =0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getChildren()!=null?list.get(i).getChildren():0);
            map.put("name",list.get(i).getDescription());
            mapList.add(map);
        }
        JSONArray jsonarray = (JSONArray) JSONArray.toJSON(mapList);
        return jsonarray;
    }


    /**
     * 展示当前用户权限列
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showtree",method = RequestMethod.POST)
    public JSONArray treedata1(@RequestParam("id")Integer id){
        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
       // List<Menu> list = menuService.findAllMenu();
        List<Menu> list = userService.findAllMenuById(id);
        for (int i =0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getChildren()!=null?list.get(i).getChildren():0);
            map.put("name",list.get(i).getDescription());
            mapList.add(map);
        }
        JSONArray jsonarray = (JSONArray) JSONArray.toJSON(mapList);
        return jsonarray;
    }

    /**
     * 展示所属角色权限列
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showtree1",method = RequestMethod.POST)
    public JSONArray treedata11(@RequestParam("id")Integer id){
        List<Map<String,Object>> mapList = new ArrayList<Map<String, Object>>();
        // List<Menu> list = menuService.findAllMenu();
        List<Menu> list = userService.findAllMenuByRoleId(id);
        for (int i =0;i<list.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getChildren()!=null?list.get(i).getChildren():0);
            map.put("name",list.get(i).getDescription());
            mapList.add(map);
        }
        JSONArray jsonarray = (JSONArray) JSONArray.toJSON(mapList);
        return jsonarray;
    }


    /**
     * 权限保存
     * @param id
     * @param mid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestParam("id")Integer id,@RequestParam("mid")String mid){
        // Role role =  roleService.findRolesByUserId(id);

        List<RoleAndPrem> list = menuService.findAllMenuByRole(id);
        for (int i =0;i<list.size();i++){
            roleService.deleteByRoleId(list.get(i).getRoleid());
        }

         String a [] = mid.split(",");
         for (int i=0;i<a.length;i++){
             String b = a[i];
             int c = Integer.parseInt(b, 10);

             RoleAndPrem roleAndPrem = new RoleAndPrem();
             roleAndPrem.setRoleid(id);
             roleAndPrem.setPremid(c);
            roleService.saveTree(roleAndPrem);
         }
         return "success";
    }



}
