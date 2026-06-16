package style

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class ExpectSpecTest : ExpectSpec({

    context("계산 테스트 블럭") {

        expect("더하기 테스트") {
            1 + 1 shouldBe 2
        }
        expect("빼기 테스트") {
            5 - 3 shouldBe 2
        }
    }
})