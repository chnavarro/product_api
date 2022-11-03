package com.tizo.productapi.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tizo.productapi.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        InputStream body = req.getInputStream();
        UserDTO user = new ObjectMapper().readValue(body, UserDTO.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(),
                        user.getUserPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        JwtUtil.addAuthentication(res, auth.getName());
    }
}
