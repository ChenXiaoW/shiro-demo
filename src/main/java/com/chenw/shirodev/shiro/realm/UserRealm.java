package com.chenw.shirodev.shiro.realm;

import com.alibaba.fastjson.JSON;
import com.chenw.shirodev.dao.PermissionDao;
import com.chenw.shirodev.dao.RoleDao;
import com.chenw.shirodev.dao.UserDao;
import com.chenw.shirodev.model.Permission;
import com.chenw.shirodev.model.Role;
import com.chenw.shirodev.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义realm，shiro连接数据库桥梁
 *
 * @author chenw
 * @title: UserRealm
 * @date 2019/10/21 10:24
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;

    /**
     * 定义当前realm的名称
     *
     * @return
     */
    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 设置盐解析，这里的解析规则与加密规则相同
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密算法MD5
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数3
        hashedCredentialsMatcher.setHashIterations(3);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 权限认证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //给资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取当前登陆角色
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //获取登陆角色的接口权限
        List<String> permissions = getPermission(user);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        User user = new User(userToken.getUsername(),userToken.getPassword().toString());
        //从数据库中获取用户信息进行校验
        User checkUser = this.userDao.queryUserByName(user);
        if(null == checkUser){
            //校验失败，底层会抛出UnknownAccountException异常
            return null;
        }
        /**
         * 参数说明：
         * 1、登陆对象
         * 2、数据库中的用户密码
         * 3、随机盐
         * 4、Realm
         */
        return new SimpleAuthenticationInfo(user,checkUser.getPassword(), ByteSource.Util.bytes(checkUser.getUsername()),getName());
    }

    /**
     * 获取用户角色所拥有的权限
     *
     * @param user
     * @return
     */
    public List<String> getPermission(User user){
        List<String> permissions = new ArrayList<>();
        //获取用户信息
        User userInfo = userDao.queryUserByName(user);
        //获取用户角色
        List<Role> roles = roleDao.queryRoleByUserId(userInfo);
        for(Role role: roles){
            //获取用户权限
            List<Permission> permissionList = permissionDao.queryPermissionByRoleId(role);
            List<String> collect = permissionList.stream().map(Permission::getPermission).collect(Collectors.toList());
            permissions.addAll(collect);
        }
        return permissions;
    }
}
