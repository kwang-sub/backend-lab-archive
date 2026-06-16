package org.example.querydsl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceUnit
import org.assertj.core.api.Assertions.assertThat
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.QMember
import org.example.querydsl.domain.QMember.member
import org.example.querydsl.domain.QTeam.team
import org.example.querydsl.domain.Team
import org.example.querydsl.dto.MemberDto
import org.example.querydsl.dto.QMemberDto
import org.example.querydsl.dto.UserDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class QuerydslBasicTest {

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var jpaQueryFactory: JPAQueryFactory


    @BeforeEach
    fun init() {
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
    }

    @Test
    fun startJPQL() {
        val result = em.createQuery("select m from Member m where m.username = :username", Member::class.java)
            .setParameter("username", "member1")
            .singleResult

        assertThat(result.username).isEqualTo("member1")
    }

    @Test
    fun startQuerydsl() {
        val result = jpaQueryFactory.selectFrom(member)
            .where(member.username.eq("member1"))
            .fetchOne()

        assertThat(result).isNotNull()
        assertThat(result?.username).isEqualTo("member1")
    }

    @Test
    fun search() {
        val findMember = jpaQueryFactory
            .selectFrom(member)
            .where(member.username.eq("member1").and(member.age.eq(10)))
            .fetchOne()
        assertThat(findMember).isNotNull
        assertThat(findMember?.username).isEqualTo("member1")
    }

    @Test
    fun searchAndParam() {
        val findMember = jpaQueryFactory
            .selectFrom(member)
            .where(
                member.username.eq("member1"),
                (member.age.eq(10))
            )
            .fetchOne()
        assertThat(findMember).isNotNull
        assertThat(findMember?.username).isEqualTo("member1")
    }

    @Test
    fun resultFetch() {
//        val fetch = jpaQueryFactory
//            .selectFrom(member)
//            .fetch()
//
//
//        val fetchOne = jpaQueryFactory
//            .selectFrom(member)
//            .fetchOne()
//
//        val fetchFirst = jpaQueryFactory
//            .selectFrom(member)
//            .fetchFirst()

        val results = jpaQueryFactory
            .selectFrom(member)
            .fetchResults()
        results.total
        results.results

    }

    @Test
    fun sort() {
        em.persist(Member(null, null, 100))
        em.persist(Member(null, "member5", 100))
        em.persist(Member(null, "member6", 100))
        val result = jpaQueryFactory
            .selectFrom(member)
            .orderBy(member.age.desc(), member.username.asc().nullsLast())
            .fetch()

        val member5 = result[0]
        val member6 = result[1]
        val memberNull = result[2]

        assertThat(member5.username).isEqualTo("member5")
        assertThat(member6.username).isEqualTo("member6")
        assertThat(memberNull.username).isNull()
    }

    @Test
    fun paging1() {
        val fetch = jpaQueryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetch()
        assertThat(fetch.size).isEqualTo(2)
    }

    @Test
    fun paging2() {
        val fetch = jpaQueryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetchResults()
        assertThat(fetch.total).isEqualTo(4)
        assertThat(fetch.limit).isEqualTo(2)
        assertThat(fetch.offset).isEqualTo(1)
        assertThat(fetch.results.size).isEqualTo(2)
    }

    @Test
    fun aggregation() {
        val result = jpaQueryFactory.select(
            member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min(),

            )
            .from(member)
            .fetch()

        val tuple = result[0]
        assertThat(tuple.get(member.count())).isEqualTo(4)
        assertThat(tuple.get(member.age.sum())).isEqualTo(100)
        assertThat(tuple.get(member.age.avg())).isEqualTo(25.0)
        assertThat(tuple.get(member.age.max())).isEqualTo(40)
        assertThat(tuple.get(member.age.min())).isEqualTo(10)
    }

    @Test
    fun group() {
        val result = jpaQueryFactory.select(team, member.age.avg())
            .from(team)
            .join(team.members, member)
            .groupBy(team)
            .orderBy(team.name.asc())
            .fetch()

        val tuple = result[0]
        assertThat(tuple.get(team)?.name).isEqualTo("teamA")
        assertThat(tuple.get(member.age.avg())).isEqualTo(15.0)
    }

    @Test
    fun join() {
        val result = jpaQueryFactory
            .selectFrom(member)
            .join(member.team, team)
            .where(team.name.eq("teamA"))
            .fetch()
        assertThat(result).extracting("username")
            .containsExactly("member1", "member2")
    }

    @Test
    fun thetaJoin() {
        em.persist(Member(username = "teamA"))
        em.persist(Member(username = "teamB"))

        val result = jpaQueryFactory
            .select(member)
            .from(member, team)
            .where(member.username.eq(team.name))
            .fetch()
        assertThat(result)
            .extracting("username")
            .containsExactly("teamA", "teamB")
    }

    @Test
    fun join_on_filtering() {
        val result = jpaQueryFactory.select(member, team)
            .from(member)
            .join(member.team, team).on(team.name.eq("teamA"))
            .fetch()

        for (tuple in result) {
            println("tuple = $tuple")
        }
    }

    @Test
    fun join_on_no_relation() {
        em.persist(Member(username = "teamA"))
        em.persist(Member(username = "teamB"))

        val result = jpaQueryFactory
            .select(member)
            .from(member)
            .join(team).on(member.username.eq(team.name))
            .fetch()
        assertThat(result)
            .extracting("username")
            .containsExactly("teamA", "teamB")

        for (member1 in result) {
            println("확인 $member1")
        }
    }

    @PersistenceUnit
    private lateinit var emf: EntityManagerFactory

    @Test
    fun fetchJoinNo() {
        em.flush()
        em.clear()

        val findMember = jpaQueryFactory
            .selectFrom(member)
            .where(member.username.eq("member1"))
            .fetchOne()
        val loaded = emf.persistenceUnitUtil.isLoaded(findMember?.team)
        assertThat(loaded).isFalse()
    }

    @Test
    fun fetchJoinUse() {
        em.flush()
        em.clear()

        val findMember = jpaQueryFactory
            .selectFrom(member)
            .join(member.team, team).fetchJoin()
            .where(member.username.eq("member1"))
            .fetchOne()
        val loaded = emf.persistenceUnitUtil.isLoaded(findMember?.team)
        assertThat(loaded).isTrue()
    }

    @Test
    fun subQuery() {
        val memberSub = QMember("memberSub")
        val result = jpaQueryFactory.selectFrom(member)
            .where(
                member.age.eq(
                    JPAExpressions.select(memberSub.age.max())
                        .from(memberSub)
                )
            )
            .fetchOne()

        assertThat(result?.age).isEqualTo(40)
    }

    @Test
    fun subQuery2() {
        val memberSub = QMember("memberSub")
        val result = jpaQueryFactory.selectFrom(member)
            .where(
                member.age.goe(
                    JPAExpressions.select(memberSub.age.avg())
                        .from(memberSub)
                )
            )
            .fetch()

        assertThat(result).allMatch { (it.age ?: 0) > 25 }
    }

    @Test
    fun subQueryIn() {
        val memberSub = QMember("memberSub")
        val result = jpaQueryFactory.selectFrom(member)
            .where(
                member.age.`in`(
                    JPAExpressions.select(memberSub.age)
                        .from(memberSub)
                        .where(memberSub.age.gt(10))
                )
            )
            .fetch()

        assertThat(result).allMatch { (it.age ?: 0) > 10 }
    }

    @Test
    fun selectSubQuery() {
        val memberSub = QMember("memberSub")
        val result = jpaQueryFactory.select(member.username, JPAExpressions.select(memberSub.age.avg()).from(memberSub))
            .from(member)
            .fetch()

        assertThat(result).allMatch { it.get(1, Double::class.java) == 25.0 }
    }

    @Test
    fun basisCase() {
        val result = jpaQueryFactory.select(
            CaseBuilder()
                .`when`(member.age.eq(10)).then("열살")
                .`when`(member.age.eq(20)).then("스무살")
                .otherwise("기타")
        )
            .from(member)
            .fetch()

        for (s in result) {
            println("확인 $s")
        }
    }

    @Test
    fun complexCase() {
        val result = jpaQueryFactory.select(
            CaseBuilder()
                .`when`(member.age.between(0, 20)).then("0~20살")
                .`when`(member.age.between(21, 30)).then("21~30살")
                .otherwise("기타")
        )
            .from(member)
            .fetch()

        for (s in result) {
            println(result)
        }
    }

    @Test
    fun constant() {
        val result = jpaQueryFactory.select(member.username, Expressions.constant("A"))
            .from(member)
            .fetch()

        for (tuple in result) {
            println(tuple)
        }
    }

    @Test
    fun concat() {
        val result = jpaQueryFactory.select(member.username.concat("_").concat(member.age.stringValue()))
            .from(member)
            .where(member.username.eq("member1"))
            .fetch()

        for (s in result) {
            println(s)
        }
    }

    @Test
    fun simpleProjection() {
        val reulst = jpaQueryFactory
            .select(member.username)
            .from(member)
            .fetch()

        for (s in reulst) {
            println(s)
        }
    }

    @Test
    fun tupleProjection() {
        val result = jpaQueryFactory.select(member.username, member.age)
            .from(member)
            .fetch()

        for (tuple in result) {
            val name = tuple.get(member.username)
            val age = tuple.get(member.age)
            println("$name $age")
        }
    }

    @Test
    fun findDtoByJPQL() {
        val resultList = em.createQuery(
            "select new org.example.querydsl.dto.MemberDto(m.username, m.age) from Member m",
            MemberDto::class.java
        )
            .resultList

        for (memberDto in resultList) {
            println(memberDto)
        }
    }

    @Test
    fun findDtoBySetter() {
        val result = jpaQueryFactory.select(
            Projections.bean(
                MemberDto::class.java,
                member.username,
                member.age
            )
        )
            .from(member)
            .fetch()

        for (memberDto in result) {
            println(memberDto)
        }
    }

    @Test
    fun findDtoByFields() {
        val result = jpaQueryFactory.select(
            Projections.fields(
                MemberDto::class.java,
                member.username,
                member.age
            )
        )
            .from(member)
            .fetch()

        for (memberDto in result) {
            println(memberDto)
        }
    }

    @Test
    fun findDtoByConstructor() {
        val result = jpaQueryFactory.select(
            Projections.constructor(
                MemberDto::class.java,
                member.username,
                member.age
            )
        )
            .from(member)
            .fetch()

        for (memberDto in result) {
            println(memberDto)
        }
    }

    @Test
    fun findUserDtoByConstructor() {
        val memberSub = QMember("memberSub")
        val result = jpaQueryFactory.select(
            Projections.constructor(
                UserDto::class.java,
                member.username,
                ExpressionUtils.`as`(
                    JPAExpressions.select(memberSub.age.max())
                        .from(memberSub), " age"
                )
            )
        )
            .from(member)
            .fetch()

        for (memberDto in result) {
            println(memberDto)
        }
    }

    @Test
    fun findDtoByQueryProjection() {
        val result = jpaQueryFactory.select(QMemberDto(member.username, member.age))
            .from(member)
            .fetch()

        for (memberDto in result) {
            println(memberDto)
        }
    }

    @Test
    fun dynamicQuery_booleanBuilder() {
        val usernameParam = "member1"
        val ageParam = 10
        val result: List<Member> = searchMember1(usernameParam, ageParam)

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].username).isEqualTo(usernameParam)
        assertThat(result[0].age).isEqualTo(ageParam)

    }

    private fun searchMember1(usernameParam: String?, ageParam: Int?): List<Member> {
        val booleanBuilder = BooleanBuilder()
        usernameParam?.let { booleanBuilder.and(member.username.eq(usernameParam)) }
        ageParam?.let { booleanBuilder.and(member.age.eq(ageParam)) }
        return jpaQueryFactory.selectFrom(member)
            .where(booleanBuilder)
            .fetch()
    }

    @Test
    fun dynamicQuery_whereParam() {
        val usernameParam = "member1"
        val ageParam = 10
        val result: List<Member> = searchMember2(usernameParam, ageParam)

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].username).isEqualTo(usernameParam)
        assertThat(result[0].age).isEqualTo(ageParam)

    }

    private fun searchMember2(usernameParam: String?, ageParam: Int?): List<Member> {
        return jpaQueryFactory
            .selectFrom(member)
            .where(usernameEq(usernameParam), ageEq(ageParam))
            .fetch()
    }

    private fun ageEq(ageParam: Int?): Predicate? {
        return ageParam?.let { member.age.eq(it) }
    }

    private fun usernameEq(usernameParam: String?): Predicate? {
        return usernameParam?.let { member.username.eq(it) }
    }

    @Test
    fun bulkUpdate() {
        val count = jpaQueryFactory.update(member)
            .set(member.username, "비회원")
            .where(member.age.lt(28))
            .execute()
        assertThat(count).isEqualTo(2)
    }

    @Test
    fun bulkAdd() {
        jpaQueryFactory.update(member)
            .set(member.age, member.age.add(1))
            .execute()
    }

    @Test
    fun bulkDelete() {
        jpaQueryFactory.delete(member)
            .where(member.age.gt(18))
            .execute()
    }

    @Test
    fun sqlFunction() {
        val result = jpaQueryFactory.select(
            Expressions.stringTemplate(
                "function('replace', {0}, {1}, {2})",
                member.username, "member", "M"
            )
        )
            .from(member)
            .fetch()

        for (s in result) {
            println(s)
        }
    }

    @Test
    fun sqlFunction2() {
        val result = jpaQueryFactory.select(member.username)
            .from(member)
//            .where(member.username.eq(Expressions.stringTemplate("function('lower', {0})", member.username)))
            .where(member.username.eq(member.username.lower()))
            .fetch()

        for (s in result) {
            println(s)
        }
    }
}