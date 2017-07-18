package com.boke.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boke.pojo.*;
import com.boke.service.IUserService;
import com.boke.service.MenuService;
import com.boke.service.RoleService;
import com.boke.utils.CryptographyUtil;
import com.boke.utils.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;

	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}


	/**
	 * 分页列表
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model,HttpSession session){
	    User user = (User) session.getAttribute("user");
		List<Menu> menus = userService.findAllMenuById(user.getId());
		Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
		model.addAttribute("map", map);
		String pageNow = request.getParameter("pageNow");

		Page page = null;
		int totalCount = (int) userService.getUserCount();
		List allUser;
		if (pageNow!=null){
			page = new Page(totalCount,Integer.parseInt(pageNow));
			 allUser = this.userService.selectUserByPage(page.getStartPos(),page.getPageSize());
		}else {
			page = new Page(totalCount,1);
			allUser = this.userService.selectUserByPage(page.getStartPos(),page.getPageSize());
		}
       model.addAttribute("url","/user/list");
		model.addAttribute("list", allUser);
		model.addAttribute("page", page);
		return "/user/list";
	}

    /**
     * 权限树
     * @param model
     * @return
     */
	@RequestMapping("/tree")
	public String showtree(Model model){
		return "tree";
	}

	/**
	 * 新增页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model,HttpSession session){
		List<Role> roleList = roleService.findAllRole();
		User user = (User) session.getAttribute("user");
		List<Menu> menus = userService.findAllMenuById(user.getId());
		Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
		model.addAttribute("map", map);
		model.addAttribute("rolelist",roleList);
		return "user/add";
	}

    /**
	 * 修改页面
	 * @param id
	 * @param model
	 * @return
	 */
    @RequestMapping("/update/{id}")
	public String updateuser(@PathVariable("id")Integer id,Model model){
		User user =userService.findUserById(id);
		List<Role> roleList = roleService.findAllRole();
		List<Menu> menus = userService.findAllMenuById(user.getId());
		Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
		model.addAttribute("roleList",roleList);
		model.addAttribute("map", map);
		model.addAttribute("user",user);
		return "user/update";
	}


    /**
	 * 新增保存
	 * @param username
	 * @param rolename
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestParam("username") String username,@RequestParam("rolename") String rolename){

		User user2 = userService.getByName(username);
		if (user2==null){
			User user = new User();
			user.setUsername(username);
			user.setPassword(CryptographyUtil.md5("123","eson_15"));
			userService.saveUser(user);
			User user1 =  userService.getByName(username);
			Role role = roleService.findRolesByRolename(rolename);
			userService.saveUserAndRole(user1.getId(),role.getId());
		}else {
			Role role = roleService.findRolesByRolename(rolename);
			userService.saveUserAndRole(user2.getId(),role.getId());
		}

      return "success";
	}



    /**
	 * 修改保存
	 * @param id
	 * @param username
	 * @param rolename
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/usave", method = RequestMethod.POST)
	public String saveupdate(@RequestParam("id") Integer id,@RequestParam("username") String username,@RequestParam("rolename") String rolename){

		User user = userService.findUserById(id);
		user.setUsername(username);
		user.setPassword(CryptographyUtil.md5("123","eson_15"));
		userService.updateUser(id,username);

		Role role = roleService.findRolesByRolename(rolename);
		List<UserAndRole> list = userService.findAllById(id);
		for (int i=0;i<list.size();i++){
			userService.deteleById(list.get(i).getUserid());
		}
		userService.saveUserAndRole(id,role.getId());
		return "success";
	}


    /**
	 * 级联删除
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public String detele(@PathVariable("id")Integer id,Model model){
		userService.deteleById(id);
        userService.deteleUandRById(id);
		return "success";
	}

	/**
	 * 分页查询
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/findu")
	public String list2(Model model,HttpSession session,HttpServletRequest request){

		User user = (User) session.getAttribute("user");
		List<Menu> menus = userService.findAllMenuById(user.getId());
		Map<Menu, List<Menu>> map = MenuUtil.getMenu(menus);
		model.addAttribute("map", map);

		Page page = null;
		String param = request.getParameter("param");


		//将查询的参数保存
		String condition = (String) session.getAttribute("condition");

		//先判断SESSION中的condition是否为空
		if (condition == null) {
			condition = new String();
			session.setAttribute("condition", condition);
			//如果Session中的condition为空，再判断传入的参数是否为空，如果为空就跳转到搜索结果页面
			if (param == null || "".equals(param)) {
				return "user/list";
			}
		}
		//如果SESSION不为空，且传入的搜索条件param不为空，那么将param赋值给condition
		if (param != null && !("".equals(param))) {
			condition = param;
			session.setAttribute("condition", condition);
		}

		String pageNow = request.getParameter("pageNow");
		int totalCount = (int) userService.getUserParamCount(condition);
        List allUser;
		if (pageNow!=null){
			page = new Page(totalCount,Integer.parseInt(pageNow));
			 allUser = this.userService.selectUserByParamPage(page.getStartPos(),page.getPageSize(),condition);
		}else {
			page = new Page(totalCount,1);
			 allUser = this.userService.selectUserByParamPage(page.getStartPos(),page.getPageSize(),condition);
		}
		model.addAttribute("url","/user/findu");
		model.addAttribute("list", allUser);
		model.addAttribute("page", page);

		return "/user/list";
	}

	@RequestMapping("/updateSecret")
	public String updateSecret(){
		return "user/passwork";
	}

	/**
	 * 验证原密码
	 * @param oldpassword
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/oldpassword", method = RequestMethod.POST)
	public String password(@RequestParam("oldpassword")String oldpassword,
						   @RequestParam("id")Integer id){
		String a = oldpassword;
		String p = CryptographyUtil.md5(a,"eson_15");
		String password=userService.findUserById(id).getPassword();
		String result ;
		if (p.equals(password)){
		result ="dui";
		}else {
			result ="error";
		}
		return result;
	}

	/**
	 * 保存新密码
	 * @param newpassword
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savep",method = RequestMethod.POST)
	public String savep(@RequestParam("newpassword")String newpassword,@RequestParam("id")Integer id){
		String a = newpassword;
		String p = CryptographyUtil.md5(a,"eson_15");
		userService.saveUserPassword(id,p);
		return "success";
	}
}
