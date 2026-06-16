package conditional

import io.kotest.core.Tag
import io.kotest.core.spec.style.FunSpec

class TagTest: FunSpec({
    test("conditional.Linux 테스트").config(tags = setOf(Linux)) {
        println("conditional.Linux 테스트 실행")
    }
    test("conditional.Windows 테스트").config(tags = setOf(Windows)) {
        println("conditional.Windows 테스트 실행")
    }
}) {

}

object Linux: Tag()
object Windows: Tag()