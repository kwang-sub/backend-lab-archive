package com.example.ssiach6ex1.controller

import com.example.ssiach6ex1.domain.*
import com.example.ssiach6ex1.repository.AuthorityRepository
import com.example.ssiach6ex1.repository.ProductRepository
import com.example.ssiach6ex1.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BasicController(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val authorityRepository: AuthorityRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {

    @GetMapping("/insert")
    fun insert() {
        val findById = userRepository.findById(1L)
        if (findById.isPresent) return

        val user = User(
            username = "john",
            password = bCryptPasswordEncoder.encode("1234"),
            algorithm = EncryptionAlgorithm.BCRYPT
        ).let { userRepository.save(it) }
        val user2 = User(
            username = "kwang",
            password = "1234",
            algorithm = EncryptionAlgorithm.SCRYPT
        ).let { userRepository.save(it) }

        val authority1 = Authority(
            name = "READ"
        ).apply {
            this.user = user
        }
        val authority2 = Authority(
            name = "WRITE"
        ).apply {
            this.user = user
        }
        authorityRepository.saveAll(mutableListOf(authority1, authority2))

        Product(
            name = "Chocolate",
            price = "10",
            currency = Currency.USD
        ).let { productRepository.save(it) }
    }
}