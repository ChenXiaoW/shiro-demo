package com.chenw.shirodev.controller;

import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.Permission;
import com.chenw.shirodev.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理请求
 *
 * @author chenw
 * @title: PermissionController
 * @date 2019/10/21 14:04
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    @RequiresPermissions({"permission:addPermission"})
    @PostMapping("/addPermission")
    BaseModel addPermission(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }
}
