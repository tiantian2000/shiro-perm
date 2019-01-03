package com.perm.mapper;

import java.util.List;

import com.perm.model.Permission;

/**
 * Created by Administrator on 2018/12/23.
 */
public interface PermissionMapper {

    List<Permission> selectByUsername(String username);
    
    List<Permission> selectAll();
    
    List<Permission> queryAll(Permission perm);
    
    void add(Permission permission);
    
    Permission selectById(Integer id);
    
    void del(Integer id);
    
    void delRolesPermissions(Integer id);
    
    void update(Permission perm);
}
