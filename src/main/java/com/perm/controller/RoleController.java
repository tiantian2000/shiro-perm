package com.perm.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perm.model.Role;
import com.perm.service.PermissionService;
import com.perm.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", roleService.selAll());
		return "/user/role_list";
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping("goAdd")
	public String goAdd(Model model) {
		model.addAttribute("permList", permissionService.selectAll());
		return "/user/role_add";
	}
	
	@RequiresPermissions("role:add")
	@RequestMapping("add")
	public String add(Role role) {
		System.out.println("add");
		roleService.add(role);
		return "redirect:/role/list";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,Model model) {
		model.addAttribute("role",this.roleService.selById(id));
		model.addAttribute("permList", permissionService.selectAll());
		return "/user/role_update";
	}
	
	@RequestMapping("update")
	public String updat(Role role) {
		roleService.update(role);
		return "redirect:/role/list";
	}
	
	@RequestMapping("del")
	public String del(Integer id) {
		roleService.del(id);
		return "redirect:/role/list";
	}
}
