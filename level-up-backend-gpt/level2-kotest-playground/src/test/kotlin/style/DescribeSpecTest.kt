package style

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

// DescribeSpec은 테스트를 설명하는 형태로 작성할 수 있는 스타일입니다.
// 각 테스트는 `describe` 블록 안에 그룹화되고, `it` 블
// 록 안에 테스트 케이스가 작성됩니다.
class DescribeSpecTest : DescribeSpec({

    // 테스트 컨테이너
    describe("숫자 테스트") {
        // 테스트 케이스(터미널, 리프)
        it("숫자 1은 1과 같다") {
            1 shouldBe 1
        }
        describe("덧셈 테스트") {
            it("덧셈 테스트1") {
                val result = 2 + 3
                result shouldBe 5
            }
        }
        describe("뺄셈 테스트") {
            it("뺄셈 테스트1") {
                val result = 10 - 4
                result shouldBe 6
            }
        }
    }
})