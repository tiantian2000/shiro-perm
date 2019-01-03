package com.perm.mapper;

import java.util.List;
import java.util.Map;

import com.perm.model.Role;


public interface RoleMapper {

	List<Role> selectAll();
	
    void add(Role role);
    
    void addRolePerm(Map<String,Object> hm);
    
    Role selById(Integer id);
    
    void delPermissionByRole(Integer id);
    
    void update(Role role);
    
    void delUsersRolesByRole(Integer id);
    
    void del(Integer id);

}