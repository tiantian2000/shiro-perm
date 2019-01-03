package com.perm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perm.mapper.RoleMapper;
import com.perm.model.Permission;
import com.perm.model.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	public List<Role> selAll(){
		return roleMapper.selectAll();
	}

	@Transactional
	public void add(Role role) {
		// TODO Auto-generated method stub
		this.roleMapper.add(role);
		System.out.println(role.getId());
		Map<String,Object> hm = new HashMap<String,Object>();
		if(role.getPermissions() != null) {
			for(Integer p:role.getPermissions()) {
				hm.put("roleId", role.getId());
				hm.put("permId", p);
				roleMapper.addRolePerm(hm);
			}
		}
		
	}

	@Override
	public Role selById(Integer id) {
		// TODO Auto-generated method stub
		Role role = this.roleMapper.selById(id);
		List<Permission> list = role.getPermsList();
		Integer[] arr = new Integer[list.size()];
		int i=0;
		for(Permission p:list) {
			arr[i] = p.getId();
			i++;
		}
		role.setPermissions(arr);
		return role;
	}

	@Transactional
	public void update(Role role) {
		// TODO Auto-generated method stub
		this.roleMapper.update(role);
		this.roleMapper.delPermissionByRole(role.getId());
		Map<String,Object> hm = new HashMap<String,Object>();
		if(role.getPermissions() != null) {
			for(Integer p:role.getPermissions()) {
				hm.put("roleId", role.getId());
				hm.put("permId", p);
				roleMapper.addRolePerm(hm);
			}
		}
		
	}

	@Transactional
	public void del(Integer id) {
		// TODO Auto-generated method stub
		this.roleMapper.del(id);
		this.roleMapper.delPermissionByRole(id);
		this.roleMapper.delUsersRolesByRole(id);
	}
	
	
	
	
	
}
