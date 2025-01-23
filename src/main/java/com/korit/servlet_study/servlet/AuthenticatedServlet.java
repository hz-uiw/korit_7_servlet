package com.korit.servlet_study.servlet;

import com.korit.servlet_study.dto.ResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/authenticated")
// AccessToken으로 로그인 되었는지 확인하는 클래스
public class AuthenticatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bearerToekn = req.getHeader("Authorization");
        System.out.println(bearerToekn);

        ResponseDto responseDto = null;
        if(bearerToekn == null) {
            responseDto = ResponseDto.forbidden("검증할 수 없는 Access Token입니다.");
        }

        resp.setStatus(responseDto.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().println(responseDto);
    }
}
