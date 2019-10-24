package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 角色模型
 *
 * @author chenw
 * @title: Role
 * @date 2019/10/21 10:44
 */
@Data
public class Role {
    /** 角色id */
    private String roleId;
    /** 角色名称 */
    private String roleName;
}
