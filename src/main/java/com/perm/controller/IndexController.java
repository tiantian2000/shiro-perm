package com.perm.controller;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.perm.model.User;
import com.perm.service.UserService;

/**
 * Created by Administrator on 2018/12/22.
 */
@Controller
@RequestMapping(value="/")
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping(value="/")
    public String index() {
    	return "main";
    }
    
/*    *//**
     * 注册
     * @param user
     * @return
     *//*
    @RequestMapping(value="register")
    public String register(User user){
        System.out.println(user);
        userService.add(user);
        return "/login";
    }*/

    @RequestMapping(value="register")
    @ResponseBody
    public JSONObject register(User user){
        System.out.println(user);
        JSONObject object = new JSONObject();
        userService.add(user);
        object.put("data", 200);
        return object;
    }

    
    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping(value="toRegister")
    public String toRegister(){
        return "/register";
    }

    /**
     * 未授权
     * @return
     */
    @RequestMapping(value="unAuth")
    public String unAuth(){
        System.out.println("------没有权限-------");
        return "/unauth";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "/login";
    }

    /**
     * 跳转到登录页面
     * @param user
     * @return
     */
    @RequestMapping(value="/toLogin",method = RequestMethod.GET)
    public String toLogin(User user){
        return "/login";
    }

/*    *//**
     * 登录
     * @param user
     * @param model
     * @return
     *//*
    @RequestMapping(value="login")
    public String login(User user,boolean rememberMe,Model model){
        System.out.println(rememberMe);
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //3.执行登录方法
        //此方法如果没有异常,则登录成功
        try{
            token.setRememberMe(rememberMe);
            subject.login(token);
            //登录成功
            return "redirect:/main";
        }catch(UnknownAccountException e){
            e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "/login";
        }catch(IncorrectCredentialsException e){
            e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg","密码错误");
            return "/login";
        }

    }*/
    
    
    /**
     * 登录
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value="login")
    @ResponseBody
    public JSONObject login(User user,boolean rememberMe){
        System.out.println(rememberMe);
        JSONObject object=new JSONObject();
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //3.执行登录方法
        //此方法如果没有异常,则登录成功
        try{
            token.setRememberMe(rememberMe);
            subject.login(token);
            //登录成功
            object.put("data", 0);
            //return "redirect:/main";
        }catch(UnknownAccountException e){
            e.printStackTrace();
            //登录失败:用户名不存在
            //model.addAttribute("msg","用户名不存在");
            object.put("data", 1);
            //return "/login";
        }catch(IncorrectCredentialsException e){
            e.printStackTrace();
            //登录失败:密码错误
            //model.addAttribute("msg","密码错误");
            //return "/login";
            object.put("data", 2);
        }
        
        return object;

    }

    @RequestMapping(value="main")
    public String main(){
        return "main";
    }
    
    @RequestMapping(value="info")
    public String info() {
    	return "info";
    }

}
