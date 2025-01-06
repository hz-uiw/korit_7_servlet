package com.korit.servlet_study.servlet;

import com.korit.servlet_study.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

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
}
