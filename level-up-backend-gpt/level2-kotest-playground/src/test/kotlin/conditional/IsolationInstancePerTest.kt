package conditional

import io.kotest.core.spec.style.WordSpec

class IsolationInstancePerTest : WordSpec() {
    override fun isolationMode() = io.kotest.core.spec.IsolationMode.InstancePerTest

    init {
        "a" should {
            println("a")
            "b" {
                println("b")
            }

            "c" {
                println("c")
            }
        }
    }
}
