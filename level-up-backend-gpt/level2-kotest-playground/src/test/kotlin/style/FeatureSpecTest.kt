package style

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

class FeatureSpecTest: FeatureSpec({
    feature("더하기 기능") {
        scenario("양수를 더할 수 있다") {
            1 + 1 shouldBe 2
        }
        scenario("음수를 더할 수 있다") {
            -1 + -1 shouldBe -2
        }
    }
})
