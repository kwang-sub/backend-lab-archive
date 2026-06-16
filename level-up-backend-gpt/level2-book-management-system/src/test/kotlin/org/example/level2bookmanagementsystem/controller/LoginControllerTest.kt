package org.example.level2bookmanagementsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.example.level2bookmanagementsystem.security.command.SignInCommand
import org.example.level2bookmanagementsystem.security.command.SignupCommand
import org.example.level2bookmanagementsystem.security.entity.Role
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.CommandFactory
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var entityFactory: EntityFactory
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    private val baseUrl = "/api/v1/auth"
    @Autowired
    lateinit var cleanUp: CleanUp


    init {
        beforeTest { cleanUp.all() }
        Given("회원가입 API") {
            When("정상 입력을 전달하면") {
                Then("사용자를 생성한다") {
                    val signupCommand = SignupCommand(
                        userLoginId = "testUser",
                        name = "테스트 사용자",
                        password = "password123",
                        role = Role.ROLE_USER
                    )

                    mockMvc.perform(
                        MockMvcRequestBuilders.post("$baseUrl/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(signupCommand))
                    ).andExpect(status().isCreated)
                        .andExpect(jsonPath("$.id").isNotEmpty)
                        .andExpect(jsonPath("$.userLoginId").value("testUser"))
                        .andExpect(jsonPath("$.name").value("테스트 사용자"))
                        .andExpect(jsonPath("$.role").value("ROLE_USER"))
                }
            }

            When("잘못된 입력을 전달하면") {
                Then("400을 반환한다") {
                    CommandFactory.getInvalidSignupCommandList().forEach { invalidSignup ->
                        mockMvc.perform(
                            MockMvcRequestBuilders.post("$baseUrl/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidSignup))
                        ).andExpect(status().isBadRequest)
                            .andExpect(jsonPath("$.fieldErrors").isNotEmpty)
                    }
                }
            }

            When("중복 사용자를 등록하면") {
                Then("실패한다") {
                    entityFactory.createUser("user123", "password123", passwordEncoder)
                    val duplicate = SignupCommand(
                        userLoginId = "user123",
                        name = "중복 사용자",
                        password = "password123",
                        role = Role.ROLE_USER
                    )

                    mockMvc.perform(
                        MockMvcRequestBuilders.post("$baseUrl/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(duplicate))
                    ).andExpect(status().is4xxClientError)
                }
            }
        }

        Given("로그인 API") {
            When("올바른 자격 증명을 사용하면") {
                Then("토큰을 발급한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val signInCommand = SignInCommand(user.userLoginId, "password123")

                    mockMvc.perform(
                        MockMvcRequestBuilders.post("$baseUrl/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(signInCommand))
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.accessToken").isNotEmpty)
                }
            }

            When("잘못된 비밀번호를 사용하면") {
                Then("401을 반환한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val signInCommand = SignInCommand(user.userLoginId, "password1235451")

                    mockMvc.perform(
                        MockMvcRequestBuilders.post("$baseUrl/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(signInCommand))
                    ).andExpect(status().isUnauthorized)
                }
            }
        }
    }
}
