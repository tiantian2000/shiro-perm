package com.perm.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.perm.mapper.PermissionMapper;
import com.perm.mapper.UserMapper;
import com.perm.model.Permission;
import com.perm.model.Role;
import com.perm.model.User;

/**
 * Created by Administrator on 2018/12/22.
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据用户名查询用户
     */
    @Override
    public User findByUserName(String username) {
        return userMapper.selectByUsername(username);
    }

    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public void add(User user) {
        String password = user.getPassword();
        Md5Hash md5Hash = new Md5Hash(password,user.getUsername());
        user.setPassword(md5Hash.toString());
        userMapper.add(user);
    }

    public List<Permission> findPermissonByUsername(String username){
        System.out.println("查询数据库");
        return permissionMapper.selectByUsername(username);
    }

	@Override
	public PageInfo<User> selectAll(Integer currPage) {
		// TODO Auto-generated method stub
		if(currPage == null) currPage = 1;
	    PageHelper.startPage(currPage,5);
	    PageInfo<User> pageInfo = new PageInfo<User>(this.userMapper.selectAll());
		return pageInfo;
	}

	@Override
	public User selectById(Integer id) {
		// TODO Auto-generated method stub
		User user = this.userMapper.selectById(id);
		List<Role> list = user.getRolesList();
		Integer[] arr = new Integer[list.size()];
		int i=0;
		for(Role r:list) {
			arr[i] = r.getId();
			i++;
		}
		user.setRoles(arr);
		return user;
	}
	
	@Transactional
	public void update(User user) {
		this.userMapper.delRoleByUsername(user.getUsername());
		Map<String,Object> hm = new HashMap<String,Object>();
		if(user.getRoles() != null) {
			for(Integer r:user.getRoles()) {
				hm.put("username", user.getUsername());
				hm.put("roleId", r);				
				userMapper.addUserRole(hm);
			}
		}
	}
}
