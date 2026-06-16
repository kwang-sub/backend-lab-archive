package org.example.level2bookmanagementsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtUtil: JwtUtil
    @Autowired
    lateinit var entityFactory: EntityFactory
    @Autowired
    lateinit var cleanUp: CleanUp


    init {
        beforeTest { cleanUp.all() }

        Given("어드민 권한이 필요한 API") {
            When("어드민이 호출할 경우") {
                Then("정상 응답을 반환한다") {
                    val admin = entityFactory.createAdmin("admin", "password123", passwordEncoder)
                    val bookCommand = BookCommand(
                        title = "타이틀",
                        author = "작가",
                        publisher = "출판사",
                        publishedDate = LocalDate.now(),
                        isbn = "123-456-789",
                        price = BigDecimal.valueOf(10_000),
                    )
                    val token = jwtUtil.generateToken(admin.userLoginId).accessToken
                    mockMvc.perform(
                        get("/api/v1/admin")
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(bookCommand))
                    ).andExpect(status().isOk)
                }
            }

            When("일반 사용자가 호출할 경우") {
                Then("Forbidden을 반환한다") {
                    val user = entityFactory.createUser("user", "password123", passwordEncoder)
                    val bookCommand = BookCommand(
                        title = "타이틀",
                        author = "작가",
                        publisher = "출판사",
                        publishedDate = LocalDate.now(),
                        isbn = "123-456-789",
                        price = BigDecimal.valueOf(10_000),
                    )
                    val token = jwtUtil.generateToken(user.userLoginId).accessToken
                    mockMvc.perform(
                        get("/api/v1/admin")
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(bookCommand))
                    ).andExpect(status().isForbidden)
                }
            }
        }
    }
}