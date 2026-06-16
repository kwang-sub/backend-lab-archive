package com.example.querydslkotlin

import com.example.querydslkotlin.domain.Customer
import com.example.querydslkotlin.repository.CustomerJpaRepository
import com.example.querydslkotlin.repository.CustomerQueryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class CustomerTest {
    @Autowired
    private lateinit var customerJpaRepository: CustomerJpaRepository

    @Autowired
    private lateinit var customerQueryRepository: CustomerQueryRepository

    @BeforeEach
    fun init() {
        Customer.create("choi", "kwangsub", 10)
            .let(customerJpaRepository::save)
    }

    @Test
    fun findChoi() {
        val result = customerQueryRepository.findByFistName("choi")
        assertThat(result.isPresent).isTrue()
    }

    @Test
    fun delete() {
        customerQueryRepository.deleteAll()
        val result = customerQueryRepository.findByFistName("choi")
        assertThat(result.isPresent).isFalse()
    }

    @Test
    fun update() {
        customerQueryRepository.update("choi", "Choi")
        val result = customerQueryRepository.findByFistName("Choi")
        assertThat(result.isPresent).isTrue()
    }
}