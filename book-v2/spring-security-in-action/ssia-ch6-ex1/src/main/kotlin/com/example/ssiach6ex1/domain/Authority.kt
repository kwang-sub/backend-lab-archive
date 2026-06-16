package com.example.ssiach6ex1.domain

import jakarta.persistence.*

@Entity
class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
) {
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
}