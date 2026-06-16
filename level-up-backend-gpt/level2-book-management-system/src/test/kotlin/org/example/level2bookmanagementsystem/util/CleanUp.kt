package org.example.level2bookmanagementsystem.util

import jakarta.persistence.EntityManager
import jakarta.persistence.Table
import jakarta.persistence.metamodel.EntityType
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CleanUp(
    private var jdbcTemplate: JdbcTemplate,
    private var entityManager: EntityManager
) {
    @Transactional
    fun all() {
        val tables = entityManager.metamodel.entities.map(::entityToTableName)
        // 외래키 제약 조건 해제
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE")
        try {
            tables.forEach { table -> jdbcTemplate.execute("TRUNCATE TABLE $table") }
        } finally {
            jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE")
        }
    }

    private fun entityToTableName(entity: EntityType<*>): String {
        val javaType = entity.javaType
        val tableAnnot = javaType.getAnnotation(Table::class.java)
        val annotatedName = tableAnnot?.name?.takeIf { it.isNotBlank() }
        return annotatedName ?: toSnakeUpper(javaType.simpleName)
    }

    private fun toSnakeUpper(name: String): String {
        val withUnderscores = name
            .replace(Regex("([a-z0-9])([A-Z])"), "$1_$2")
            .replace(Regex("([A-Z])([A-Z][a-z])"), "$1_$2")
        return withUnderscores.uppercase(Locale.ROOT)
    }
}