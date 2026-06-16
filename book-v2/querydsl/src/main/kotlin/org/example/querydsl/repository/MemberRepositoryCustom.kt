package org.example.querydsl.repository

import org.example.querydsl.domain.Member
import org.example.querydsl.dto.MemberSearchCondition
import org.example.querydsl.dto.MemberTeamDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MemberRepositoryCustom {
    fun search(condition: MemberSearchCondition): List<MemberTeamDto>

    fun searchPageSimple(condition: MemberSearchCondition, pageable: Pageable): Page<MemberTeamDto>

    fun searchPageComplex(condition: MemberSearchCondition, pageable: Pageable): Page<MemberTeamDto>

    fun findAll_Querydsl(): List<Member>

    fun findByUsername_Querydsl(username: String): List<Member>

    fun searchByBuilder(condition: MemberSearchCondition): List<MemberTeamDto>
}