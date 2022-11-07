package com.example.productapi.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication != null) {
            logger.info("Authentication " + authentication.getName() + ", Authenticated: " + authentication.isAuthenticated());
        }
        filterChain.doFilter(request, response);
    }
}
