package com.chenw.shirodev.dao;

import com.chenw.shirodev.model.Permission;
import com.chenw.shirodev.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久层
 *
 * @author chenw
 * @title: PermissionDao
 * @date 2019/10/21 14:07
 */
@Repository
public interface PermissionDao {
    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    Integer addPermission(Permission permission);

    /**
     * 根据角色id查询权限
     *
     * @param role
     * @return
     */
    List<Permission> queryPermissionByRoleId(Role role);
}
