package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        List<User> users = new ArrayList<>();
        users.add(new User("aaa", "1111", "aaaaaa", "aaa@gmail.com"));
        users.add(new User("bbb", "1111", "bbbbbb", "bbb@gmail.com"));
        users.add(new User("ccc", "1111", "cccccc", "ccc@gmail.com"));
        users.add(new User("ddd", "1111", "dddddd", "ddd@gmail.com"));
        users.add(new User("eee", "1111", "eeeeee", "eee@gmail.com"));

        config.getServletContext().setAttribute("users", users);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("searchValue");
        ServletContext servletContext = req.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");

        if(searchValue != null) {
            if(searchValue.isBlank()) {
                req.setAttribute("users", users.stream()
                        .filter(user -> user.getUsername().contains(searchValue))
                        .collect(Collectors.toList()));
            }
        }
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .build();

        ServletContext servletContext = req.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
        if(users.stream().filter(u -> u.getUsername().equals(user.getUsername())).collect(Collectors.toList()).size() > 0) {
            resp.setContentType("text/html");
            resp.getWriter().println(
                    "<script>" +
                    "alert('이미 존재하는 사용자 이름입니다.');" +
                    "history.back();" +
                    "</script>"
            );
            return;
        }
        users.add(user);

        resp.sendRedirect("http://localhost:8080/servlet_study_war/user");
    }
}
