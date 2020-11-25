package com.poc.graphql.querydl.demo.interceptor;

import com.poc.graphql.querydl.demo.holder.AuthenticationHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationHolder holder;

    public TenantInterceptor(AuthenticationHolder holder) {
        this.holder = holder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String serverName = request.getServerName();
        String tenant = request.getHeader("tenant");
        holder.setTenant(tenant);
        request.setAttribute("tenant", tenant);

        return true;
    }
}
