package com.korit.servlet_study.security.jwt;

import com.korit.servlet_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private Key key;
    private static JwtProvider instance;

    private JwtProvider() {
        final String SECRET = "faf0562aab89326427884967d1fae07e7a41f9e0dcd62c7d3e3282871efabf335ebf75c164926a3da69059d71c578cdd32bfc0b836b0a274d4e870caa777c0d1a1f4420ee115b7668ffa802237b151dc5dc2e8ed75bd45f6794212302b0ede3956687654d79907e856824cc2ee632a886f79079bd7158b7eb99f66a8d112e8843ae0c7bf31a63f12555e916a91f7a337d17f8cd7809ddcafb45af2897a0448d348db8ce81d47298ee4c31d556d3194c2590de024c2892fd539a3eb8f2ad0453fad70b5d9fe5c9a12405fd7f2d47817b387b7ec41b8f981f5fe06b97b6cd7d34d5804743d35dbe58ef77181cf97ca1860685f1b831edbcdb9f3fa386eceff59ed";
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

    }

    public static JwtProvider getInstance() {
        if (instance == null) {
            instance = new JwtProvider();
        }
        return instance;
    }

    private Date getExpireDate() {
        // 1초 1분 1시간 1일 1년
        return new Date(new Date().getTime() + (1000l* 60 * 60 * 24 *365));
    }


    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .setExpiration(getExpireDate())
                // 항상 마지막에 올 코드, 256으로 키를 만들었기 때문에 HS256
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // parsing후 검증
    public Claims parseToken(String token) {
        Claims claims = null;
        try {       // 토큰의 변조나 기간이 지났거나 유효하지 않은 토큰인 경우, 예외를 피하기 위해
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(removeBearer(token))
                    .getBody(); // Claims로 반환
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private String removeBearer(String bearerToken) {
        String accessToken = null;
        final String BEAR_KEYWORD = "Bearer ";
        if (bearerToken.startsWith(BEAR_KEYWORD)) {
            accessToken = bearerToken.substring(BEAR_KEYWORD.length());
        }
        return accessToken;
    }

}
