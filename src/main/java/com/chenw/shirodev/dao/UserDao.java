package com.chenw.shirodev.dao;

import com.chenw.shirodev.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 *
 * @author chenw
 * @title: UserDao
 * @date 2019/10/21 13:29
 */
@Repository
public interface UserDao {
    /**
     * 根据用户名称查询用户
     *
     * @param user
     * @return
     */
    User queryUserByName(User user);
}
