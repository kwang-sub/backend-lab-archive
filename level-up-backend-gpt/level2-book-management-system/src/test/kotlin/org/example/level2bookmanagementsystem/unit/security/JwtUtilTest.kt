package org.example.level2bookmanagementsystem.unit.security

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException

class JwtUtilTest : BehaviorSpec({

    val secret = "HOUcUnw574DticDirb6Y6y1MVkniCvtsfVHPq1fSjS8="
    val expiration = 1000L * 60 * 10
    val refreshExpiration = 1000L * 60 * 10
    val jwtUtil = JwtUtil(secret, expiration, refreshExpiration)

    Given("generateAccessToken") {
        When("userId 주어지면 토큰 생성") {
            Then("access token이 생성된다") {
                val userId = "user1"
                val response: AuthTokenResponse = jwtUtil.generateToken(userId)
                response.accessToken.isNotBlank() shouldBe true
            }
        }
    }

    Given("isValidateToken") {
        When("올바른 토큰과 사용자명일때") {
            Then("true를 반환한다") {
                val userId = "user1"
                val token = jwtUtil.generateToken(userId).accessToken
                jwtUtil.isValidateToken(token, userId, JwtUtil.Companion.TokenType.ACCESS) shouldBe true
            }
        }

        When("토큰과 다른 사용자명이면") {
            Then("false를 반환한다") {
                val token = jwtUtil.generateToken("user1").accessToken
                jwtUtil.isValidateToken(token, "user2", JwtUtil.Companion.TokenType.ACCESS) shouldBe false
            }
        }

        When("만료된 토큰이면 예외") {
            Then("CustomBadRequestException이 발생한다") {
                val userId = "user1"
                val expiredUtil = JwtUtil(secret, -1000L, -1000L)
                val expiredToken = expiredUtil.generateToken(userId).accessToken

                val ex = shouldThrow<CustomBadRequestException> {
                    expiredUtil.isValidateToken(expiredToken, userId, JwtUtil.Companion.TokenType.ACCESS)
                }
                ex.errorCode shouldBe ErrorCode.AUTH_TOKEN_ERROR

                // 두번째 검증 시나리오
                val ex2 = shouldThrow<CustomBadRequestException> {
                    expiredUtil.isValidateToken(expiredToken, "user1", JwtUtil.Companion.TokenType.ACCESS)
                }
                ex2.errorCode shouldBe ErrorCode.AUTH_TOKEN_ERROR
            }
        }

        When("유효 access 토큰 올바른 타입이면 통과") {
            Then("예외가 발생하지 않는다") {
                val accessToken = jwtUtil.generateToken("user1").accessToken
                shouldNotThrowAny {
                    jwtUtil.isValidateToken(accessToken, "user1", JwtUtil.Companion.TokenType.ACCESS)
                }
            }
        }

        When("refresh 토큰을 access 타입으로 검증시") {
            Then("CustomBadRequestException이 발생한다") {
                val refreshToken = jwtUtil.generateToken("user1").refreshToken
                val ex = shouldThrow<CustomBadRequestException> {
                    jwtUtil.isValidateToken(refreshToken, "user1", JwtUtil.Companion.TokenType.ACCESS)
                }
                ex.errorCode shouldBe ErrorCode.AUTH_TOKEN_ERROR
            }
        }
    }

    Given("extractSubject") {
        When("만료된 토큰이면 예외") {
            Then("CustomBadRequestException이 발생한다") {
                val userId = "user1"
                val expiredUtil = JwtUtil(secret, -1000L, -1000L)
                val expiredToken = expiredUtil.generateToken(userId).accessToken

                val ex = shouldThrow<CustomBadRequestException> {
                    expiredUtil.extractSubject(expiredToken)
                }
                ex.errorCode shouldBe ErrorCode.AUTH_TOKEN_ERROR

                val ex2 = shouldThrow<CustomBadRequestException> {
                    expiredUtil.extractSubject(expiredToken)
                }
                ex2.errorCode shouldBe ErrorCode.AUTH_TOKEN_ERROR
            }
        }
    }

})