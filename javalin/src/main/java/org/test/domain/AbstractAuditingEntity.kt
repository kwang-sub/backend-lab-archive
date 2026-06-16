package org.test.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.ColumnDefault
import org.test.domain.converter.ZonedDateTimeConverter
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

/**
 * Base abstract class for entities which will hold definitions for created, last modified by, created by,
 * last modified by attributes.
 */
@MappedSuperclass
@JsonIgnoreProperties(value = ["createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"], allowGetters = true)
abstract class AbstractAuditingEntity(
    @Column(name = "created_by", nullable = false, length = 100, updatable = false)
    open var createdBy: String = "",

    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @Convert(converter = ZonedDateTimeConverter::class)
    open var createdDate: ZonedDateTime = ZonedDateTime.now(),

    @Column(name = "last_modified_by", nullable = false, length = 100)
    open var lastModifiedBy: String = "",

    @Column(name = "last_modified_date", nullable = false, columnDefinition = "TIMESTAMP")
    @Convert(converter = ZonedDateTimeConverter::class)
    open var lastModifiedDate: ZonedDateTime = ZonedDateTime.now(),

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    open var isDeleted: Boolean? = false,
) : Serializable {

    @PrePersist
    fun prePersist() {
        createdDate = ZonedDateTime.now()
        lastModifiedDate = ZonedDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        lastModifiedDate = ZonedDateTime.now()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
