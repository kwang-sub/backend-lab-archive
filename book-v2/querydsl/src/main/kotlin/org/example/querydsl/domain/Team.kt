package org.example.querydsl.domain

import jakarta.persistence.*

@Entity
@Table(name = "team")
open class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,

    ) {

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    var members: MutableSet<Member> = mutableSetOf()


    companion object {
        fun create(name: String?): Team {
            return Team()
                .apply { this.name = name }
        }
    }

    override fun toString(): String {
        return "Team(id=$id, name=$name)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (id != other.id) return false
        if (name != other.name) return false
        if (members != other.members) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + members.hashCode()
        return result
    }


}