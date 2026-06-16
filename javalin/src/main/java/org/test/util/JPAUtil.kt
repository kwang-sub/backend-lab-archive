package org.test.util

import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

class JPAUtil {
    companion object {
        private val PERSISTENCE_UNIT_NAME = "javalin-db"
        private var factory: EntityManagerFactory? = null

        fun getEntityManagerFactory(): EntityManagerFactory {
            if (factory == null || (factory != null && !factory!!.isOpen)) {
                println()
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
            }
            return factory!!
        }

        fun shutdown() {
            if (factory != null) {
                factory!!.close()
                factory = null
            }
        }
    }
}