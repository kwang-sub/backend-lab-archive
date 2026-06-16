package conditional

import io.kotest.assertions.nondeterministic.eventually
import io.kotest.assertions.nondeterministic.eventuallyConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class EventuallyTest : FunSpec({
    val config = eventuallyConfig {
        duration = 5.seconds
        interval = 250.milliseconds
    }
    test("비결정적 테스트") {
        eventually(config) {
            println("이 테스트는 설정된 시간 동안 성공할 때까지 반복적으로 실행됩니다.")
            1 shouldBe 1
        }
    }
}) {
}