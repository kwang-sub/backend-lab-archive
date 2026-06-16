package org.example.level2bookmanagementsystem.jpa.entity

import com.github.f4b6a3.uuid.UuidCreator
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.UUID

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseUuidEntity(
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    var id: UUID? = null,
) : BaseEntity() {

    @PrePersist
    fun prePersist() {
        if (id == null) {
            // v7 (time-ordered, DB 인덱스 친화적인 UUID)
            id = UuidCreator.getTimeOrderedEpoch() // v7 스타일
        }
    }
}