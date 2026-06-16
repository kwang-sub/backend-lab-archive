package org.example.level2bookmanagementsystem.security.entity

import jakarta.persistence.*
import org.example.level2bookmanagementsystem.jpa.entity.BaseUuidEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.ZonedDateTime

@Entity
@Table(name = "TB_USER")
@SQLDelete(sql = "UPDATE TB_USER SET is_deleted = true WHERE id = ?")
@SQLRestriction(value = "is_deleted = false")
class TbUser(
    var userLoginId: String,
    var name: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: Role,
    @Embedded
    var loanInfo: LoanInfo,
    var refreshToken: String? = null,
) : BaseUuidEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TbUser) return false
        if (id == null || other.id == null) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    fun isLoanAvailable(now: ZonedDateTime = ZonedDateTime.now()): Boolean {
        return loanInfo.nextLoanAvailableDate.isBefore(now)
    }

    fun postponeLoanAvailableDates(day: Long) {
        if (day <= 0L) return
        if (this.isLoanAvailable()) this.loanInfo.nextLoanAvailableDate = ZonedDateTime.now().plusDays(day)
        else this.loanInfo.nextLoanAvailableDate = this.loanInfo.nextLoanAvailableDate.plusDays(day)
    }

    companion object {
        fun create(
            userLoginId: String,
            name: String,
            password: String,
            role: Role
        ): TbUser {
            return TbUser(
                userLoginId = userLoginId,
                name = name,
                password = password,
                role = role,
                loanInfo = LoanInfo(),
                refreshToken = null,
            )
        }
    }
}

enum class Role {
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN
}

