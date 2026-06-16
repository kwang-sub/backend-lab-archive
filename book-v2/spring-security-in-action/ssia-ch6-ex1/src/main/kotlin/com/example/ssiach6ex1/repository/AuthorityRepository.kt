package com.example.ssiach6ex1.repository

import com.example.ssiach6ex1.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority, Long> {
}