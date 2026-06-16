package org.example.querydsl.dto

data class MemberTeamDto(
    var memberId : Long? = null,
    var username : String?= null,
    var age : Int?= null,
    var teamId : Long?= null,
    var teamName : String?= null,
)