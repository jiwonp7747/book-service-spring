package org.service.api.domain.token.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.service.api.domain.token.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs{

    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAcessToken(Map<String, Object> data) {
        LocalDateTime expiredLocalDateTime=LocalDateTime.now().plusHours(accessTokenPlusHour);
        Date expiredAt= Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant()); //LocalDateTime->Date
        SecretKey key= Keys.hmacShaKeyFor(secretKey.getBytes()); // 비밀 키를 바탕으로 HMAC SHA 키 생성 // 바이트 배열로 변환

        String jwtToken= Jwts.builder() // 토큰 생성
                .signWith(key, SignatureAlgorithm.HS256) // 서명 생성
                .setClaims(data) // 데이터를 클레임으로 설정, 클레임은 JWT 페이로드에 포함되는 정보, 사용자 식별 정보, 권한, 기타 메타데이터 포함
                .setExpiration(expiredAt) // JWT의 만료시간을 설정
                .compact();

        return TokenDto.builder() // token -> 데이터 전송 객체
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime= LocalDateTime.now().plusHours(refreshTokenPlusHour); // 토큰의 만료 시간
        var expiredAt= Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant()); // Date
        var key= Keys.hmacShaKeyFor(secretKey.getBytes()); // 키 만들기

        var jwtToken= Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        return Map.of();
    }
}
