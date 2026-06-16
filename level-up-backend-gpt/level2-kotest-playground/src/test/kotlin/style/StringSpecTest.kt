package style

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringSpecTest: StringSpec({
    "여기가 테스트명 입력" {
        "kwang".length shouldBe 5
    }
})