package org.example.querydsl.controller

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Profile("local")
@Component
class InitMember {

    @Autowired
    private lateinit var initMemberService: InitMemberService

    @PostConstruct
    fun initTest() {
        initMemberService.init()
    }
    companion object {

        @Component
        class InitMemberService() {
            @Autowired
            private lateinit var em: EntityManager

            @Transactional
            fun init() {
                val teamA = Team(null, "teamA")
                val teamB = Team(null, "teamB")
                em.persist(teamA)
                em.persist(teamB)

                for (i in 1..100) {
                    val team = if (i % 2 == 0) teamA else teamB
                    em.persist(Member.create("member$i", i, team))
                }
            }
        }
    }

}