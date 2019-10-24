package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 角色权限模型
 *
 * @author chenw
 * @title: RolePermissionMapping
 * @date 2019/10/21 10:53
 */
@Data
public class RolePermissionMapping {
    /** id */
    private Integer id;
    /** 角色id */
    private String roleId;
    /** 权限id */
    private String permissionId;
}
