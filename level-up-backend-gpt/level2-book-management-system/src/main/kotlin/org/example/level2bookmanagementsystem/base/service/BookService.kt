package org.example.level2bookmanagementsystem.base.service

import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.base.dto.condition.BookCondition
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.example.level2bookmanagementsystem.base.mapper.BookMapper
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.base.dto.response.BookResponse
import org.example.level2bookmanagementsystem.base.dto.response.BookSimpleResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper,
) {

    fun create(command: BookCommand): BookResponse {
        return command.let(bookMapper::toEntity)
            .let(bookRepository::save)
            .let(bookMapper::toResponse)
    }

    @Transactional(readOnly = true)
    fun getBook(id: Long): BookResponse {
        return bookRepository.findById(id)
            .orElseThrow { EntityNotFoundException(Book::class) }
            .let(bookMapper::toResponse)
    }

    @Transactional(readOnly = true)
    fun getAll(pageable: Pageable, condition: BookCondition): Page<BookSimpleResponse> {
        val page = bookRepository.search(condition, pageable)
        return page.map { bookMapper.toSimpleResponse(it) }
    }

    fun update(id: Long, command: BookCommand): BookResponse {

        return bookRepository.findById(id).orElseThrow { EntityNotFoundException(Book::class) }
            .update(command)
            .let(bookRepository::save)
            .let(bookMapper::toResponse)
    }

    fun delete(id: Long) {
        bookRepository.findById(id).orElseThrow{ EntityNotFoundException(Book::class) }
            .let(bookRepository::delete)
    }

    fun decreaseStock(bookId: Long) {
        bookRepository.findByIdAndPessimisticLock(bookId)
        .orElseThrow{ EntityNotFoundException(Book::class) }
            .also {
                it.decreaseStock()
                bookRepository.save(it)
            }
    }

    fun increaseStock(bookId: Long) {
        bookRepository.findByIdAndPessimisticLock(bookId)
            .orElseThrow { EntityNotFoundException(Book::class) }
            .also {
                it.increaseStock()
                bookRepository.save(it)
            }
    }
}