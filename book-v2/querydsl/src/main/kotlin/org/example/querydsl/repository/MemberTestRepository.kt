package org.example.querydsl.repository

import com.querydsl.core.types.dsl.BooleanExpression
import org.example.querydsl.domain.Member
import org.example.querydsl.domain.QMember.member
import org.example.querydsl.domain.QTeam.team
import org.example.querydsl.dto.MemberSearchCondition
import org.example.querydsl.repository.support.Querydsl4RepositorySupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class MemberTestRepository : Querydsl4RepositorySupport(Member::class.java) {

    fun basicSelect(): List<Member> {
        return select(member)
            .from(member)
            .fetch()
    }

    fun searchPage(condition: MemberSearchCondition, pageable: Pageable): Page<Member> {
        val query = selectFrom(member)
            .leftJoin(member.team, team)
            .where(
                usernameEq(condition.username),
                teamNameEq(condition.teamName),
                ageGoe(condition.ageGoe),
                ageLoe(condition.ageLoe),
            )

        val content = querydsl.applyPagination(pageable, query)
            .fetch()
        return PageableExecutionUtils.getPage(content, pageable, query::fetchCount)
    }

    fun applyPagination(condition: MemberSearchCondition, pageable: Pageable): Page<Member> {
        return applyPagination(pageable) {
            it.selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                    usernameEq(condition.username),
                    teamNameEq(condition.teamName),
                    ageGoe(condition.ageGoe),
                    ageLoe(condition.ageLoe),
                )
        }
    }

    fun applyPagination2(condition: MemberSearchCondition, pageable: Pageable): Page<Member> {
        return applyPagination(pageable) {
            it.selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                    usernameEq(condition.username),
                    teamNameEq(condition.teamName),
                    ageGoe(condition.ageGoe),
                    ageLoe(condition.ageLoe),
                )
        }
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