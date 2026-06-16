package org.example.level2bookmanagementsystem.jpa.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseEntity {

    @CreatedDate
    val createdAt: ZonedDateTime? = null

    @LastModifiedDate
    var updatedAt: ZonedDateTime = ZonedDateTime.now()

    var isDeleted: Boolean = false
}