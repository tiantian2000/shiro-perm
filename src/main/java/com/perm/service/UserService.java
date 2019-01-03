package com.perm.service;



import java.util.List;

import com.github.pagehelper.PageInfo;
import com.perm.model.Permission;
import com.perm.model.Role;
import com.perm.model.User;

/**
 * Created by Administrator on 2018/12/22.
 */
public interface UserService {

	PageInfo<User> selectAll(Integer currPage);
	
    User findByUserName(String username);

    List<Permission> findPermissonByUsername(String username);

    void logout();

    void add(User user);
    
    User selectById(Integer id);
    
    void update(User user);

}
