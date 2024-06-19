package com.equipment.school_equipment.config;

import com.equipment.school_equipment.controller.session.SessionObj;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

public class FilterAdminPermission implements Filter {
    private static final Logger log = LoggerFactory.getLogger(FilterAdminPermission.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SessionObj attribute = (SessionObj) session.getAttribute(SessionObj.SESSION_NAME);

        log.info("attribute => {}", attribute);

        if(Objects.isNull(attribute) || attribute.getUserRole().getRole().equals("사용자")) response.sendRedirect("/error/permission");
        else filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
