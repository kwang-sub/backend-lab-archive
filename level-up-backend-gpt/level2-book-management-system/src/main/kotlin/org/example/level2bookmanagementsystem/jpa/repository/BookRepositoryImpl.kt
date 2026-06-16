package org.example.level2bookmanagementsystem.jpa.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.level2bookmanagementsystem.base.dto.condition.BookCondition
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.jpa.entity.QBook.*
import org.example.level2bookmanagementsystem.jpa.util.QueryDslUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils


class BookRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : BookRepositoryCustom {


    // TODO 블로그 글 작성 페이지 네이션

    override fun search(condition: BookCondition, pageable: Pageable): Page<Book> {
        val pathMap = mapOf(
            "id" to book.id,
            "title" to book.title,
            "author" to book.author,
            "publishedYear" to book.publishedDate.year()
        )

        val content = queryFactory.select(book)
            .from(book)
            .where(getBookWhereBuilder(condition))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*QueryDslUtil.buildOrderSpecifiers(pageable, pathMap))
            .fetch()

        val total = queryFactory.select(book.count())
            .from(book)
            .where(getBookWhereBuilder(condition))
            .fetchFirst()
            ?: 0L

        return PageableExecutionUtils.getPage(content, pageable) { total }
    }

    private fun getBookWhereBuilder(
        condition: BookCondition,
    ): BooleanBuilder {
        val builder = BooleanBuilder()
        condition.title?.let {
            builder.and(book.title.containsIgnoreCase(it))
        }
        condition.author?.let {
            builder.and(book.author.containsIgnoreCase(it))
        }
        condition.publishedYear?.let {
            builder.and(book.publishedDate.year().eq(it))
        }
        return builder
    }
}
