package com.perm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.perm.mapper.PermissionMapper;
import com.perm.model.Permission;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> selectAll() {
		// TODO Auto-generated method stub
		return permissionMapper.selectAll();
	}

	@Override
	public PageInfo<Permission> selectAll(Permission perm,Integer currPage) {
		// TODO Auto-generated method stub
		if(currPage == null) currPage = 1;
	    PageHelper.startPage(currPage,5);
	    PageInfo<Permission> pageInfo = new PageInfo<Permission>(this.permissionMapper.queryAll(perm));
		return pageInfo;
	}

	@Override
	public void add(Permission permission) {
		// TODO Auto-generated method stub
		this.permissionMapper.add(permission);
	}

	@Override
	public Permission selById(Integer id) {
		// TODO Auto-generated method stub
		return this.permissionMapper.selectById(id);
	}

	@Transactional
	public void del(Integer id) {
		// TODO Auto-generated method stub
		this.permissionMapper.del(id);
		this.permissionMapper.delRolesPermissions(id);
	}

	@Override
	public void update(Permission perm) {
		// TODO Auto-generated method stub
		this.permissionMapper.update(perm);
	}

}
