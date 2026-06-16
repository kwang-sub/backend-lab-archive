package com.example.ssiach6ex1.repository

import com.example.ssiach6ex1.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUsername(username: String): Optional<User>
}