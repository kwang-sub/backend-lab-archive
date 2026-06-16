package org.example.level2bookmanagementsystem.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationFilterTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtUtil: JwtUtil
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var entityFactory: EntityFactory

    private val baseUrl = "/api/v1/books"
    @Autowired
    lateinit var cleanUp: CleanUp


    init {
        beforeTest { cleanUp.all() }
        Given("JWT 인증 필터") {
            When("유효한 인증 토큰을 제공하면") {
                Then("요청이 성공한다") {
                    val tbUser: TbUser = entityFactory.createUser("testUser", "password123", passwordEncoder)
                    val token = jwtUtil.generateToken(tbUser.userLoginId).accessToken
                    mockMvc.perform(get(baseUrl).header("Authorization", "Bearer $token"))
                        .andExpect(status().isOk)
                }
            }

            When("유효하지 않은 토큰을 제공하면") {
                Then("401을 반환한다") {
                    mockMvc.perform(
                        get(baseUrl).header("Authorization", "Bearer invalid_token")
                            .param("page", "0").param("size", "10").param("sort", "title,desc")
                    ).andExpect(status().isUnauthorized)
                }
            }

            When("만료된 토큰을 제공하면") {
                Then("401을 반환한다") {
                    val tbUser: TbUser = entityFactory.createUser("expiredUser", "password123", passwordEncoder)
                    val expirationJwtUtil =
                        JwtUtil("cvbnaaorenghaoernyoaernyoanhobnathonoaesrombvboajdsf", -1000, -1000)
                    val expirationToken = expirationJwtUtil.generateToken(tbUser.userLoginId).accessToken
                    mockMvc.perform(
                        get(baseUrl).header("Authorization", "Bearer $expirationToken")
                            .param("page", "0").param("size", "10").param("sort", "title,desc")
                    ).andExpect(status().isUnauthorized)
                }
            }

            When("Authorization 헤더 없이 호출하면") {
                Then("401을 반환한다") {
                    mockMvc.perform(
                        get(baseUrl).param("page", "0").param("size", "10").param("sort", "title,desc")
                    ).andExpect(status().isUnauthorized)
                }
            }
        }
    }
}
