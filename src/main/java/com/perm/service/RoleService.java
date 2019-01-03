package com.perm.service;

import java.util.List;

import com.perm.model.Role;

public interface RoleService {

	public List<Role> selAll();
	
	public void add(Role role);
	
    Role selById(Integer id);
    
    void update(Role role);
    
    void del(Integer id);
}
