package com.example.toy.user.infrastructure.repository.entity

import com.example.toy.user.domain.UserDomain
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val age: Int,
) {
    fun toModel(): UserDomain = UserDomain(
        id = this.id,
        name = this.name,
        age = this.age,
    )

    companion object {
        fun fromModel(userDomain: UserDomain): UserEntity =
            UserEntity(name = userDomain.getName(), age = userDomain.getAge())
    }
}