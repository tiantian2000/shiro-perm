package com.perm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**测试Shiro
 * Created by Administrator on 2018/11/12.
 */
@Controller
@RequestMapping(value="/test")
public class TestController {
 
    @RequestMapping(value="main")
    public String main(){
        return "main";
    }  



}
