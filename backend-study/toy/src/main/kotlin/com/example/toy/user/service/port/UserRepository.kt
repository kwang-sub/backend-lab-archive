package com.example.toy.user.service.port

import com.example.toy.user.domain.UserDomain

interface UserRepository {
    fun save(userDomain: UserDomain): UserDomain
}