package com.mybatis.test.controller;

import com.mybatis.test.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liujianguo
 * @data 2019/4/15
 * 描述：
 */
@Controller
@RequestMapping("/login")
public class LoginController {



    @GetMapping("/jumpLogin")
    public String jumpLogin(Model model){
        return "login/login";
    }
    @GetMapping("/jumpLogout")
    public String jumpLogout(){
        return "login/login";
    }


    @PostMapping("/login")
    public String login(SysUser sysUser, Model model){

        //执行认证逻辑
        final Subject subject = SecurityUtils.getSubject();
        //封装用户名密码
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassword());

        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("name","用户名不存在");
            return "login/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("name","密码错误");
            return "login/login";
        }catch (AuthenticationException e){
            model.addAttribute("name","用户名/密码错误");
            return "login/login";
        }
        System.out.println("sysUser:--->"+ sysUser);
        return "index";

    }
}
