package com.perm.shiro;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.perm.model.Permission;
import com.perm.model.Role;
import com.perm.model.User;
import com.perm.service.UserService;

/**自定义Realm
 * Created by Administrator on 2018/12/22.
 */
public class UserRealm extends AuthorizingRealm{

    @Resource    
    private UserService userService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //取出认证逻辑转过来的用户
        User user = (User)principalCollection.getPrimaryPrincipal();
        //给资源进行授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加资源的授权角色
        simpleAuthorizationInfo.setRoles(getRoles(user.getRolesList()));
        //添加资源的授权权限
        simpleAuthorizationInfo.addStringPermissions(getPermissionsByUserName(user.getUsername()));
        return simpleAuthorizationInfo;
    }

    /**
     * 获取授权角色名称的HashSet
     * @param roleList
     * @return
     */
    public Set<String> getRoles(List<Role> roleList){
        Set<String> roles = new HashSet<String>();
        for(Role role:roleList){
            roles.add(role.getRoleCode());
        }
        return roles;
    }

    /**
     * 获取授权权限名称的HashSet
     * @param username
     * @return
     */
    private Set<String> getPermissionsByUserName(String username) {
        List<Permission> list = this.userService.findPermissonByUsername(username);
        System.out.println(list);
        Set<String> set = new HashSet<String>();
        for(Permission p:list){
            set.add(p.getPermCode());
        }
        return set;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //模拟数据库中有用户admin,123456
        /*String name = "admin";
        String password = "123456";
        */
        //编写shiro判断逻辑,判断用户名和密码
        //1.判斷用户名
        /*if(!token.getUsername().equals(name)){
            //用户名不存在,会抛出UnknowAccountException
            return null;
        }*/
        //2.判断密码
        //第一个参数:需要返回给subject.login的方法
        //第二个参数:数据库中查询到的用户的密码
        //第三个参数:当前realm的名称
        /*SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("",password,"");*/
        //1.查询用户
        User user = userService.findByUserName(token.getUsername());
        System.out.println(user);
        if(user == null){
            //用户名不存在,会抛出UnknowAccountException
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),"");
        //使用用户名加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
        return simpleAuthenticationInfo;
    }
}
