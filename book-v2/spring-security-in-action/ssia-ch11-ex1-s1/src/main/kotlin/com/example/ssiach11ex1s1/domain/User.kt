package com.example.ssiach11ex1s1.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class User(
    @Id
    var username: String,
    var password: String,
) {
}