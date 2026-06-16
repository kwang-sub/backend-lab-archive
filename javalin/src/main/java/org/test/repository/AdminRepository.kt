package org.test.repository

import org.test.domain.StdAdmin
import javax.persistence.EntityManager

class AdminRepository {
    fun save(entity: StdAdmin, em: EntityManager): StdAdmin {
        em.persist(entity)
        return entity
    }
}