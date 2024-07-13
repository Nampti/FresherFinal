package com.lthdv.authservice.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lthdv.authservice.service.JwtService;
import com.lthdv.authservice.vo.response.MessEntity;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(req, res);
//            setErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header", res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req, res);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        try {
            chain.doFilter(req, res);
        }catch (Exception e) {
            accessDeniedHandler();
        }

    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"statusCode\": 403, \"message\": \"Access Denied: You do not have the necessary permissions to access this resource.\"}");
        };
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        Claims claims = jwtService.validateToken(token);

        if (claims == null) {
            setErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token", response);

        }
        if (claims != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (claims.get("role") != null) {
                authorities.add(new SimpleGrantedAuthority((String) claims.get("role")));
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
            authentication.setDetails(claims);
            return authentication;
        }

        return null;
    }

    private void setErrorResponse(int statusCode, String message, HttpServletResponse response) throws IOException {
        MessEntity messEntity = new MessEntity(statusCode, message);
        response.setStatus(statusCode);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(messEntity));
    }
}