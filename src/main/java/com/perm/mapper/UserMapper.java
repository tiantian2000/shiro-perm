package com.perm.mapper;

import java.util.List;
import java.util.Map;

import com.perm.model.User;

public interface UserMapper {

	List<User> selectAll();
	
    User selectByUsername(String username);

    void add(User user);

    User selectById(Integer id);
    
    void delRoleByUsername(String username);
    
    void addUserRole(Map<String,Object> hm);
}