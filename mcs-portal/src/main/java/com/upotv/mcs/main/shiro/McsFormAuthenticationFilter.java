package com.upotv.mcs.main.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by wow on 2017/7/20.
 */
public class McsFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String basePath = request.getContextPath();
        request.setAttribute("basePath", basePath);
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //判断session里是否有用户信息
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有，x-requested-with
                response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                return false;
            }
        }
        return super.onAccessDenied(request, response);
    }
}
