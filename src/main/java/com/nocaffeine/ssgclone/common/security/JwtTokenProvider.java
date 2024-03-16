package com.nocaffeine.ssgclone.common.security;

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
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

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


    public String validateAndGetUserUuid(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (NullPointerException e) {
            log.info("토큰에 담긴 유저 정보가 없습니다");
            return null;
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
        return null;
    }
}
