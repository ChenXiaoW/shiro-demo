package com.chenw.shirodev.model;

import lombok.Data;

/**
 * 权限模型
 *
 * @author chenw
 * @title: Permission
 * @date 2019/10/21 10:45
 */
@Data
public class Permission {
    /** 权限id */
    private String permissionId;
    /** 权限名称 */
    private String permissionName;
    /** 权限标识 */
    private String permission;
}
