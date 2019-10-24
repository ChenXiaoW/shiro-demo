package com.chenw.shirodev.shiro.config;

import com.chenw.shirodev.shiro.filter.UnLoginFilter;
import com.chenw.shirodev.shiro.realm.UserRealm;
import com.chenw.shirodev.shiro.utils.ShiroParams;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author chenw
 * @title: ShiroConfig
 * @date 2019/10/21 10:24
 */
@Configuration
public class ShiroConfig {


    @Value("${filterInterface}")
    String filterInterface;

    //--------------------------------解决shiro注解不生效的问题------------------------begin---------------
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("getSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator app=new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }
    //--------------------------------解决shiro注解不生效的问题-------------------------end--------------

    /**
     * 过滤器
     *
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean(name = "filterFactoryBean")
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("getSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String,String> map = new LinkedHashMap<>();

        //排除不用认证的接口
        if(!StringUtils.isEmpty(filterInterface)){
            List<String> stringList = analysisList();
            for(String str : stringList){
                map.put(str,ShiroParams.ANON);
            }
        }
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        map.put("/**", ShiroParams.AUTHC);
        //设置过滤器链
        filterFactoryBean.setFilterChainDefinitionMap(map);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put(ShiroParams.AUTHC,new UnLoginFilter());
        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    /**
     * 安全管理器 - 关联自定义realm
     *
     * @param userRealm
     * @return
     */
    @Bean(name = "getSecurityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 获取自定义realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    /**
     * 解析数组
     *
     * @return
     */
    public  List<String> analysisList(){
        String[] split = filterInterface.split(",");
        return Arrays.asList(split);
    }
}
