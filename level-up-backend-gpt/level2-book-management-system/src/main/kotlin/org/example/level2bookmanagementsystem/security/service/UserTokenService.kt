package org.example.level2bookmanagementsystem.security.service

import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.base.repository.UserTokenRepository
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.security.entity.UserToken
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.ZoneId

@Service
@Transactional
class UserTokenService(
    private val userTokenRepository: UserTokenRepository,
    private val userRepository: UserRepository,
    @Value("\${jwt.refresh_expiration_time}") private val jwtRefreshExpiration: Long,
) {
    fun save(userId: String, refreshToken: String, remoteAddr: String) {
        val tbUser = userRepository.findByUserLoginId(userId)
            ?: throw EntityNotFoundException(TbUser::class)

        val existingToken = userTokenRepository.findByTbUser(tbUser)
        val now = Instant.now()
        val newExpiresAt = now.plusMillis(jwtRefreshExpiration).atZone(ZoneId.systemDefault())

        if (existingToken != null) {
            existingToken.update(refreshToken, remoteAddr)
            existingToken.expiresAt = newExpiresAt
            userTokenRepository.save(existingToken)
        } else {
            val token = UserToken.create(tbUser, refreshToken, remoteAddr, newExpiresAt)
            userTokenRepository.save(token)
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteByUserLoginId(userId: String) {
        val userToken = userTokenRepository.findByTbUserUserLoginId(userId)
            ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_AUTH_REFRESH)
        userTokenRepository.deleteByTbUser(userToken.tbUser)
    }
}