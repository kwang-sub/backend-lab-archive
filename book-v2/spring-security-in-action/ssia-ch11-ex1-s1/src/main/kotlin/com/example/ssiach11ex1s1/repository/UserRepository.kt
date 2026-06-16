package com.example.ssiach11ex1s1.repository

import com.example.ssiach11ex1s1.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, String> {

    fun findByUsername(username: String): Optional<User>
}