package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 用户角色模型
 *
 * @author chenw
 * @title: UserRoleMapping
 * @date 2019/10/21 10:51
 */
@Data
public class UserRoleMapping {
    /** id */
    private Integer id;
    /** 用户id */
    private String userId;
    /** 角色id */
    private String roleId;
}
