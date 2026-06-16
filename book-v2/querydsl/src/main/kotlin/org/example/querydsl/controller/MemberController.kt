package org.example.querydsl.controller

import org.example.querydsl.dto.MemberSearchCondition
import org.example.querydsl.dto.MemberTeamDto
import org.example.querydsl.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberRepository: MemberRepository,
) {

    @GetMapping("/v1/members")
    fun searchMemberV1(condition: MemberSearchCondition): ResponseEntity<List<MemberTeamDto>> {
        val result = memberRepository.search(condition)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/v2/members")
    fun searchMemberV2(condition: MemberSearchCondition, pageable: Pageable): ResponseEntity<Page<MemberTeamDto>> {
        val result = memberRepository.searchPageComplex(condition, pageable)
        return ResponseEntity.ok(result)
    }

}