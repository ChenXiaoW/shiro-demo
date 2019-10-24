package com.chenw.shirodev.advice;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.FailModel;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author chenw
 * @title: GlobalExceptionHandle
 * @date 2019/10/22 8:34
 */
@RestControllerAdvice   //定义全局异常处理
public class GlobalExceptionHandle {
    /**
     * 权限异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public BaseModel authorizationExceptionHandle(Exception e){
        return new FailModel<String>("该用户没有权限操作");
    }

    /**
     * 密码错误异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public BaseModel incorrectCredentialsExceptionHandle(Exception e){
        return new FailModel<String>("用户密码错误");
    }

    /**
     * 账号错误异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public BaseModel unknownAccountExceptionHandle(Exception e){
        return new FailModel<String>("用户账号不存在");
    }


}
