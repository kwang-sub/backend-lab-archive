package com.example.querydslkotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Customer private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
    var age: Int,
) {
    companion object {
        fun create(firstName: String, lastName: String, age: Int): Customer {
            return Customer(
                firstName = firstName,
                lastName = lastName,
                age = age
            )
        }
    }
}
