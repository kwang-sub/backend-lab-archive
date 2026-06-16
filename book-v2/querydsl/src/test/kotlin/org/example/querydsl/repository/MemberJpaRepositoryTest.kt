package org.example.querydsl.repository

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.QMember.member
import org.example.querydsl.domain.Team
import org.example.querydsl.dto.MemberSearchCondition
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun basicTest() {
        val member = Member(null, "member1", 10)
        memberRepository.save(member)

        val findMember = member.id?.let { memberRepository.findById(it) }?.get()
        assertThat(findMember).isEqualTo(member)

        val result1 = memberRepository.findAll()
        assertThat(result1).containsExactly(member)


        val result2 = memberRepository.findByUsername("member1")
        assertThat(result2).containsExactly(member)
    }

    @Test
    fun basicQueryTest() {
        val member = Member(null, "member1", 10)
        memberRepository.save(member)

        val findMember = member.id?.let { memberRepository.findById(it) }?.get()
        assertThat(findMember).isEqualTo(member)

        val result1 = memberRepository.findAll_Querydsl()
        assertThat(result1).containsExactly(member)


        val result2 = memberRepository.findByUsername_Querydsl("member1")
        assertThat(result2).containsExactly(member)
    }

    @Test
    fun searchTest() {
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

        val con = MemberSearchCondition()
        con.ageGoe = 35
        con.ageLoe = 40
        con.teamName = "teamB"

        val result = memberRepository
            .searchByBuilder(con)
        assertThat(result).extracting("username").containsExactly("member4")
    }

    @Test
    fun searchTest2() {
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

        val con = MemberSearchCondition()
        con.teamName = "teamB"

        val result = memberRepository
            .searchByBuilder(con)
        assertThat(result).extracting("username").containsExactly("member3", "member4")
    }

    @Test
    fun searchTest3() {
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

        val con = MemberSearchCondition()
        con.teamName = "teamB"

        val result = memberRepository
            .search(con)
        assertThat(result).extracting("username").containsExactly("member3", "member4")
    }

    @Test
    fun searchPageImpl() {
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

        val con = MemberSearchCondition()
        val pageRequest = PageRequest.of(0, 3)

        val result = memberRepository
            .searchPageSimple(con, pageRequest)
        assertThat(result.size).isEqualTo(3)
        assertThat(result.content).extracting("username").containsExactly("member1", "member2", "member3")
    }

    @Test
    fun querydslPredicateExecutorTest() {
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
        val findAll = memberRepository.findAll(member.age.between(20, 40).and(member.username.eq("member2")))
        for (member5 in findAll) {
            println(member5)
        }
    }
}