package com.example.ssiach6ex1.domain

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var algorithm: EncryptionAlgorithm,
) {
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var authorityList: MutableSet<Authority> = mutableSetOf()
}