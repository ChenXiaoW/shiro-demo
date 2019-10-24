package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 用户模型
 *
 * @author chenw
 * @title: User
 * @date 2019/10/21 10:42
 */
@Data
public class User {
    /** 用户id */
    private  String userId;
    /** 用户名称 */
    private String username;
    /** 用户密码 */
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
