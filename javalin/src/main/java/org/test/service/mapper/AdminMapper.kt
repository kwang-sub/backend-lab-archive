package org.test.service.mapper

import org.mapstruct.Mapper
import org.test.domain.StdAdmin
import org.test.service.dto.StdAdminDTO


@Mapper
interface AdminMapper : EntityMapper<StdAdminDTO, StdAdminDTO, StdAdmin> {

}