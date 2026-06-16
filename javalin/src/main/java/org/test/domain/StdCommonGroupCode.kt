package org.test.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * 공통그룹코드
 */
@Entity
@Table(name = "tbl_std_common_group_code")
@SuppressWarnings("common-java:DuplicatedBlocks")
data class StdCommonGroupCode(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    /**
     * 공통그룹코드
     */
    @Column(name = "common_group_code", length = 25, nullable = false)
    var commonGroupCode: String? = null,

    /**
     * 공통그룹코드명
     */
    @Column(name = "common_group_code_name", length = 50, nullable = false)
    var commonGroupCodeName: String? = null,

    /**
     * 공통그룹코드설명
     */
    @Column(name = "common_group_code_desc", length = 255)
    var commonGroupCodeDesc: String? = null,

    /**
     * 정렬순서
     */
    @Column(name = "sort_num", nullable = false)
    var sortNum: Int? = null,

    /**
     * 활성화여부
     */
    @Column(name = "is_activated", nullable = false)
    var isActivated: Boolean? = null,

    // jhipster-needle-entity-add-field - JHipster will add fields here
) : AbstractAuditingEntity(), Serializable {

    @OneToMany(mappedBy = "commonGroupCode")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = [
            "commonGroupCode",
        ],
        allowSetters = true
    )
    var stdCommonCodes: MutableSet<StdCommonCode>? = mutableSetOf()

    fun addStdCommonCode(stdCommonCode: StdCommonCode): StdCommonGroupCode {
        this.stdCommonCodes?.add(stdCommonCode)
        stdCommonCode.commonGroupCode = this
        return this
    }

    fun removeStdCommonCode(stdCommonCode: StdCommonCode): StdCommonGroupCode {
        this.stdCommonCodes?.remove(stdCommonCode)
        stdCommonCode.commonGroupCode = null
        return this
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StdCommonGroupCode) return false
        return id != null && other.id != null && id == other.id
    }

    override fun toString(): String {
        return "StdCommonGroupCode{" +
                "id=" + id +
                ", commonGroupCode='" + commonGroupCode + "'" +
                ", commonGroupCodeName='" + commonGroupCodeName + "'" +
                ", commonGroupCodeDesc='" + commonGroupCodeDesc + "'" +
                ", sortNum=" + sortNum +
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
