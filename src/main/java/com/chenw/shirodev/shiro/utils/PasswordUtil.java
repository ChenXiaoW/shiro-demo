package com.chenw.shirodev.shiro.utils;

import com.chenw.shirodev.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码加密工具类
 *
 * @author chenw
 * @title: PasswordUtil
 * @date 2019/10/21 11:11
 */
public class PasswordUtil {

    public static String encryption(User user){
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getUsername(),3);
        return md5Hash.toString();
    }
}
