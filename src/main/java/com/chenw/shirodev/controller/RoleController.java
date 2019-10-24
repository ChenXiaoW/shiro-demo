package com.chenw.shirodev.controller;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.Role;
import com.chenw.shirodev.model.RolePermissionMapping;
import com.chenw.shirodev.model.UserRoleMapping;
import com.chenw.shirodev.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理请求
 *
 * @author chenw
 * @title: RoleController
 * @date 2019/10/21 13:50
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequiresPermissions(value ={ "role:add"})
    @PostMapping("/addRole")
    BaseModel addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    /**
     * 添加用户角色
     *
     * @param userRoleMapping
     * @return
     */
    @RequiresPermissions(value = {"role:addUserRoleMapping"})
    @PostMapping("/addUserRoleMapping")
    BaseModel addUserRoleMapping(@RequestBody UserRoleMapping userRoleMapping){
        return roleService.addUserRoleMapping(userRoleMapping);
    }

    /**
     * 添加角色权限
     *
     * @param rolePermissionMapping
     * @return
     */
    @RequiresPermissions(value = {"role:addRolePermissionMapping"})
    @PostMapping("/addRolePermissionMapping")
    BaseModel addRolePermissionMapping(@RequestBody RolePermissionMapping rolePermissionMapping){
        return roleService.addRolePermissionMapping(rolePermissionMapping);
    }
}
