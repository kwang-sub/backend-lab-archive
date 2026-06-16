package org.example.level2bookmanagementsystem.security.util

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration_time}") private val jwtExpiration: Long,
    @Value("\${jwt.refresh_expiration_time}") private val jwtRefreshExpiration: Long,
) {

    private val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

    /**
     * Access Token을 생성합니다.
     */
    fun generateToken(subject: String): AuthTokenResponse {
        val accessToken = generateToken(subject, jwtExpiration, TokenType.ACCESS)
        val refreshToken = generateToken(subject, jwtRefreshExpiration, TokenType.REFRESH)
        return AuthTokenResponse(accessToken, refreshToken)
    }

    private fun generateToken(subject: String, expiration: Long, tokenType: TokenType): String {
        val now = Instant.now()
        val expirationDate = now.plusMillis(expiration)

        return Jwts.builder()
            .subject(subject)
            .claim("type", tokenType.name)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expirationDate))
            .signWith(key)
            .compact()
    }

    /**
     * 토큰이 유효한지(사용자명 일치 및 만료 여부) 검증합니다.
     */
    fun isValidateToken(token: String, subject: String, tokenType: TokenType): Boolean {
        val extractedUsername = extractSubject(token)
        checkTokenType(token, tokenType)
        return extractedUsername == subject && !isTokenExpired(token)
    }

    /**
     * 토큰에서 subject(사용자명)를 추출합니다.
     */
    fun extractSubject(token: String): String {
        return parseToken(token).payload.subject
    }

    /**
     * 토큰이 만료되었는지 확인합니다.
     */
    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date.from(Instant.now()))
    }

    /**
     * 토큰에서 만료일(expiration)을 추출합니다.
     * 만료된 경우 커스텀 예외를 발생시킵니다.
     */
    private fun extractExpiration(token: String): Date {
        return try {
            parseToken(token).payload.expiration
        } catch (e: ExpiredJwtException) {
            throw CustomBadRequestException(ErrorCode.EXPIRED_TOKEN)
        }
    }

    /**
     * 토큰을 파싱하여 Claims 정보를 반환합니다.
     * 파싱 실패 시 커스텀 예외를 발생시킵니다.
     */
    private fun parseToken(token: String): Jws<Claims> {
        try {
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
        } catch (e: JwtException) {
            throw CustomBadRequestException(ErrorCode.AUTH_TOKEN_ERROR)
        }
    }

    private fun checkTokenType(token: String, tokenType: TokenType) {
        val type = parseToken(token).payload["type"]?.toString()
            ?: throw CustomBadRequestException(ErrorCode.AUTH_TOKEN_ERROR)

        if (type != tokenType.name) {
            throw CustomBadRequestException(ErrorCode.AUTH_TOKEN_ERROR)
        }
    }

    companion object {
        enum class TokenType {
            ACCESS, REFRESH
        }
    }
}