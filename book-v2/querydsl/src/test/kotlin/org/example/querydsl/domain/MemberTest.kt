package org.example.querydsl.domain

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private lateinit var em: EntityManager


    @Test
    fun testEntity() {
        val teamA = Team.create("teamA")
        val teamB = Team.create("teamB")
        em.persist(teamA)
        em.persist(teamB)

        val member1 = Member.create("member1", 10, teamA)
        val member2 = Member.create("member2", 20, teamA)
        val member3 = Member.create("member3", 30, teamB)
        val member4 = Member.create("member4", 40, teamB)

        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)

        em.flush()
        em.clear()

        val resultList = em.createQuery("select m from Member m", Member::class.java).resultList
        resultList.forEach {
            println("member = $it")
            println("member.team = ${it.team}")
        }

    }
}