package org.example.ladder.repository

interface Reader<T> {
    fun findAll(): List<T>
    fun findOne(): T? {
        return findAll().firstOrNull()
    }
}