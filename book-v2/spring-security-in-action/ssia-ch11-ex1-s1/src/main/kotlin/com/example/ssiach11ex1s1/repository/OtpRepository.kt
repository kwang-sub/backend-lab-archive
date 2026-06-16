package com.example.ssiach11ex1s1.repository

import com.example.ssiach11ex1s1.domain.Otp
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface OtpRepository : JpaRepository<Otp, String> {
    fun findByUsername(username: String): Optional<Otp>
}