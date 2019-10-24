package com.chenw.shirodev.dao;

import com.chenw.shirodev.model.Role;
import com.chenw.shirodev.model.RolePermissionMapping;
import com.chenw.shirodev.model.User;
import com.chenw.shirodev.model.UserRoleMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层
 *
 * @author chenw
 * @title: RoleDao
 * @date 2019/10/21 13:52
 */
@Repository
public interface RoleDao {
    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    Integer addRole(Role role);

    /**
     * 添加用户角色
     *
     * @param userRoleMapping
     * @return
     */
    Integer addUserRoleMapping(UserRoleMapping userRoleMapping);

    /**
     * 添加角色权限
     *
     * @param permissionMapping
     * @return
     */
    Integer addRolePermissionMapping(RolePermissionMapping permissionMapping);

    /**
     * 根据用户id查询用户角色
     *
     * @param user
     * @return
     */
    List<Role> queryRoleByUserId(User user);
}
