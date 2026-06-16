package org.example.level2bookmanagementsystem.security.service

import org.example.level2bookmanagementsystem.base.dto.response.UserResponse
import org.example.level2bookmanagementsystem.base.mapper.UserMapper
import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.base.repository.UserTokenRepository
import org.example.level2bookmanagementsystem.security.command.SignInCommand
import org.example.level2bookmanagementsystem.security.command.SignupCommand
import org.example.level2bookmanagementsystem.security.core.CustomUserDetails
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.security.util.CryptoUtil
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.security.util.JwtUtil.Companion.TokenType
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userMapper: UserMapper,
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userTokenService: UserTokenService,
    private val userTokenRepository: UserTokenRepository,
    private val cryptoUtil: CryptoUtil,
) {
    fun signup(command: SignupCommand): UserResponse {
        userRepository.findByUserLoginId(command.userLoginId)
            ?.let { throw CustomBadRequestException(ErrorCode.DUPLICATE_REQUEST) }

        return TbUser.create(
            userLoginId = command.userLoginId,
            name = command.name,
            password = passwordEncoder.encode(command.password),
            role = command.role,
        )
            .let(userRepository::save)
            .let(userMapper::toResponse)
    }

    fun signIn(command: SignInCommand, remoteAddr: String): AuthTokenResponse {
        val authToken = UsernamePasswordAuthenticationToken(command.userLoginId, command.password)
        val authentication = authenticationManager.authenticate(authToken)

        val principal = authentication.principal as? CustomUserDetails
            ?: throw CustomBadRequestException(ErrorCode.UNAUTHORIZED)

        val token = jwtUtil.generateToken(principal.username)
        userTokenService.save(principal.username, token.refreshToken, remoteAddr)
        return token
    }

    fun reissue(refreshToken: String, remoteAddr: String): AuthTokenResponse {
        val userId = requireValidRefreshSubject(refreshToken)

        val tbUser: TbUser = userRepository.findByUserLoginId(userId)
            ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_AUTH_USER)

        val userToken = userTokenRepository.findByTbUser(tbUser)
            ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_AUTH_REFRESH)

        // DB 저장 만료(absolute) 확인 (JWT 자체 만료와 별도 관리)
        if (userToken.expiresAt.isBefore(ZonedDateTime.now())) {
            userTokenService.deleteByUserLoginId(userId)
            throw CustomBadRequestException(ErrorCode.EXPIRED_AUTH_TOKEN)
        }

        // 해시된 refreshToken 매칭 (Argon2 단방향) - 직접 문자열 비교 금지
        val matched = cryptoUtil.verifyArgon2(userToken.refreshToken, refreshToken.toCharArray())
        if (!matched) throw CustomBadRequestException(ErrorCode.INVALID_AUTH_REFRESH)

        val newTokens = jwtUtil.generateToken(userId)
        userTokenService.save(userId, newTokens.refreshToken, remoteAddr)
        return newTokens
    }

    private fun requireValidRefreshSubject(refreshToken: String): String {
        return runCatching {
            val subject = jwtUtil.extractSubject(refreshToken)
            if (!jwtUtil.isValidateToken(refreshToken, subject, TokenType.REFRESH)) {
                throw IllegalArgumentException("Invalid refresh token")
            }
            subject
        }.getOrElse {
            throw CustomBadRequestException(ErrorCode.INVALID_AUTH_TOKEN)
        }
    }

    fun logout(name: String) {
        userTokenService.deleteByUserLoginId(name)
    }
}