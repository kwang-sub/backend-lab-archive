package org.test.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * 권한
 */
@Entity
@Table(name = "tbl_std_authority")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class StdAuthority(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    /**
     * 권한코드
     */
    @Column(name = "authority_code", length = 25, nullable = false)
    var authorityCode: String? = null,

    /**
     * 권한명
     */
    @Column(name = "authority_name", length = 100, nullable = false)
    var authorityName: String? = null,

    /**
     * 권한설명
     */
    @Column(name = "authority_desc", length = 255)
    var authorityDesc: String? = null,

    /**
     * 활성화여부
     */
    @Column(name = "is_activated", nullable = false)
    var isActivated: Boolean? = null,

    ) : AbstractAuditingEntity(), Serializable {

    @OneToMany(mappedBy = "authorityCode")
    @JsonIgnoreProperties(
        value = [
            "authorityCode",
        ],
        allowSetters = true
    )
    var stdAdminAuthorities: MutableSet<StdAdminAuthority>? = mutableSetOf()

    fun addStdAdminAuthority(stdAdminAuthority: StdAdminAuthority): StdAuthority {
        this.stdAdminAuthorities?.add(stdAdminAuthority)
        stdAdminAuthority.authorityCode = this
        return this
    }

    fun removeStdAdminAuthority(stdAdminAuthority: StdAdminAuthority): StdAuthority {
        this.stdAdminAuthorities?.remove(stdAdminAuthority)
        stdAdminAuthority.authorityCode = null
        return this
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StdAuthority) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString(): String {
        return "StdAuthority{" +
                "id=" + id +
                ", authorityCode='" + authorityCode + "'" +
                ", authorityName='" + authorityName + "'" +
                ", authorityDesc='" + authorityDesc + "'" +
                ", isActivated='" + isActivated + "'" +
                ", isDeleted='" + isDeleted + "'" +
                ", createdBy='" + createdBy + "'" +
                ", createdDate='" + createdDate + "'" +
                ", lastModifiedBy='" + lastModifiedBy + "'" +
                ", lastModifiedDate='" + lastModifiedDate + "'" +
                "}"
    }

    companion object {
        private const val serialVersionUID = 1L

        const val CODE_AUTHORITY_ADMIN = "ROLE_ADMIN"
        const val CODE_AUTHORITY_USER = "ROLE_USER"
        const val CODE_AUTHORITY_ANONYMOUS = "ROLE_ANONYMOUS"
    }
}
