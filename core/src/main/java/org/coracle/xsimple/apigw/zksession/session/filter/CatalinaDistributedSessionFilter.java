/**
 * Storevm.org Inc.
 * Copyright (c) 2004-2010 All Rights Reserved.
 */
package org.coracle.xsimple.apigw.zksession.session.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.coracle.xsimple.apigw.zksession.session.catalina.CatalinaDistributedSessionManager;

/**
 * 用于Tomcat容器的分布式Session的过滤器实现
 * @author hzxiongwenwu.tan
 * @version $Id: CatalinaDistributedSessionFilter.java, v 0.1 2010-12-31 下午03:07:55 hzxiongwenwu.tan Exp $
 */
public class CatalinaDistributedSessionFilter extends DistributedSessionFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        // 实例化Tomcat容器下的Session管理器
        this.sessionManager = new CatalinaDistributedSessionManager(
            filterConfig.getServletContext());
        try {
            sessionManager.start(); // 启动初始化
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("DistributedSessionFilter.init completed.");
            }
        } catch (Exception ex) {
            LOGGER.error("过滤器初始化失败，", ex);
        }
    }

    /** 
     * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                                                             throws IOException,
                                                                                             ServletException {
        //设置Response
        sessionManager.setHttpServletResponse((HttpServletResponse) response);
        super.doFilter(request, response, chain);
    }

}
