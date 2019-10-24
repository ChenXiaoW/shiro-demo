package com.chenw.shirodev.service;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.Permission;

/**
 * 权限业务接口
 *
 * @author chenw
 * @title: PermissionService
 * @date 2019/10/21 14:13
 */
public interface PermissionService {
    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    BaseModel addPermission(Permission permission);
}
