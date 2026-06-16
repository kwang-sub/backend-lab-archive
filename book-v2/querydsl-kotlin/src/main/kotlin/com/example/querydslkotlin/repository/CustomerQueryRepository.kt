package com.example.querydslkotlin.repository

import com.example.querydslkotlin.domain.Customer
import com.example.querydslkotlin.domain.QCustomer
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class CustomerQueryRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {

    private val qCustomer = QCustomer("qCustomer")

    fun findByFistName(firstName: String): Optional<Customer> {
        val result = jpaQueryFactory.selectFrom(qCustomer)
            .where(qCustomer.firstName.eq(firstName))
            .fetchOne()
        return Optional.ofNullable(result)
    }


    fun findOrderBy() {
        jpaQueryFactory.selectFrom(qCustomer)
            .orderBy(qCustomer.id.desc(), qCustomer.lastName.asc())
    }

    fun deleteAll() {
        jpaQueryFactory.delete(qCustomer).execute()
    }

    fun update(beforeFirstName: String, afterFirstName: String) {
        jpaQueryFactory.update(qCustomer)
            .where(qCustomer.firstName.eq(beforeFirstName))
            .set(qCustomer.firstName, afterFirstName)
            .execute()
    }
}