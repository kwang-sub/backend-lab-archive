package org.test.service

import org.test.repository.AdminRepository
import org.test.service.dto.StdAdminDTO
import org.test.service.mapper.AdminMapper
import javax.persistence.EntityManager

class AdminService(
    private val adminRepository: AdminRepository,
    private val adminMapper: AdminMapper,
) {
    fun create(dto: StdAdminDTO, em: EntityManager): StdAdminDTO {

        return adminMapper.toEntity(dto)
            .let { adminRepository.save(it, em) }
            .let(adminMapper::toDto)
    }
}