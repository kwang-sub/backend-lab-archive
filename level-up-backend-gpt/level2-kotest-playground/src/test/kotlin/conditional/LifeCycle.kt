package conditional

import io.kotest.core.spec.style.FunSpec

class LifeCycle : FunSpec({
    beforeEach { println("beforeEach") }

    afterEach { println("afterEach") }
    beforeSpec { println("beforeContainer") }

    context("context1") {
        test("test1") {
            println("context1 test1")
        }

        test("test2") {
            println("context1 test2")
        }
    }

    test("test1") {
        println("test1")
    }

    test("test2") {
        println("test2")
    }
}) {
}