package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.BoardDao;
import com.korit.servlet_study.dto.ResponseDto;
import com.korit.servlet_study.dto.InsertBoardDto;
import com.korit.servlet_study.entity.Board;

public class BoardService {

    private BoardDao boardDao;
    private static BoardService instance;

    private BoardService() {
        boardDao = boardDao.getInstance();
    }

    public static BoardService getInstance() {
        if (instance == null) {
            instance = new BoardService();
        }
        return instance;
    }

    public ResponseDto<?> insertBoard (InsertBoardDto dto) {
        Board board = dto.toBoard();
        Board insertedBoard = boardDao.save(board);
        if(insertedBoard == null) {
            return ResponseDto.fail("게시글 작성 실패!");
        }
        return ResponseDto.success(insertedBoard);
    }
}
