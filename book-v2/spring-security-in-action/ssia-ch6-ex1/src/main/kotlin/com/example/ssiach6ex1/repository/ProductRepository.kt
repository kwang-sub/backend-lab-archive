package com.example.ssiach6ex1.repository

import com.example.ssiach6ex1.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}