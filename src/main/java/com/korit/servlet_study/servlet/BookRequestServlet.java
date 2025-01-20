package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.Author;
import com.korit.servlet_study.entity.Book;
import com.korit.servlet_study.entity.BookCategory;
import com.korit.servlet_study.entity.Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = new Author(1, "테스트 저자");
        Publisher publisher = new Publisher(10, "테스트 출판사");
        BookCategory bookCategory = new BookCategory(100, "테스트 카테고리");

        Book book = Book.builder()
                .bookId(200)
                .bookName("테스트 도서명")
                .isbn("abcd1234")
                .bookImgUrl("http://test.com/1234")
                .publisherId(publisher.getPublisherId())
                .categoryId(bookCategory.getCategoryId())
                .author(author)
                .publisher(publisher)
                .bookCategory(bookCategory)
                .build();


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(book);
        System.out.println(json);

        response.setContentType("application/json");
        response.getWriter().println(json);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
