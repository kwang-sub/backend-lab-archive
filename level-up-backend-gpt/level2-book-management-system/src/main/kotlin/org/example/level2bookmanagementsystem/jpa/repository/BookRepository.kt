package org.example.level2bookmanagementsystem.jpa.repository

import jakarta.persistence.LockModeType
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface BookRepository : JpaRepository<Book, Long>, BookRepositoryCustom {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Book b where b.id = :id")
    fun findByIdAndPessimisticLock(id: Long): Optional<Book>
}