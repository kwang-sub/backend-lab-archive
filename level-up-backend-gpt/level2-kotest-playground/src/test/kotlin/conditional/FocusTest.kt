package conditional

import io.kotest.core.spec.style.FunSpec

class FocusTest : FunSpec({
    test("f:test2") {
        println("f:test2")
    }

    test("test1") {
        println("test1")
    }
}) {

}