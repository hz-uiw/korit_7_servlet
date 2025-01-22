package com.korit.servlet_study.service;

import com.korit.servlet_study.dao.AuthDao;
import com.korit.servlet_study.dao.ResponseDto;
import com.korit.servlet_study.dto.SignupDto;
import com.korit.servlet_study.entity.User;

public class AuthService {
    private AuthDao authDao;
    private static AuthService instance;

    private AuthService() {
        authDao = AuthDao.getInstance();
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public ResponseDto<?> signup(SignupDto signupDto) {
        User user = signupDto.toUser();
        User insertedUser = authDao.signup(user);
        if (insertedUser == null) {
            return ResponseDto.fail("로그인 실패!");
        }
        return ResponseDto.success(insertedUser);
    }
}
