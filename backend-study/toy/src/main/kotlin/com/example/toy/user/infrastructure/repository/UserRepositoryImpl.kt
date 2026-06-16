package com.example.toy.user.infrastructure.repository

import com.example.toy.user.domain.UserDomain
import com.example.toy.user.infrastructure.repository.entity.UserEntity
import com.example.toy.user.service.port.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
): UserRepository{

    override fun save(userDomain: UserDomain): UserDomain {
        return userJpaRepository.save(UserEntity.fromModel(userDomain))
            .toModel()

    }
}