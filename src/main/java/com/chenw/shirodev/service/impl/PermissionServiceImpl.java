package com.chenw.shirodev.service.impl;

import com.chenw.shirodev.dao.PermissionDao;
import com.chenw.shirodev.model.BaseModel;
import com.chenw.shirodev.model.FailModel;
import com.chenw.shirodev.model.Permission;
import com.chenw.shirodev.model.SuccessModel;
import com.chenw.shirodev.service.PermissionService;
import com.chenw.shirodev.utils.UtilFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限业务接口实现
 *
 * @author chenw
 * @title: PermissionServiceImpl
 * @date 2019/10/21 14:14
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    @Override
    public BaseModel addPermission(Permission permission) {
        permission.setPermissionId(UtilFactory.getUUID());
        Integer result = permissionDao.addPermission(permission);
        if(null == result){
            return new FailModel<String>("添加失败");
        }
        return new SuccessModel<String>("添加成功");
    }
}
