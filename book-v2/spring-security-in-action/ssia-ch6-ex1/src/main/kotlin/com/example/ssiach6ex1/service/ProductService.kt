package com.example.ssiach6ex1.service

import com.example.ssiach6ex1.domain.Product
import com.example.ssiach6ex1.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(
    private val productRepository: ProductRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}