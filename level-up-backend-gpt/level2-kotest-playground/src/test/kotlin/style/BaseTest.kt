package style

import io.kotest.core.spec.BeforeTest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength

// FunSpec은 테스트 케이스를 함수 형태로 작성할 수 있는 스타일입니다.
// 각 테스트 케이스는 `test` 블록 안에 작성됩니다.
// FunSpec은 테스트 케이스가 독립적으로 실행되며, 각 테스트는
// 다른 테스트와 상태를 공유하지 않습니다.
class BaseTest : FunSpec({
    test("addition") {
            val result = 2 + 3
            result shouldBe 5
        }

    test("한글도 작성") {
        val result = 10 - 4
        result shouldBe 6
    }
})

// DynamicTests는 동적으로 생성된 테스트 케이스를 작성할 수 있는 스타일입니다.
// 테스트 케이스는 `test` 블록 안에 작성되며, 반복적으로
// 생성된 테스트 케이스는 `forEach` 블록 안에 작성됩니다.
// 이 스타일은 테스트 케이스가 동적으로 생성되어야 할 때 유용합니다
// (예: 리스트의 각 요소에 대해 테스트를 수행할 때).
class DynamicTests: FunSpec({
    listOf(
        "sam",
        "kkk",
        "joo",
    ).forEach {
        test("{$it}의 길이 테스트") {
            it.shouldHaveLength(3)
        }
    }
})

class Callbacks: FunSpec({
    beforeEach {
        println("Hello from $it")
    }

    afterEach {
        println("Goodbye from $it")
    }

    test("글자 길이 테스트1") {
        val name = "kwang"
        println(name)
        name.shouldHaveLength(5)
    }

    test("글자 길이 테스트2") {
        val name = "kotlin"
        println(name)
        name.shouldHaveLength(6)
    }
})

val resetDatabase: BeforeTest ={
    println("reset database!")
}

class ReusableCallbacks: FunSpec({
    beforeEach(resetDatabase)

    test("데이터베이스 초기화 테스트1") {
        println("테스트1 실행")
    }

    test("데이터베이스 초기화 테스트2") {
        println("테스트2 실행")
    }
})