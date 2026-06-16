package org.example.querydsl.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.QMember.member
import org.example.querydsl.domain.QTeam.team
import org.example.querydsl.dto.MemberSearchCondition
import org.example.querydsl.dto.MemberTeamDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.support.PageableExecutionUtils

class MemberRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : MemberRepositoryCustom, QuerydslRepositorySupport(Member::class.java) {

    override fun search(condition: MemberSearchCondition): List<MemberTeamDto> {
        /* from(member)
             .join(member.team, team)
             .where(
                 usernameEq(condition.username),
                 teamNameEq(condition.teamName),
                 ageGoe(condition.ageGoe),
                 ageLoe(condition.ageLoe),
             )
             .select(
                 Projections.fields(
                     MemberTeamDto::class.java,
                     member.id.`as`("memberId"),
                     member.username.`as`("username"),
                     member.age.`as`("age"),
                     team.id.`as`("teamId"),
                     team.name.`as`("teamName"),
                 )
             )
             .fetch()*/
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

    override fun searchPageSimple(condition: MemberSearchCondition, pageable: Pageable): PageImpl<MemberTeamDto> {
        val results = queryFactory.select(
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
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetchResults()
        return PageImpl(results.results, pageable, results.total)
    }

    fun searchPageSimple2(condition: MemberSearchCondition, pageable: Pageable): PageImpl<MemberTeamDto> {
        val jpaQuery = from(member)
            .join(member.team, team)
            .where(
                usernameEq(condition.username),
                teamNameEq(condition.teamName),
                ageGoe(condition.ageGoe),
                ageLoe(condition.ageLoe),
            )
            .select(
                Projections.fields(
                    MemberTeamDto::class.java,
                    member.id.`as`("memberId"),
                    member.username.`as`("username"),
                    member.age.`as`("age"),
                    team.id.`as`("teamId"),
                    team.name.`as`("teamName"),
                )
            )
        val query = querydsl?.applyPagination(pageable, jpaQuery)
        val results = query?.fetchResults()

        return PageImpl(results!!.results, pageable, results!!.total)
    }

    override fun searchPageComplex(condition: MemberSearchCondition, pageable: Pageable): Page<MemberTeamDto> {
        val results = queryFactory.select(
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
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val total = queryFactory
            .selectFrom(member)
            .join(member.team, team)
            .where(
                usernameEq(condition.username),
                teamNameEq(condition.teamName),
                ageGoe(condition.ageGoe),
                ageLoe(condition.ageLoe),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

//        return PageImpl(results, pageable, total)
        return PageableExecutionUtils.getPage(results, pageable, total::fetchCount)
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


    override fun findAll_Querydsl(): List<Member> {
        return queryFactory.selectFrom(member)
            .fetch()
    }


    override fun findByUsername_Querydsl(username: String): List<Member> {
        return queryFactory.selectFrom(member)
            .where(member.username.eq(username))
            .fetch()
    }

    override fun searchByBuilder(condition: MemberSearchCondition): List<MemberTeamDto> {
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
}