package com.chenw.shirodev.controller;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.FailModel;
import com.chenw.shirodev.model.SuccessModel;
import com.chenw.shirodev.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共请求
 *
 * @author chenw
 * @title: PublicController
 * @date 2019/10/21 10:55
 */
@RestController
@RequestMapping("/public")
public class PublicController {
    /**
     * 登陆请求
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    BaseModel login(@RequestBody User user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return new SuccessModel<String>("登陆成功");
    }

    /**
     * 注销请求
     *
     * @return
     */
    @PostMapping("/logout")
    BaseModel logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new SuccessModel<String>("注销登陆");
    }

}
