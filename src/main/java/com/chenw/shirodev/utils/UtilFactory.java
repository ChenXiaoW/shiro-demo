package com.chenw.shirodev.utils;

import java.util.UUID;

/**
 * 工具类
 *
 * @author chenw
 * @title: UtilFactory
 * @date 2019/10/21 14:10
 */
public class UtilFactory {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
