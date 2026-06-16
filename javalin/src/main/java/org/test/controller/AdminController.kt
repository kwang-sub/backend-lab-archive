package org.test.controller

import com.querydsl.jpa.impl.JPAQueryFactory
import io.javalin.apibuilder.CrudHandler
import io.javalin.http.Context
import org.test.domain.QStdAdmin.stdAdmin
import org.test.service.AdminService
import org.test.service.dto.StdAdminDTO
import org.test.util.TokenProvider
import org.test.util.constant.PersistenceConstant
import org.test.util.createValidationException
import org.test.util.extension.getEntityManager
import javax.persistence.EntityManager


class AdminController(
    private val adminService: AdminService
) : CrudHandler {
    override fun create(ctx: Context) {
        val em = ctx.req().getAttribute(PersistenceConstant.EM) as EntityManager
        val dto = ctx.bodyAsClass(StdAdminDTO::class.java)
        val stdAdmin: StdAdminDTO = adminService.create(dto, em)
        ctx.json(stdAdmin)
    }



    override fun delete(ctx: Context, resourceId: String) {
        ctx.result("delete")
    }

    override fun getAll(ctx: Context) {
        val em = ctx.getEntityManager()
        val jpaQueryFactory = JPAQueryFactory(em)
        val result = jpaQueryFactory.selectFrom(stdAdmin)
            .fetch()

        ctx.json(result)
    }

    override fun getOne(ctx: Context, resourceId: String) {
        ctx.result("getOne")
    }

    override fun update(ctx: Context, resourceId: String) {
        ctx.result("update")
    }


    fun login(ctx: Context) {
//            val pathParam = ctx.pathParam("userId")
//            println("파라미터 " + pathParam)
//            println(ctx.pathParamMap())
        val header = ctx.header("Authorization")
        println("11 " + header?.substringAfter("Bearer "))

        val id = ctx.pathParam("adminId").toLongOrNull() ?: throw createValidationException("adminId", "not_number")
        val em = ctx.getEntityManager()
        val member = JPAQueryFactory(em)
            .selectFrom(stdAdmin)
            .where(stdAdmin.id.eq(id))
            .fetchOne()
            ?: throw NullPointerException()
        TokenProvider().checkToken(header?.substringAfter("Bearer ")!!)
        val createToken = TokenProvider().createTokens(member)
        println("발급 토큰" + createToken)
        ctx.json(createToken)
    }

}