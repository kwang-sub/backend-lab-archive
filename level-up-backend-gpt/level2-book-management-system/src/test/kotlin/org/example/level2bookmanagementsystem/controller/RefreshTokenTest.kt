package org.example.level2bookmanagementsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.level2bookmanagementsystem.base.repository.UserTokenRepository
import org.example.level2bookmanagementsystem.security.command.SignInCommand
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.security.util.CryptoUtil
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.ZonedDateTime

@SpringBootTest
@AutoConfigureMockMvc
class RefreshTokenTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var jwtUtil: JwtUtil
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var userTokenRepository: UserTokenRepository
    @Autowired
    lateinit var cryptoUtil: CryptoUtil
    @Autowired
    lateinit var entityFactory: EntityFactory

    private val baseUrl = "/api/v1/auth"

    @Autowired
    lateinit var cleanUp: CleanUp


    init {
        beforeTest { cleanUp.all() }
        Given("signin 요청") {
            When("정상 로그인 시") {
                Then("refresh 토큰을 저장하고 해시를 검증한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val signInCommand = SignInCommand(user.userLoginId, "password123")

                    val result = mockMvc.perform(
                        post("$baseUrl/signin")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(signInCommand))
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.accessToken").isNotEmpty)
                        .andReturn()

                    val response = result.response.contentAsString
                    val authTokenResponse = objectMapper.readValue(response, AuthTokenResponse::class.java)
                    val stored = userTokenRepository.findByTbUserUserLoginId(user.userLoginId)
                    stored.shouldNotBe(null)
                    val hashedRefresh = stored!!.refreshToken
                    hashedRefresh.shouldNotBe(null)
                    cryptoUtil.verifyArgon2(hashedRefresh, authTokenResponse.refreshToken.toCharArray()).shouldBeTrue()
                }
            }
        }

        Given("logout 요청") {
            When("유효한 토큰으로 로그아웃하면") {
                Then("refresh 토큰을 삭제한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)

                    val userLoginId = user.userLoginId
                    val generated = jwtUtil.generateToken(userLoginId)
                    entityFactory.createToken(userLoginId, generated.refreshToken)
                    val accessToken = generated.accessToken

                    userTokenRepository.findByTbUserUserLoginId(userLoginId).shouldNotBe(null)

                    mockMvc.perform(
                        post("$baseUrl/logout")
                            .header("Authorization", "Bearer $accessToken")
                    ).andExpect(status().isOk)

                    userTokenRepository.findByTbUserUserLoginId(userLoginId).shouldBe(null)
                }
            }

            When("유효하지 않은 토큰으로 로그아웃하면") {
                Then("401을 반환한다") {
                    mockMvc.perform(
                        post("$baseUrl/logout")
                            .header("Authorization", "Bearer invalid_token")
                    ).andExpect(status().isUnauthorized)
                }
            }

            When("로그아웃된 토큰으로 로그아웃하면") {
                Then("401을 반환한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val signInCommand = SignInCommand(user.userLoginId, "password123")

                    val result = mockMvc.perform(
                        post("$baseUrl/signin")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(objectMapper.writeValueAsString(signInCommand))
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.accessToken").isNotEmpty)
                        .andReturn()

                    val response = result.response.contentAsString
                    val authTokenResponse = objectMapper.readValue(response, AuthTokenResponse::class.java)

                    val accessToken = authTokenResponse.accessToken
                    mockMvc.perform(
                        post("$baseUrl/logout")
                            .header("Authorization", "Bearer $accessToken")
                    ).andExpect(status().isOk)

                    mockMvc.perform(
                        post("$baseUrl/logout")
                            .header("Authorization", "Bearer $accessToken")
                    ).andExpect(status().isUnauthorized)
                }
            }
            
        }

        Given("reissue 요청") {
            When("올바른 refresh 토큰이 존재하면") {
                Then("새 토큰을 발급하고 해시를 갱신한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val tokenPair = jwtUtil.generateToken(user.userLoginId)
                    entityFactory.createToken(user.userLoginId, tokenPair.refreshToken)
                    val beforeStored = userTokenRepository.findByTbUserUserLoginId(user.userLoginId)?.refreshToken

                    val result = mockMvc.perform(
                        post("$baseUrl/reissue")
                            .header("Authorization", "Bearer ${tokenPair.refreshToken}")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.accessToken").isNotEmpty)
                        .andReturn()

                    val response = result.response.contentAsString
                    val newTokens = objectMapper.readValue(response, AuthTokenResponse::class.java)
                    val afterStored = userTokenRepository.findByTbUserUserLoginId(user.userLoginId)?.refreshToken
                    afterStored shouldNotBe(null)
                    beforeStored shouldNotBe afterStored
                    cryptoUtil.verifyArgon2(afterStored!!, newTokens.refreshToken.toCharArray()).shouldBeTrue()
                }
            }

            When("refresh 토큰이 없으면") {
                Then("401을 반환한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val tokenPair = jwtUtil.generateToken(user.userLoginId)

                    mockMvc.perform(
                        post("$baseUrl/reissue")
                            .header("Authorization", "Bearer ${tokenPair.refreshToken}")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                    ).andExpect(status().isUnauthorized)
                }
            }

            When("refresh 토큰이 만료되면") {
                Then("401을 반환한다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val tokenPair = jwtUtil.generateToken(user.userLoginId)
                    entityFactory.createToken(user.userLoginId, tokenPair.refreshToken, ZonedDateTime.now().minusDays(1))

                    mockMvc.perform(
                        post("$baseUrl/reissue")
                            .header("Authorization", "Bearer ${tokenPair.refreshToken}")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                    ).andExpect(status().isUnauthorized)
                }

                Then("기존 토큰은 삭제된다") {
                    val user = entityFactory.createUser("user123", "password123", passwordEncoder)
                    val tokenPair = jwtUtil.generateToken(user.userLoginId)
                    entityFactory.createToken(user.userLoginId, tokenPair.refreshToken, ZonedDateTime.now().minusDays(1))

                    val userToken1 = userTokenRepository.findByTbUserUserLoginId(user.userLoginId)
                    userToken1.shouldNotBe(null)

                    mockMvc.perform(
                        post("$baseUrl/reissue")
                            .header("Authorization", "Bearer ${tokenPair.refreshToken}")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                    ).andExpect(status().isUnauthorized)

                    val userToken = userTokenRepository.findByTbUserUserLoginId(user.userLoginId)
                    userToken.shouldBe(null)
                }
            }
        }
    }
}
