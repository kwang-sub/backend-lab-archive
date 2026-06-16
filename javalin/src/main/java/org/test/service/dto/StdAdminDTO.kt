package org.test.service.dto

import java.io.Serializable
import java.time.ZonedDateTime

/**
 * A DTO for the [com.weedscomm.server.leesan.domain.core.StdAdmin] entity.
 */
data class StdAdminDTO(

    var id: Long? = null,

    /**
     * 사용자로그인아이디
     */
    var adminLoginId: String? = null,

    var adminPassword: String? = null,

    /**
     * 사용자명
     */
    var adminUserName: String? = null,

    /**
     * 사용자연락처
     */
    var adminPhoneNum: String? = null,

    /**
     * 사용자이메일
     */
    var adminEmail: String? = null,

    /**
     * 활성화여부
     */
    var isActivated: Boolean? = true,

    /**
     * 삭제여부
     */
    var isDeleted: Boolean? = false,

    /**
     * 등록자ID
     */
    var createdBy: String? = "",

    /**
     * 등록일시
     */
    var createdDate: ZonedDateTime? = ZonedDateTime.now(),

    /**
     * 수정자ID
     */
    var lastModifiedBy: String? = "",

    /**
     * 수정일시
     */
    var lastModifiedDate: ZonedDateTime? = ZonedDateTime.now(),
) : Serializable
