package com.example.toy.user.infrastructure.repository

import com.example.toy.user.infrastructure.repository.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
}