package com.nocaffeine.ssgclone.common.security;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.JWT_VALID_FAILED;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.TOKEN_NULL;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;


    @Value("${JWT.SECRET_KEY}")
    private String secretKey;

    public String getUuid(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(
            Map<String, Objects> extractClaims,
            UserDetails userDetails
    ) {

        log.info("generateToken {} ", userDetails);
        return Jwts.builder()
                .setClaims(extractClaims) // 정보저장
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME", Long.class)))// 토큰 유효시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 암호화 알고리즘
                .compact();
    }

    public Long getExpiration(String accessToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(accessToken).getBody();

        // 토큰의 만료 시간
        Date expiration = claims.getExpiration();
        // 현재 시간
        Date now = new Date();
        // 남은 유효 시간 계산 (밀리초 단위)
        long remainingTimeMillis = expiration.getTime() - now.getTime();
        // 음수 값이 나올 경우 0으로 설정하여 음수 값 방지
        return Math.max(remainingTimeMillis, 0);
    }




    public String validateAndGetUserUuid(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            throw new BaseException(JWT_VALID_FAILED);
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String uuid = getUuid(token);
        return (uuid.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }





    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            // "Bearer " 부분을 제외한 토큰만 반환
            return headerValue.substring(7).trim();
        }
        throw new BaseException(TOKEN_NULL);
//        return null;
    }
}
