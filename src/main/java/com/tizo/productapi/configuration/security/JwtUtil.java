package com.tizo.productapi.configuration.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tizo.productapi.exception.JWTForbiddenException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public class JwtUtil {

    static final String SECRET_KEY = "T1z04pi";

    static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }

    static Authentication getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                String user = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token.replace("Bearer", ""))
                        .getBody()
                        .getSubject();

                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                        null;
            }
            return null;
        } catch (ExpiredJwtException e) {
            JWTForbiddenException errorResponse = new JWTForbiddenException(e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
            return null;
        }
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}

