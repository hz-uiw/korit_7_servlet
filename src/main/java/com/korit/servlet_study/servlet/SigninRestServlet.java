package com.korit.servlet_study.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/signin")
public class SigninRestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder requestJsonData = new StringBuilder();
        try (BufferedReader bufferedReader = request.getReader();) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                requestJsonData.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
