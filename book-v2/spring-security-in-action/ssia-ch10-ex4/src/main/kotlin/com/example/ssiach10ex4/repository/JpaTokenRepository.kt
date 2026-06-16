package com.example.ssiach10ex4.repository

import com.example.ssiach10ex4.domain.Token
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface JpaTokenRepository : JpaRepository<Token, Long> {

    fun findByIdentifier(identifier: String?): Optional<Token>
}