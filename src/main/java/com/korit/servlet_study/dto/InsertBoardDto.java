package com.korit.servlet_study.dto;

import com.korit.servlet_study.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertBoardDto {
    // DTO는 react의 useState와 동일하게 설정
    private String title;
    private String content;

    public Board toBoard() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
