package com.perm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perm.model.Permission;
import com.perm.model.Role;
import com.perm.service.PermissionService;

@Controller
@RequestMapping("/perm")
public class PermController {

	@Resource
	private PermissionService permissionService;
	
	@RequestMapping(value="list")
	public String list(Permission perm,Integer currPage,Model model){
		model.addAttribute("perm", perm);
		model.addAttribute("page", this.permissionService.selectAll(perm,currPage));
		return "/user/perm_list";
	}
	
	@RequestMapping("goAdd")
	public String goAdd() {
		return "/user/perm_add";
	}
	
	@RequestMapping("add")
	public String add(Permission permission) {
		permissionService.add(permission);
		return "redirect:/perm/list";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,Model model) {
		model.addAttribute("perm",this.permissionService.selById(id));
		return "/user/perm_update";
	}
	
	@RequestMapping("update")
	public String updat(Permission permission) {
		permissionService.update(permission);
		return "redirect:/perm/list";
	}
	
	@RequestMapping("del")
	public String del(Integer id) {
		permissionService.del(id);
		return "redirect:/perm/list";
	}
}
