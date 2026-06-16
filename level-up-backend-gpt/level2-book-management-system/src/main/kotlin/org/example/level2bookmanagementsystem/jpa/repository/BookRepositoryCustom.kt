package org.example.level2bookmanagementsystem.jpa.repository

import org.example.level2bookmanagementsystem.base.dto.condition.BookCondition
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookRepositoryCustom {
    fun search(condition: BookCondition, pageable: Pageable): Page<Book>
}

