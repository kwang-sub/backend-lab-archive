package style

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BehaviorSpecTest : BehaviorSpec({

    Context("숫자 테스트") {
        Given("숫자 1, 2") {
            val number1 = 1
            val number2 = 2

            When("숫자들을 더하면") {
                val result = number1 + number2

                Then("결과는 3이어야 한다") {
                    result shouldBe 3
                }
            }

            When("숫자들을 곱하면") {
                val result = number1 * number2

                Then("결과는 2이어야 한다") {
                    result shouldBe 2
                }
            }

            And("숫자 3") {
                val number3 = 3

                When("숫자 1과 숫자 3을 더하면") {
                    val result = number1 + number3

                    Then("결과는 4이어야 한다") {
                        result shouldBe 4
                    }
                }
            }
        }
    }
})