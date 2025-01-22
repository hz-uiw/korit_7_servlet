package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signup")
public class SignupRestServlet extends HttpServlet {
    public AuthService authService;

    public SignupRestServlet() { authService = AuthService.getInstance(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        SignupDto signupDto = objectMapper.readValue(request.getReader(), SignupDto.class);

        ResponseDto<?> responseDto = authService.signup(signupDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().print(responseJson);
    }
}
