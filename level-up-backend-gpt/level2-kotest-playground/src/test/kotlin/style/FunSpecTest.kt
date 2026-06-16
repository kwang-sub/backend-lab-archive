package style

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FunSpecTest : FunSpec({

    test("String.length는 문자열의 길이를 반환한다") {
        "kwang".length shouldBe 5
    }

    context("활성화되 블럭") {
        xtest("비활성화된 테스트") {
            1 + 1 shouldBe 3
        }

        test("활성호된 테스트") {
            1 + 1 shouldBe 2
        }
    }

    xcontext("비활성화된 블럭") {
        xtest("비활성화된 테스트") {
            1 + 1 shouldBe 3
        }

        test("활성호된 테스트") {
            1 + 1 shouldBe 2
        }
    }
})