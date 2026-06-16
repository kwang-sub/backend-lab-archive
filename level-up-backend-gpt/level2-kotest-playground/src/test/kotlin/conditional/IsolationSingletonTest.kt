package conditional

import io.kotest.core.spec.style.WordSpec
import java.util.*
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class IsolationSingletonTest : WordSpec({
    val random = UUID.randomUUID()

    "UUID 생성" should {
        println("랜덤 UUID: $random")
        "랜덤 UUID를 생성한다" {
            println("랜덤 UUID: $random")
        }
        "c" {
            println(random)
        }
    }
}) {
}