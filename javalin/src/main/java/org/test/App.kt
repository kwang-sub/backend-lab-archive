package org.test

import com.auth0.jwt.exceptions.TokenExpiredException
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.after
import io.javalin.apibuilder.ApiBuilder.before
import io.javalin.apibuilder.ApiBuilder.crud
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.Context
import io.javalin.json.JavalinJackson
import io.javalin.validation.ValidationException
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.test.config.startKoin
import org.test.service.dto.Role
import org.test.util.handler.tokenExpiredExceptionHandler
import org.test.util.handler.validationExceptionHandler
import org.test.util.Filters
import org.test.util.Filters.Companion.handleAuth
import org.test.util.Filters.Companion.handleCheckUserRole
import org.test.util.adminController
import java.sql.DriverManager

const val SYSTEM_TIME_ZONE = "Asia/Seoul"
fun main() {
    // 시스템 타임존 설정
    System.setProperty("user.timezone", SYSTEM_TIME_ZONE)
    // DI 라이브러리(Koin) 추가
    startKoin()
    // Database migration 라이브러리(Liquibase) 추가
    startLiquibase()

    val app = Javalin.create { config ->
        config.router.apiBuilder {
            crud("/api/v1/std-admins/{adminId}", adminController, Role.ROLE_USER)
            get("token/{userId}") { ctx: Context? -> adminController.login(ctx!!) }
            before(Filters.handleInsereEntityManager())
            after(Filters.handleFechaEntityManager())
        }
        config.jsonMapper(JavalinJackson().updateMapper {
            it.registerModule(JavaTimeModule())
            it.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        })
    }
        .start(8080)


    app.before(handleCheckUserRole())
    app.before(handleAuth())

    app.exception(ValidationException::class.java, validationExceptionHandler)
    app.exception(TokenExpiredException::class.java, tokenExpiredExceptionHandler)
}

fun startLiquibase() {
    val connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/javalin", "root", "weeds0407")
    val database =
        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
    val liquibase = liquibase.Liquibase("config/liquibase/master.xml", ClassLoaderResourceAccessor(), database)
    liquibase.update(Contexts(), LabelExpression())
}




