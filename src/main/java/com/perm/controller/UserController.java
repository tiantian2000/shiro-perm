package com.perm.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perm.model.Role;
import com.perm.model.User;
import com.perm.service.RoleService;
import com.perm.service.UserService;

/**
 * Created by Administrator on 2018/12/22.
 */
//logical操作的默认检查方式是and
@RequiresRoles(value={"admin","teacher","student"}, logical = Logical.OR)
@Controller
@RequestMapping(value="/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleSrvice;
		
	@RequestMapping(value="list")
	public String list(Integer currPage,Model model){
		model.addAttribute("page", this.userService.selectAll(currPage));
		return "/user/user_list";
	}
	
    @RequiresPermissions("user:add")
    @RequestMapping(value="add")
    public String add(){
        return "/user/add";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value="toUpdate")
    public String toUpdate(Integer id,Model model){
    	model.addAttribute("user", this.userService.selectById(id));
    	model.addAttribute("rolesList",this.roleSrvice.selAll());
        return "/user/user_update";
    }

    @RequiresPermissions("user:update")
	@RequestMapping("update")
	public String updat(User user) {
		userService.update(user);
		return "redirect:/user/list";
	}
	
    @RequiresPermissions("user:delete")
    @RequestMapping(value="del")
    public String del(){
        return "/user/del";
    }
}
