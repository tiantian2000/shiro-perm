package com.perm.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.perm.model.Permission;

public interface PermissionService {

	List<Permission> selectAll();
	
	PageInfo<Permission> selectAll(Permission perm,Integer currPage);
	
	void add(Permission permission);
	
	Permission selById(Integer id);
	
	void del(Integer id);
	
	void update(Permission perm);
}
