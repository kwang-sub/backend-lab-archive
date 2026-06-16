package com.example.querydslkotlin.repository

import com.example.querydslkotlin.domain.Cat
import org.springframework.data.jpa.repository.JpaRepository

interface CatJpaRepository : JpaRepository<Cat, Long> {
}