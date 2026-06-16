package com.example.querydslkotlin.repository

import com.example.querydslkotlin.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerJpaRepository: JpaRepository<Customer, Long> {

}