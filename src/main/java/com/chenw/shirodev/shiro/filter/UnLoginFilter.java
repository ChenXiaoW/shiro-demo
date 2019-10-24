package com.chenw.shirodev.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.chenw.shirodev.model.FailModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义用户未登陆时返回JSON
 *
 * @author chenw
 * @title: UnLoginFilter
 * @date 2019/10/22 9:05
 */
@Slf4j
public class UnLoginFilter extends FormAuthenticationFilter {
    /**
     * 用户未登陆时返回JSON
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            PrintWriter writer =resp.getWriter();
            writer.print(JSON.toJSONString(new FailModel<String>("请先登陆")));
            writer.flush();
            writer.close();
            return false;
        }
    }
}
