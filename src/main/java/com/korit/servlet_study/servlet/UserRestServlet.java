package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserRestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = User.builder()
                .username("test")
                .password("1234")
                .name("테스트")
                .email("test@gmail.com")
                .build();
        // json 객체로 변환
        String jsonUser = objectMapper.writeValueAsString(user);
        System.out.println(jsonUser);

        // Origin => 대상의 주소
        // 서버의 요청을 보내는 대상의 주소를 허용
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 서버의 요청을 보내는 대상의 메서드에 대한 동의 (post, get, options만 허용)
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        // 서버의 요청을 보내는 대상의 헤더 포함 허용
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // 서버의 요청을 보내는 대상의 서명, 개인의 pc에 인증(쿠키 등) 데이터를 허용 (false면 비허용)
        response.setHeader("Access-control-Allow-Credentials", "true");

        response.setContentType("application/json");
        response.getWriter().println(jsonUser);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
