package org.example.level2bookmanagementsystem.security.entity

import jakarta.persistence.*
import org.example.level2bookmanagementsystem.jpa.entity.BaseEntity
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.springframework.data.domain.Persistable
import java.time.Duration
import java.time.ZonedDateTime
import org.example.level2bookmanagementsystem.security.converter.Argon2HashConverter
import java.util.UUID

@Entity
class UserToken(
    @Id
    var userId: UUID? = null,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val tbUser: TbUser,

    @Convert(converter = Argon2HashConverter::class)
    var refreshToken: String,

    var issuedAt: ZonedDateTime,

    var expiresAt: ZonedDateTime,

    @Version
    var issueCount: Long = 0,

    var lastIp: String,
    // 유휴 만료 판단용
    var lastUsedAt: ZonedDateTime = issuedAt,
): BaseEntity(), Persistable<UUID> {
    fun update(refreshToken: String, remoteAddr: String): UserToken {
        return this.apply {
            this.refreshToken = refreshToken
            this.lastIp = remoteAddr
            val now = ZonedDateTime.now()
            this.issuedAt = now
            this.lastUsedAt = now
        }
    }

    fun isAbsoluteExpired(now: ZonedDateTime): Boolean {
        return expiresAt.isBefore(now)
    }
    fun isIdleExpired(now: ZonedDateTime, idleTtl: Duration): Boolean {
        return lastUsedAt.plus(idleTtl).isBefore(now)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserToken) return false

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

    override fun getId(): UUID?  = userId

    override fun isNew(): Boolean {
        return this.createdAt == null
    }

    override fun toString(): String {
        return "UserToken(lastUsedAt=$lastUsedAt, lastIp='$lastIp', issueCount=$issueCount, expiresAt=$expiresAt, issuedAt=$issuedAt, refreshToken='$refreshToken', userId=$userId)"
    }


    companion object {
        fun create(tbUser: TbUser, refreshToken: String, remoteAddr: String, expiresAt: ZonedDateTime): UserToken {
            val tbUserToken = UserToken(
                userId = tbUser.id ?: throw EntityNotFoundException(TbUser::class),
                tbUser = tbUser,
                refreshToken = refreshToken,
                issuedAt = ZonedDateTime.now(),
                expiresAt = expiresAt,
                lastIp = remoteAddr,
            )
            return tbUserToken
        }
    }
}