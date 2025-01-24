package com.korit.servlet_study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.security.jwt.JwtProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class AuthenticationFilter implements Filter {
    private JwtProvider jwtProvider;

    public AuthenticationFilter() {
        jwtProvider = JwtProvider.getInstance();
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        String method = request.getMethod();

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto<?> responseDto = null;


        String bearerToken = request.getHeader("Authorization");
        if(bearerToken == null) {
            responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token입니다.");
            response.setStatus(responseDto.getStatus());
            response.setContentType("application/json");
            response.getWriter().println(objectMapper.writeValueAsString(responseDto));
            return;
        }
        jwtProvider.parseToken(bearerToken);
    }
}
