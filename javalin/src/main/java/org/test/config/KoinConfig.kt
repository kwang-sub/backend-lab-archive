package org.test.config

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.mapstruct.factory.Mappers
import org.test.controller.AdminController
import org.test.repository.AdminRepository
import org.test.service.AdminService
import org.test.service.mapper.AdminMapper


fun startKoin() {
    startKoin {
        modules(
            getControllerModules(),
            getServiceModules(),
            getRepository(),
            getMapper(),
        )
    }
}

fun getMapper() = module {
    val adminMapper = Mappers.getMapper(AdminMapper::class.java)
    single { adminMapper }
}

fun getRepository() = module {
    single { AdminRepository() }
}

fun getServiceModules() = module {
    single { AdminService(get(), get()) }
}

private fun getControllerModules() = module {
    single { AdminController(get()) }
}