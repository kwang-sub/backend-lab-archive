package org.example.level2bookmanagementsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.example.level2bookmanagementsystem.base.dto.command.BookLoanCommand
import org.example.level2bookmanagementsystem.base.dto.response.BookLoanResponse
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.jpa.status.BookLoanStatus
import org.example.level2bookmanagementsystem.security.entity.LoanInfo
import org.example.level2bookmanagementsystem.security.entity.Role
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.ZonedDateTime

@SpringBootTest
@AutoConfigureMockMvc
class BookLoanControllerTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)
    val baseUrl = "/api/v1/book-loans"

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var cleanUp: CleanUp

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var entityFactory: EntityFactory

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    init {
        beforeTest { cleanUp.all() }
        Given("도서가 등록되어 있는 경우") {
            When("유효한 도서 대출 요청을 하면") {
                Then("도서가 대출된다") {
                    val before = ZonedDateTime.now()
                    val generateToken = entityFactory.generateToken(passwordEncoder, jwtUtil)
                    val book = entityFactory.createBook()

                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer ${generateToken.accessToken}")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    )
                        .andExpect(status().isOk)
                        .andReturn()

                    val response = result.response.contentAsString
                    val bookLoanResponse = objectMapper.readValue(response, BookLoanResponse::class.java)
                    val after = ZonedDateTime.now()


                    bookLoanResponse.id shouldNotBe null
                    bookLoanResponse.book.id shouldBe book.id
                    bookLoanResponse.user.id shouldNotBe null
                    bookLoanResponse.loanDate.shouldBeAfter(before)
                    bookLoanResponse.loanDate.shouldBeBefore(after)
                    bookLoanResponse.renewCount shouldBe 0
                    bookLoanResponse.dueDate shouldBe bookLoanResponse.loanDate.plusDays(BookLoan.DEFAULT_LOAN_DATE)

                }
            }


            When("올바르지 않은 정보를 입력하면") {
                Then("대출이 실패한다") {
                    val bookLoanCommand = BookLoanCommand(0, isRenew = true)
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(bookLoanCommand))
                    )
                        .andExpect(status().isBadRequest)
                }
            }

            When("이미 대출한 도서를 대출 요청하면") {
                Then("대출이 실패한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook()
                    val bookLoan = entityFactory.createBookLoan(book, token)

                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isBadRequest)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.message shouldBe "이미 대출한 도서인 경우"
                    errorResponse.status shouldBe 400
                }
            }

            When("재고가 없는 도서를 대출 요청하면") {
                Then("대출이 실패한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook(stock = 0)

                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isBadRequest)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.message shouldBe "도서 재고가 부족한 경우"
                    errorResponse.status shouldBe 400
                }
            }

            When("대출 가능 권수를 초과하여 대출 요청하면") {
                Then("대출이 실패한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    repeat(BookLoan.MAX_LOANED_BOOK_COUNT) {
                        val book = entityFactory.createBook()
                        entityFactory.createBookLoan(book, token)
                    }
                    val book = entityFactory.createBook(10)
                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isBadRequest)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.status shouldBe 400
                    errorResponse.message shouldBe "대출 한도를 초과한 경우"
                }
            }

            When("반납일이 지난 대출 이력이 있는 경우 대출 요청하면") {
                Then("대출이 실패한다") {
                    val book = entityFactory.createBook(10)
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val overdueBookLoan = entityFactory.createBookLoan(book, token, ZonedDateTime.now().minusDays(1L))

                    val book2 = entityFactory.createBook(10)
                    val command = BookLoanCommand(book2.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isBadRequest)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.status shouldBe 400
                    errorResponse.message shouldBe "연체된 도서 대출이 존재하는 경우"
                }
            }

            When("대출 가능일이 현재 시점보다 뒤인 사용자가 대출 요청하면") {
                Then("대출이 실패한다") {
                    val book = entityFactory.createBook(10)
                    val user = entityFactory.createUser(
                        "test",
                        "password123",
                        passwordEncoder,
                        LoanInfo(ZonedDateTime.now().plusDays(3))
                    )
                    val token = jwtUtil.generateToken(user.userLoginId).accessToken

                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isBadRequest)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.status shouldBe 400
                    errorResponse.message shouldBe "도서 대출이 제한된 경우"
                }
            }

            When("비회원이 도서 대출을 요청하면") {
                Then("대출이 실패한다") {
                    val book = entityFactory.createBook()
                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer invalid_token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isUnauthorized)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)
                    errorResponse.status shouldBe 401
                    errorResponse.message shouldBe "인증이 실패한 경우"
                }
            }

            When("관리자가 도서 대출을 요청하면") {
                Then("대출이 실패한다") {
                    val adminAccessToken =
                        entityFactory.generateToken(passwordEncoder, jwtUtil, Role.ROLE_ADMIN).accessToken
                    val book = entityFactory.createBook()
                    val command = BookLoanCommand(book.id!!, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $adminAccessToken")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isForbidden)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)
                    errorResponse.status shouldBe 403
                    errorResponse.message shouldBe "권한이 없는 경우"

                }
            }

        }

        Given("도서가 없는 경우") {
            When("도서 대출을 요청하면") {
                Then("대출이 실패한다") {
                    val accessToken = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val command = BookLoanCommand(0, isRenew = false)
                    val result = mockMvc.perform(
                        post(baseUrl)
                            .header("Authorization", "Bearer $accessToken")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isNotFound)
                        .andReturn()

                    val response = result.response.contentAsString
                    val errorResponse = objectMapper.readValue(response, ErrorResponse::class.java)

                    errorResponse.status shouldBe 404
                    errorResponse.message shouldBe "Entity(Book)를 찾을 수 없는 경우"
                }
            }
        }

        Given("도서 대출이 되어 있는 경우") {
            When("유효한 도서 반납 요청을 하면") {
                Then("도서가 반납된다") {
                    val book = entityFactory.createBook(10)
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val bookLoan = entityFactory.createBookLoan(book, token)

                    val result = mockMvc.perform(
                        post("$baseUrl/${bookLoan.id}/return")
                            .header("Authorization", "Bearer $token")
                    ).andExpect(status().isOk)
                        .andReturn()

                    val response = result.response.contentAsString
                    val bookLoanResponse = objectMapper.readValue(response, BookLoanResponse::class.java)

                    bookLoanResponse.returnDate shouldNotBe null
                    bookLoanResponse.status shouldBe BookLoanStatus.RETURNED

                    val resultBook = bookRepository.findById(book.id!!)
                        .orElse(null)
                    resultBook?.stockQuantity shouldBe 10
                }
            }

            When("반납일이 지난 도서 반납 요청을 하면") {
                Then("사용자의 대출 가능일이 현재 시점부터 연체된 일수만큼 뒤로 설정된다") {
                    val book = entityFactory.createBook(10)
                    val user = entityFactory.createUser("testUser", "password123", passwordEncoder)
                    val token = jwtUtil.generateToken(user.userLoginId).accessToken
                    val bookLoan = entityFactory.createBookLoan(book, token, ZonedDateTime.now().minusDays(3L))

                    val result = mockMvc.perform(
                        post("$baseUrl/${bookLoan.id}/return")
                            .header("Authorization", "Bearer $token")
                    ).andExpect(status().isOk)
                        .andReturn()

                    val response = result.response.contentAsString
                    val bookLoanResponse = objectMapper.readValue(response, BookLoanResponse::class.java)

                    bookLoanResponse.returnDate shouldNotBe null
                    bookLoanResponse.status shouldBe BookLoanStatus.RETURNED

                    val resultUser = userRepository.findByUserLoginId(user.userLoginId)

                    resultUser?.loanInfo?.nextLoanAvailableDate?.toLocalDate() shouldBe ZonedDateTime.now().plusDays(3L)
                        .toLocalDate()
                }
            }

            When("대출한 회원이 아닌 사용자가 도서 반납을 요청하면") {
                Then("반납이 실패한다") {

                }
            }

            When("비회원이 도서 반납을 요청하면") {
                Then("반납이 실패한다") {

                }
            }

            When("관리자가 도서 반납을 요청하면") {
                Then("반납이 실패한다") {

                }
            }
        }

        Given("도서 대출이 되어 있지 않은 경우") {
            When("도서 반납을 요청하면") {
                Then("반납이 실패한다") {

                }
            }
        }

        Given("도서 대출 현황 조회 요청을 하면") {
            When("유효한 요청이면") {
                Then("대출 현황을 반환한다") {

                }
            }

            When("비회원이 요청하면") {
                Then("조회가 실패한다") {

                }
            }

            When("관리자가 요청하면") {
                Then("조회가 실패한다") {

                }
            }
        }

        Given("도서 대출 이력 조회 요청을 하면") {
            When("유효한 요청이면") {
                Then("대출 이력을 반환한다") {

                }
            }

            When("관리자가 요청하면") {
                Then("조회가 성공한다") {

                }
            }

            When("비회원이 요청하면") {
                Then("조회가 실패한다") {

                }
            }

            When("본인이 아닌 사용자가 요청하면") {
                Then("조회가 실패한다") {

                }
            }

        }
    }
}