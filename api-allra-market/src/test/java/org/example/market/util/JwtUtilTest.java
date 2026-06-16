package org.example.market.util;

import org.assertj.core.api.SoftAssertions;
import org.example.market.dto.response.AuthTokenResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.market.common.ApplicationConstant.Jwt.ACCESS_TOKEN_EXPIRATION_MS;

class JwtUtilTest {

    private final String secret = "secret".repeat(20);

    @Test
    @DisplayName("토큰 생성 테스트")
    void 토큰_생성_테스트() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        JwtUtil jwtUtil = new JwtUtil(clock, secret);

        // when
        AuthTokenResDto tokens = jwtUtil.generateAccessToken("user");

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tokens).isNotNull();
        softAssertions.assertThat(tokens.accessToken()).isNotEmpty();

        softAssertions.assertAll();
    }

    @Test
    @DisplayName("사용자 이름 추출 테스트")
    void 사용자_이름_추출_테스트() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        JwtUtil jwtUtil = new JwtUtil(clock, secret);
        AuthTokenResDto tokens = jwtUtil.generateAccessToken("user");

        // when
        String extractedUsername = jwtUtil.extractSubject(tokens.accessToken());

        // then
        assertThat(extractedUsername).isEqualTo("user");
    }

    @Test
    @DisplayName("액세스 토큰 만료 테스트")
    void 액세스_토큰_만료_테스트() {
        // given
        Instant now = Instant.now();
        Clock clock = Clock.fixed(now, ZoneId.systemDefault());
        JwtUtil jwtUtil = new JwtUtil(clock, secret);
        AuthTokenResDto tokens = jwtUtil.generateAccessToken("user");


        Clock expiredClock = Clock.fixed(now.plusMillis(ACCESS_TOKEN_EXPIRATION_MS), ZoneId.systemDefault());
        JwtUtil expiredJwtUtil = new JwtUtil(expiredClock, secret);

        Clock nonExpiredClock = Clock.fixed(now.plusMillis(ACCESS_TOKEN_EXPIRATION_MS - 60 * 1000), ZoneId.systemDefault());
        JwtUtil nonExpiredJwtUtil = new JwtUtil(nonExpiredClock, secret);

        String token = tokens.accessToken();

        // when
        boolean jwtResult = jwtUtil.isTokenExpired(token);
        boolean expiredJwtResult = expiredJwtUtil.isTokenExpired(token);
        boolean nonExpiredJwtResult = nonExpiredJwtUtil.isTokenExpired(token);

        // then
        assertThat(jwtResult).isFalse();
        assertThat(expiredJwtResult).isTrue();
        assertThat(nonExpiredJwtResult).isFalse();
    }

    @Test
    @DisplayName("토큰 유효성 검사 테스트")
    void 토큰_유효성_검사_테스트() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        JwtUtil jwtUtil = new JwtUtil(clock, secret);
        AuthTokenResDto tokens = jwtUtil.generateAccessToken("user");

        // when
        boolean isValid = jwtUtil.isValidateToken(tokens.accessToken(), "user");
        boolean isInvalid = jwtUtil.isValidateToken(tokens.accessToken(), "anonymous");

        // then
        assertThat(isValid).isTrue();
        assertThat(isInvalid).isFalse();
    }
}