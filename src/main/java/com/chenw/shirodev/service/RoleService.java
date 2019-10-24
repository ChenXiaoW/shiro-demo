package com.chenw.shirodev.service;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.Role;
import com.chenw.shirodev.model.RolePermissionMapping;
import com.chenw.shirodev.model.UserRoleMapping;

/**
 * 角色业务接口
 *
 * @author chenw
 * @title: RoleService
 * @date 2019/10/21 13:58
 */
public interface RoleService {
    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    BaseModel addRole(Role role);

    /**
     * 用户角色映射
     *
     * @param userRoleMapping
     * @return
     */
    BaseModel addUserRoleMapping(UserRoleMapping userRoleMapping);

    /**
     * 角色权限映射
     *
     * @param rolePermissionMapping
     * @return
     */
    BaseModel addRolePermissionMapping(RolePermissionMapping rolePermissionMapping);
}
