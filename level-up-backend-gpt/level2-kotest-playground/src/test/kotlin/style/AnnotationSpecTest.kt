package style

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class AnnotationSpecTest : AnnotationSpec() {

    @BeforeEach
    fun beforeEachTest() {
        println("Before each test")
    }


    @Test
    fun test1() {
        println("Test 1 executed")
        1 + 1 shouldBe  2
    }
}