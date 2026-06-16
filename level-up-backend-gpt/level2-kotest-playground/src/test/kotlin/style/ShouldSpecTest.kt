package style

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.string.shouldHaveLength

class ShouldSpecTest: ShouldSpec({

    should("문자열 길이를 반환 해야한다.") {
        "kwnag".shouldHaveLength(5)
    }

    context("활성화된 블럭") {
        xshould("비활성화된 테스트") {
            "kwnag".shouldHaveLength(6)
        }

        should("활성화된 테스트") {
            "kwnag".shouldHaveLength(5)
        }
    }

    xcontext("비활성화된 블럭") {
        xshould("비활성화된 테스트") {
            "kwnag".shouldHaveLength(6)
        }

        should("활성화된 테스트") {
            "kwnag".shouldHaveLength(5)
        }
    }
})