package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class BoardDao {
    private DBConnectionMgr dbConnectionMgr;
    private static BoardDao instance;

    private BoardDao() {}

    public static BoardDao getInstance() {
        if (instance == null) {
            instance = new BoardDao();
        }
        return instance;
    }

    public Optional<Board> save(Board board) {
        Board insertedBoard = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                    insert into board_tb
                    values(default, ?, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);    // prepareStatement => sql문 입력창 개념
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();    // ResultSet => 테이블 개념
            if (rs.next()) {
                insertedBoard = Board.builder()
                        .boardId(rs.getInt(1))
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps);
        }
        return Optional.ofNullable(board);
    }
}
