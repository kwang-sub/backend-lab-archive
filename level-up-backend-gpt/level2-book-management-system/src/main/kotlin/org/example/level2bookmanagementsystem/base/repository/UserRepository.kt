package org.example.level2bookmanagementsystem.base.repository

import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<TbUser, Long> {
    fun findByUserLoginId(userId: String): TbUser?
}