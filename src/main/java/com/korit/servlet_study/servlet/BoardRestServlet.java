package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.dao.ResponseDto;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.entity.Board;
import com.korit.servlet_study.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/board")
public class BoardRestServlet extends HttpServlet {

    public BoardService boardService;

    public BoardRestServlet() { boardService = BoardService.getInstance(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder(); // 싱글 스레드 용
//        StringBuffer stringBuffer = new StringBuffer(); // 멀티 스레드 용
        // 받아온 axios.post()가 json형태여서 getReader() 메서드 사용
        try (BufferedReader bufferedReader = request.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        InsertBoardDto insertBoardDto = objectMapper.readValue(stringBuilder.toString(), InsertBoardDto.class);

        ResponseDto<?> responseDto = boardService.insertBoard(insertBoardDto);
        String responseJson = objectMapper.writeValueAsString(responseDto);

        response.setStatus(responseDto.getStatus());
        response.setContentType("application/json");
        response.getWriter().println(responseJson);

    }
}
