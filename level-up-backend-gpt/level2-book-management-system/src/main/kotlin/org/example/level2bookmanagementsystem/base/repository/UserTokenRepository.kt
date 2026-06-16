package org.example.level2bookmanagementsystem.base.repository

import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.security.entity.UserToken
import org.springframework.data.jpa.repository.JpaRepository

interface UserTokenRepository: JpaRepository<UserToken, Long> {
    fun findByTbUser(tbUser: TbUser): UserToken?
    fun findByTbUserUserLoginId(userLoginId: String): UserToken?
    fun deleteByTbUser(tbUser: TbUser)
}