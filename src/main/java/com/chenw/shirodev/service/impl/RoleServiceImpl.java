package com.chenw.shirodev.service.impl;

import com.chenw.shirodev.dao.RoleDao;
import com.chenw.shirodev.model.*;
import com.chenw.shirodev.service.RoleService;
import com.chenw.shirodev.utils.UtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色业务接口实现
 *
 * @author chenw
 * @title: RoleServiceImpl
 * @date 2019/10/21 13:59
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public BaseModel addRole(Role role) {
        role.setRoleId(UtilFactory.getUUID());
        Integer result = roleDao.addRole(role);
        if(null == result){
            return new FailModel<String>("添加失败");
        }
        return new SuccessModel<String>("添加成功");
    }

    /**
     * 用户角色映射
     *
     * @param userRoleMapping
     * @return
     */
    @Override
    public BaseModel addUserRoleMapping(UserRoleMapping userRoleMapping) {
        Integer result = roleDao.addUserRoleMapping(userRoleMapping);
        if(null == result){
            return new FailModel<String>("添加失败");
        }
        return new SuccessModel<String>("添加成功");
    }

    /**
     * 角色权限映射
     *
     * @param rolePermissionMapping
     * @return
     */
    @Override
    public BaseModel addRolePermissionMapping(RolePermissionMapping rolePermissionMapping) {
        Integer result = roleDao.addRolePermissionMapping(rolePermissionMapping);
        if(null == result){
            return new FailModel<String>("添加失败");
        }
        return new SuccessModel<String>("添加成功");
    }
}
