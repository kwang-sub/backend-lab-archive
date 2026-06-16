package org.test.util

import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import io.javalin.security.RouteRole
import org.test.util.constant.PersistenceConstant
import org.test.service.dto.Role
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

class Filters {
    companion object{
        @JvmStatic
        fun handleInsereEntityManager(): Handler = Handler { ctx ->
            val emf: EntityManagerFactory = JPAUtil.getEntityManagerFactory()
            val em = emf.createEntityManager()
            em.transaction.begin()
            ctx.req().setAttribute(PersistenceConstant.EM, em)
        }

        @JvmStatic
        fun handleFechaEntityManager(): Handler = Handler { ctx ->
            val em = ctx.req().getAttribute(PersistenceConstant.EM) as? EntityManager
            if (em != null && em.isOpen) {
                try {
                    em.transaction.commit()
                } catch (ex: Exception) {
                    em.transaction.rollback()
                } finally {
                    try {
                        em.close()
                        em.clear()
                    } catch (exf: Exception) {
                    }
                }
            }
        }

        @JvmStatic
        fun handleCheckUserRole(): Handler = Handler { ctx ->
            val userRole = getUserRole(ctx)
            if (ctx.routeRoles().isNotEmpty() && !ctx.routeRoles().contains(userRole)) throw UnauthorizedResponse()
        }

        @JvmStatic
        fun handleAuth(): Handler = Handler { ctx ->
            val token = ctx.header("Authorization")?.substringAfter("Bearer ")

//            TokenProvider().checkToken(token!!)
        }
    }
}

fun getUserRole(ctx: Context): RouteRole {
    return Role.ROLE_USER
}