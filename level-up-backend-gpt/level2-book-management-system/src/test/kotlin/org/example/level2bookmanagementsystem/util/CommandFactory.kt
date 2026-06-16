package org.example.level2bookmanagementsystem.util

import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.security.command.SignupCommand
import org.example.level2bookmanagementsystem.security.entity.Role
import java.math.BigDecimal
import java.time.LocalDate

class CommandFactory {
    companion object {
        fun getInvalidBookCommandList(): List<BookCommand> {
            return listOf(
                BookCommand(
                    title = "",
                    author = "작가",
                    publisher = "출판사",
                    publishedDate = LocalDate.now(),
                    isbn = "123-456-789",
                    price = BigDecimal.valueOf(10_000)
                ),
                BookCommand(
                    title = "타이틀",
                    author = "",
                    publisher = "출판사",
                    publishedDate = LocalDate.now(),
                    isbn = "123-456-789",
                    price = BigDecimal.valueOf(10_000)
                ),
                BookCommand(
                    title = "타이틀",
                    author = "작가",
                    publisher = "",
                    publishedDate = LocalDate.now(),
                    isbn = "123-456-789",
                    price = BigDecimal.valueOf(10_000)
                ),
                BookCommand(
                    title = "타이틀",
                    author = "작가",
                    publisher = "출판사",
                    publishedDate = LocalDate.now(),
                    isbn = "123-456-789",
                    price = BigDecimal.valueOf(999)
                ),
            )
        }

        fun getInvalidSignupCommandList(): List<SignupCommand> {

            return listOf(
                SignupCommand(
                    userLoginId = "",
                    name = "테스트 사용자",
                    password = "password123",
                    role = Role.ROLE_USER
                ),
                SignupCommand(
                    userLoginId = "testUser",
                    name = "",
                    password = "password123",
                    role = Role.ROLE_USER
                ),
                SignupCommand(
                    userLoginId = "testUser",
                    name = "테스트 사용자",
                    password = "",
                    role = Role.ROLE_USER
                ),
            )
        }
    }
}

