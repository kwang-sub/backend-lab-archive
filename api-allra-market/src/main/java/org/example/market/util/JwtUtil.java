package org.example.market.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.market.common.ApplicationConstant;
import org.example.market.dto.response.AuthTokenResDto;
import org.example.market.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Clock clock;
    private final String secret;

    public JwtUtil(Clock clock, @Value("${app.jwt.secret}") String secret) {
        this.clock = clock;
        this.secret = secret;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public AuthTokenResDto generateAccessToken(final String username) {
        final String accessToken = this.generateToken(
                username,
                ApplicationConstant.Jwt.ACCESS_TOKEN_EXPIRATION_MS
        );
        return new AuthTokenResDto(accessToken);
    }

    private String generateToken(final String subject, final Long expiration) {
        Instant now = Instant.now(clock);
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expiration)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    public boolean isValidateToken(final String token, final String username) {
        final String extractedUsername = extractSubject(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractSubject(final String token) {
        if (isTokenExpired(token))
            throw new InvalidTokenException();

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(Date.from(Instant.now(clock)));
    }

    private Date extractExpiration(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException();
        }
    }
}
