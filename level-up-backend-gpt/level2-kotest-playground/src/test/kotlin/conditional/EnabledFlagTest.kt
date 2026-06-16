package conditional

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.EnabledIf
import io.kotest.matchers.shouldBe

class EnabledFlagTest : FunSpec({

    val disableOnFridays: EnabledIf = {
        val dayOfWeek = java.time.LocalDate.now().dayOfWeek
        println(dayOfWeek)
        dayOfWeek != java.time.DayOfWeek.TUESDAY
    }

    test("활성화된 테스트").config(enabled = true) {
        1 + 1 shouldBe  2
    }

    test("비활성화된 테스트").config(enabledIf = disableOnFridays) {
        1 + 1 shouldBe 2
    }


})