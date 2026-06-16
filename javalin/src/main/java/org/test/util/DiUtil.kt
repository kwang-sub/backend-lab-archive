package org.test.util

import org.koin.java.KoinJavaComponent
import org.test.controller.AdminController

val adminController: AdminController by KoinJavaComponent.inject(AdminController::class.java)
