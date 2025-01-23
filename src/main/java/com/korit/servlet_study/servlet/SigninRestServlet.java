package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.SigninDto;
import com.korit.servlet_study.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signin")
public class SigninRestServlet extends HttpServlet {

    private AuthService authService;

    public SigninRestServlet() {
        authService = AuthService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestJsonData = new StringBuilder();
        // 버퍼에 Json 문자열을 넣어 라인 단위로 끊어서 가지고 온다
        // BufferedReader 를 사용한 후 반드시 해당 리소스를 정리하기 위해 try 사용
        try(BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {    // 읽을 수 있는 다음라인이 있을경우
                requestJsonData.append(line);   // stringBuilder: Json 문자열을 합쳐서 반환
            }
        }

        // key, value 들을 매칭해서 class 객체로 생성
        ObjectMapper mapper = new ObjectMapper();
        SigninDto signinDto = mapper.readValue(requestJsonData.toString(), SigninDto.class);

        //  Json 을 java 객체로 변환
        ResponseDto<?> responseDto = authService.signin(signinDto);

        // 클라이언트에 Json 데이터로 반환하여 응답을 보냄
        response.setContentType("application/json");
        response.setStatus(responseDto.getStatus());
        response.getWriter().println(mapper.writeValueAsString(responseDto));

    }
}