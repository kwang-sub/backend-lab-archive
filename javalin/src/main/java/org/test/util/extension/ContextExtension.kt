package org.test.util.extension

import io.javalin.http.Context
import org.test.util.constant.PersistenceConstant
import javax.persistence.EntityManager

fun Context.getEntityManager(): EntityManager {
    return this.req().getAttribute(PersistenceConstant.EM) as? EntityManager ?: throw NullPointerException()
}