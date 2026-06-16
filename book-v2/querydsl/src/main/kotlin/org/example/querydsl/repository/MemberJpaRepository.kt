package org.example.querydsl.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.QMember.member
import org.example.querydsl.domain.QTeam.team
import org.example.querydsl.dto.MemberSearchCondition
import org.example.querydsl.dto.MemberTeamDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class MemberJpaRepository {

    @Autowired
    private lateinit var em: EntityManager

    @Autowired
    private lateinit var queryFactory: JPAQueryFactory

    fun findAll_Querydsl(): List<Member> {
        return queryFactory.selectFrom(member)
            .fetch()
    }


    fun findByUsername_Querydsl(username: String): List<Member> {
        return queryFactory.selectFrom(member)
            .where(member.username.eq(username))
            .fetch()
    }

    fun searchByBuilder(condition: MemberSearchCondition): List<MemberTeamDto> {
        val booleanBuilder = BooleanBuilder()
        condition.username?.let { if (it.isNotBlank()) booleanBuilder.and(member.username.eq(it)) }
        condition.teamName?.let { if (it.isNotBlank()) booleanBuilder.and(team.name.eq(it)) }
        condition.ageGoe?.let { booleanBuilder.and(member.age.goe(it)) }
        condition.ageLoe?.let { booleanBuilder.and(member.age.loe(it)) }

        return queryFactory.select(
            Projections.fields(
                MemberTeamDto::class.java,
                member.id.`as`("memberId"),
                member.username.`as`("username"),
                member.age.`as`("age"),
                team.id.`as`("teamId"),
                team.name.`as`("teamName"),
            )
        )
            .from(member)
            .join(member.team, team)
            .where(booleanBuilder)
            .fetch()
    }

    fun search(condition: MemberSearchCondition): List<MemberTeamDto> {

        return queryFactory.select(
            Projections.fields(
                MemberTeamDto::class.java,
                member.id.`as`("memberId"),
                member.username.`as`("username"),
                member.age.`as`("age"),
                team.id.`as`("teamId"),
                team.name.`as`("teamName"),
            )
        )
            .from(member)
            .join(member.team, team)
            .where(
                usernameEq(condition.username),
                teamNameEq(condition.teamName),
                ageGoe(condition.ageGoe),
                ageLoe(condition.ageLoe),
            )
            .fetch()
    }

    private fun ageLoe(ageLoe: Int?): BooleanExpression? {
        return ageLoe?.let { member.age.loe(it) }
    }

    private fun ageGoe(agGoe: Int?): BooleanExpression? {
        return agGoe?.let { member.age.goe(it) }
    }

    private fun teamNameEq(teamName: String?): BooleanExpression? {
        return teamName?.let { team.name.eq(it) }
    }

    private fun usernameEq(username: String?): BooleanExpression? {
        return username?.let { member.username.eq(it) }
    }
}