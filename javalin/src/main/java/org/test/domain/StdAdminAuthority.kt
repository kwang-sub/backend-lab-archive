package org.test.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * 어드민권한관계
 */
@Entity
@Table(name = "tbl_std_admin_authority")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class StdAdminAuthority(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,
) : AbstractAuditingEntity(), Serializable {

    /**
     * 권한코드
     */
    @ManyToOne(optional = false)
    @JsonIgnoreProperties(
        value = [
            "stdAdminAuthorities",
            "stdMenuAuthorities",
        ],
        allowSetters = true
    )
    @JoinColumn(name = "stdAdminAuthorities")
    var authorityCode: StdAuthority? = null

    /**
     * 어드민ID
     */
    @ManyToOne(optional = false)
    @JsonIgnoreProperties(
        value = [
            "adminLoginId",
            "adminPassword",
            "adminUserName",
            "adminPhoneNum",
            "adminEmail",
            "adminSignImageId",
            "isActivated",
            "isDeleted",
            "createdBy",
            "createdDate",
            "lastModifiedBy",
            "lastModifiedDate",
        ],
        allowSetters = true
    )
    @JoinColumn(name = "stdAuthorities")
    var admin: StdAdmin? = null

    fun authorityCode(stdAuthority: StdAuthority?): StdAdminAuthority {
        this.authorityCode = stdAuthority
        return this
    }

    fun stdAdmin(stdAdmin: StdAdmin?): StdAdminAuthority {
        this.admin = stdAdmin
        return this
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StdAdminAuthority) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString(): String {
        return "StdAdminAuthority{" +
                "id=" + id +
                ", isDeleted='" + isDeleted + "'" +
                ", createdBy='" + createdBy + "'" +
                ", createdDate='" + createdDate + "'" +
                ", lastModifiedBy='" + lastModifiedBy + "'" +
                ", lastModifiedDate='" + lastModifiedDate + "'" +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
