package com.example.querydslkotlin.config

import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig(
    private val entityManager: EntityManager,
) {

    @Bean
    fun jpaQueryFactory() = JPAQueryFactory(entityManager)

    @Bean
    fun jpaQuery() = JPAQuery<Any>(entityManager)
}