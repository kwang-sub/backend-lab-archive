package ch10

interface ReadSql {
    fun select(id: Long): String
}

interface ManagerSql: ReadSql {
    fun insert(createDTO: Any): String
    fun update(updateDTO: Any): String
    fun delete(id: Long): String
}

class MysqlSqlV2: ReadSql, ManagerSql {
    override fun select(id: Long): String = "..."
    override fun insert(createDTO: Any): String = "..."
    override fun update(updateDTO: Any): String = "..."
    override fun delete(id: Long): String = "..."
}


class UserServiceV2(
    val readSql: ReadSql
) {
    fun findOne(id: Long) {
        val selectQuery = readSql.select(id)
    }
}

class AdminServiceV2(
    val managerSql: ManagerSql
) {
    fun findOne(id: Long) {
        val selectQuery = managerSql.select(id)
    }

    fun delete(id: Long) {
        val deleteQuery = managerSql.delete(id)
    }
}