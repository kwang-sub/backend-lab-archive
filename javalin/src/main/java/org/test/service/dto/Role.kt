package org.test.service.dto

import io.javalin.security.RouteRole

enum class Role: RouteRole {
    ROLE_ADMIN, ROLE_USER
}