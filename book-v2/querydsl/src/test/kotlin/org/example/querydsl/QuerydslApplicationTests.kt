package org.example.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.example.querydsl.domain.Hello
import org.example.querydsl.domain.QHello
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

    @Autowired
    private lateinit var em: EntityManager


    @Test
    fun contextLoads() {
        val hello = Hello()
        em.persist(hello)
        val query = JPAQueryFactory(em)
        val qHello = QHello("qHello")
        val result = query.selectFrom(qHello)
            .fetchOne()

        assertThat(result).isEqualTo(hello)
        assertThat(hello.id).isNotNull()
    }

}
