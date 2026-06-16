package org.example.level2bookmanagementsystem.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.example.level2bookmanagementsystem.base.repository.BookLoanRepository
import org.example.level2bookmanagementsystem.base.service.BookLoanService
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.util.CleanUp
import org.example.level2bookmanagementsystem.util.EntityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class BookLoanServiceTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var cleanUp: CleanUp

    @Autowired
    private lateinit var bookLoanService: BookLoanService

    @Autowired
    private lateinit var entityFactory: EntityFactory

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var bookLoanRepository: BookLoanRepository

    init {
        beforeTest { cleanUp.all() }

        Given("도서가 있을때") {
            When("여러 사용자가 동시 대출 및 반납하면") {
                Then("동시성 문제가 발생하지 않아야 한다") {
                    val threadCount = 50
                    val pool = Executors.newFixedThreadPool(16)

                    val start = CountDownLatch(1)
                    val done = CountDownLatch(threadCount)

                    val failure = AtomicInteger(0)
                    val success = AtomicInteger(0)

                    val book = entityFactory.createBook(50)

                    repeat(threadCount) { i ->
                        val user = entityFactory.createUser("user$i", "test", passwordEncoder)
                        pool.submit {
                            try {
                                start.await()
                                // 서비스 메서드가 @Transactional이면 스레드별로 트랜잭션이 잡힘
                                bookLoanService.loanBook(book.id!!, userLoginId = user.userLoginId, isRenew = false)
                                if (i % 2 == 0) {
                                    bookLoanService.returnBook(book.id!!, returnUsername = user.userLoginId)
                                }
                                success.incrementAndGet()
                            } catch (e: Exception) {
                                failure.incrementAndGet()
                            } finally {
                                done.countDown()
                            }
                        }
                    }

                    // when
                    start.countDown()
                    val finished = done.await(10, TimeUnit.SECONDS)
                    pool.shutdown()

                    // then
                    finished shouldBe true
                    success.get() shouldBe 50
                    failure.get() shouldBe 0


                    // 최종 DB 상태 검증
                    val totalLoans = bookLoanRepository.countByBookId(book.id!!)
                    totalLoans shouldBe 50L

                    val resultBook = bookRepository.findById(book.id!!)
                        .orElse(null)

                    resultBook?.stockQuantity shouldBe 25
                }
            }
        }
    }
}