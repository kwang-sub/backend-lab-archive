package org.example.querydsl.domain

import jakarta.persistence.*

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String? = null,
    var age: Int? = null,
) {

    @ManyToOne(fetch = FetchType.LAZY)
    var team: Team? = null

    fun changeTeam(team: Team) {
        this.team = team
        team.members.add(this)
    }


    companion object {
        fun create(username: String?, age: Int?, team: Team?): Member {
            return Member()
                .apply {
                    this.username = username
                    this.age = age
                    this.team = team
                }
        }
    }

    override fun toString(): String {
        return "Member(id=$id, username=$username, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false
        if (username != other.username) return false
        if (age != other.age) return false
        if (team != other.team) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        result = 31 * result + (team?.hashCode() ?: 0)
        return result
    }
}