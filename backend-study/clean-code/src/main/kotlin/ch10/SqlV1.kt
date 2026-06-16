package ch10

interface Sql {
    fun select(id: Long): String
    fun insert(createDTO: Any): String
    fun update(updateDTO: Any): String
    fun delete(id: Long): String
}

class MysqlSql: Sql {
    override fun select(id: Long): String = "..."
    override fun insert(createDTO: Any): String = "..."
    override fun update(updateDTO: Any): String = "..."
    override fun delete(id: Long): String = "..."
}

class OracleSql: Sql {
    override fun select(id: Long): String = selectById(id)
    private fun selectById(id: Long): String = "..."
    override fun insert(createDTO: Any): String = "..."
    override fun update(updateDTO: Any): String = "..."
    override fun delete(id: Long): String = "..."
}

class UserService(
    val sql: Sql
) {
    fun findOne(id: Long) {
        val selectQuery = sql.select(id)
    }
}