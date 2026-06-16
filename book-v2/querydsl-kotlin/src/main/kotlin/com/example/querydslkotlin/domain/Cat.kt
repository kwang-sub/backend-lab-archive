package com.example.querydslkotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Cat private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var age: Int,
){

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parentCat: Cat? = null

    @OneToMany(mappedBy = "parentCat")
    var childCat: MutableSet<Cat> = mutableSetOf()



    companion object {
        fun create(name: String,age:Int, parentCat: Cat?): Cat {
            val result = Cat(name = name, age = age)
                .apply { this.parentCat = parentCat }
            parentCat?.childCat?.add(result)
            return result
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Cat
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}