package org.example.level2bookmanagementsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.fail
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.CommandFactory
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var bookRepository: BookRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtUtil: JwtUtil
    @Autowired
    lateinit var entityFactory: EntityFactory

    private val baseUrl = "/api/v1/books"
    @Autowired
    lateinit var cleanUp: CleanUp


    init {
        beforeTest { cleanUp.all() }
        Given("책 생성 요청") {
            When("유효한 입력이면") {
                Then("책을 생성한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val command = BookCommand(
                        title = "타이틀",
                        author = "작가",
                        publisher = "출판사",
                        publishedDate = LocalDate.now(),
                        isbn = "123-456-789",
                        price = BigDecimal.valueOf(10_000),
                    )

                    mockMvc.perform(
                        post(baseUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                            .header("Authorization", "Bearer $token")
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.title").value("타이틀"))
                        .andExpect(jsonPath("$.author").value("작가"))
                        .andExpect(jsonPath("$.publisher").value("출판사"))
                        .andExpect(jsonPath("$.publishedDate").isNotEmpty)
                        .andExpect(jsonPath("$.isbn").value("123-456-789"))
                        .andExpect(jsonPath("$.price").value(10_000))

                    val allBook = bookRepository.findAll()
                    allBook.shouldHaveSize(1)
                    val book = allBook[0]
                    book.title shouldBe "타이틀"
                    book.author shouldBe "작가"
                    book.publisher shouldBe "출판사"
                    book.publishedDate shouldBe command.publishedDate
                    book.isbn shouldBe "123-456-789"
                    book.price.compareTo(BigDecimal.valueOf(10_000)) shouldBe 0
                }
            }

            When("유효하지 않은 입력이면") {
                Then("400을 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    CommandFactory.getInvalidBookCommandList().forEach { invalidCommand ->
                        mockMvc.perform(
                            post(baseUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidCommand))
                                .header("Authorization", "Bearer $token")
                        ).andExpect(status().isBadRequest)
                    }
                }
            }
        }

        Given("책 상세 조회") {
            When("책이 존재하면") {
                Then("상세 정보를 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook()

                    mockMvc.perform(get("$baseUrl/${book.id}").header("Authorization", "Bearer $token"))
                        .andExpect(status().isOk)
                        .andExpect(jsonPath("$.title").value(book.title))
                        .andExpect(jsonPath("$.author").value(book.author))
                        .andExpect(jsonPath("$.publisher").value(book.publisher))
                        .andExpect(jsonPath("$.publishedDate").value(book.publishedDate.toString()))
                        .andExpect(jsonPath("$.isbn").value(book.isbn))
                        .andExpect(jsonPath("$.price").value(book.price))
                }
            }

            When("책이 존재하지 않으면") {
                Then("404를 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    mockMvc.perform(get("$baseUrl/1").header("Authorization", "Bearer $token"))
                        .andExpect(status().isNotFound)
                }
            }
        }

        Given("책 수정 요청") {
            When("책이 존재하면") {
                Then("정보를 수정한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook()
                    val today = LocalDate.now()
                    val command = BookCommand(
                        title = "수정된 타이틀",
                        author = "수정된 작가",
                        publisher = "수정된 출판사",
                        publishedDate = today,
                        isbn = "123-456-789",
                        price = BigDecimal.valueOf(20_000)
                    )

                    mockMvc.perform(
                        patch("$baseUrl/${book.id}")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer $token")
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.title").value("수정된 타이틀"))
                        .andExpect(jsonPath("$.author").value("수정된 작가"))
                        .andExpect(jsonPath("$.publisher").value("수정된 출판사"))
                        .andExpect(jsonPath("$.publishedDate").value(today.toString()))
                        .andExpect(jsonPath("$.isbn").value("123-456-789"))
                        .andExpect(jsonPath("$.price").value(20_000))

                    val updatedBook = book.id?.let { bookRepository.findById(it).orElse(null) } ?: fail("책이 존재하지 않습니다.")
                    updatedBook.title shouldBe "수정된 타이틀"
                    updatedBook.author shouldBe "수정된 작가"
                    updatedBook.publisher shouldBe "수정된 출판사"
                    updatedBook.publishedDate shouldBe today
                    updatedBook.isbn shouldBe "123-456-789"
                    updatedBook.price.compareTo(BigDecimal.valueOf(20_000)) shouldBe 0
                }
            }

            When("유효하지 않은 값이면") {
                Then("400을 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook()
                    CommandFactory.getInvalidBookCommandList().forEach { invalidCommand ->
                        mockMvc.perform(
                            patch("$baseUrl/${book.id}")
                                .header("Authorization", "Bearer $token")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidCommand))
                        ).andExpect(status().isBadRequest)
                    }
                }
            }

            When("책이 존재하지 않으면") {
                Then("404를 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val command = BookCommand(
                        title = "타이틀",
                        author = "작가",
                        publisher = "출판사",
                        publishedDate = LocalDate.now(),
                        isbn = "123-456-789",
                        price = BigDecimal.valueOf(10_000),
                    )
                    mockMvc.perform(
                        patch("$baseUrl/${Int.MIN_VALUE}")
                            .header("Authorization", "Bearer $token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(command))
                    ).andExpect(status().isNotFound)
                }
            }
        }

        Given("책 삭제 요청") {
            When("책이 존재하면") {
                Then("삭제한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    val book = entityFactory.createBook()
                    mockMvc.perform(delete("$baseUrl/${book.id}").header("Authorization", "Bearer $token"))
                        .andExpect(status().is2xxSuccessful)

                    val findBook = bookRepository.findById(book.id!!)
                    findBook.isEmpty shouldBe true
                }
            }

            When("책이 존재하지 않으면") {
                Then("404를 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    mockMvc.perform(delete("$baseUrl/${Int.MIN_VALUE}").header("Authorization", "Bearer $token"))
                        .andExpect(status().isNotFound)
                }
            }
        }

        Given("책 목록 조회") {
            When("11개의 책이 존재하면") {
                Then("페이지네이션으로 10개를 반환한다") {
                    val token = entityFactory.generateToken(passwordEncoder, jwtUtil).accessToken
                    entityFactory.createBooks(11)

                    mockMvc.perform(
                        get(baseUrl)
                            .header("Authorization", "Bearer $token")
                            .param("page", "0")
                            .param("size", "10")
                            .param("sort", "title,desc")
                    ).andExpect(status().isOk)
                        .andExpect(jsonPath("$.content.size()").value(10))
                        .andExpect(jsonPath("$.content[0].title").value("Test Book 9"))
                        .andExpect(jsonPath("$.content[9].title").value("Test Book 10"))
                }
            }
        }
    }
}
