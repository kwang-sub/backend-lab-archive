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
 * 어드민계정비밀번호변경이력
 */
@Entity
@Table(name = "tbl_std_admin_password")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class StdAdminPassword(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    /**
     * 이전비밀번호
     */
    @Column(name = "before_password", length = 255, nullable = false)
    var beforePassword: String? = null,

    /**
     * 변경비밀번호
     */
    @Column(name = "after_password", length = 255, nullable = false)
    var afterPassword: String? = null,

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : AbstractAuditingEntity(), Serializable {

    /**
     * 관리자인덱스ID
     */
    @ManyToOne(optional = false)
    @JsonIgnoreProperties(
        value = [
            "hopHospitals",
            "hopHospitalDepartmentAdmins",
            "stdAdminPasswords",
            "usrPatients",
        ],
        allowSetters = true
    )
    @JoinColumn(name = "stdAdminPasswords")
    var admin: StdAdmin? = null

    fun admin(stdAdmin: StdAdmin?): StdAdminPassword {
        this.admin = stdAdmin
        return this
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StdAdminPassword) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString(): String {
        return "StdAdminPassword{" +
                "id=" + id +
                ", beforePassword='" + beforePassword + "'" +
                ", afterPassword='" + afterPassword + "'" +
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
