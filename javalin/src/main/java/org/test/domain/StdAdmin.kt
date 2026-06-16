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
 * 어드민계정
 */
@Entity
@Table(name = "tbl_std_admin")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class StdAdmin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    /**
     * 어드민로그인아이디
     */
    @Column(name = "admin_login_id", length = 100, nullable = false)
    var adminLoginId: String = "",

    /**
     * 어드민비밀번호
     */
    @Column(name = "admin_password", length = 255, nullable = false)
    var adminPassword: String = "",

    /**
     * 어드민명
     */
    @Column(name = "admin_user_name", length = 50, nullable = false)
    var adminUserName: String = "",

    /**
     * 어드민연락처
     */
    @Column(name = "admin_phone_num", length = 30, nullable = false)
    var adminPhoneNum: String = "",

    /**
     * 어드민이메일
     */
    @Column(name = "admin_email", length = 100, nullable = false)
    var adminEmail: String = "",

    /**
     * 활성화여부
     */
    @Column(name = "is_activated", nullable = false)
    var isActivated: Boolean = true,

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : AbstractAuditingEntity(), Serializable {

    @OneToMany(mappedBy = "admin")
    var stdAuthorities: MutableSet<StdAdminAuthority> = mutableSetOf()

    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties(
        value = [
            "admin",
        ],
        allowSetters = true
    )
    var stdAdminPasswords: MutableSet<StdAdminPassword>? = mutableSetOf()

    fun addStdAdminPassword(stdAdminPassword: StdAdminPassword): StdAdmin {
        this.stdAdminPasswords?.add(stdAdminPassword)
        stdAdminPassword.admin = this
        return this
    }

    fun removeStdAdminPassword(stdAdminPassword: StdAdminPassword): StdAdmin {
        this.stdAdminPasswords?.remove(stdAdminPassword)
        stdAdminPassword.admin = null
        return this
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StdAdmin) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString(): String {
        return "StdAdmin{" +
                "id=" + id +
                ", adminLoginId='" + adminLoginId + "'" +
                ", adminPassword='" + adminPassword + "'" +
                ", adminUserName='" + adminUserName + "'" +
                ", adminPhoneNum='" + adminPhoneNum + "'" +
                ", adminEmail='" + adminEmail + "'" +
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
    }
}
