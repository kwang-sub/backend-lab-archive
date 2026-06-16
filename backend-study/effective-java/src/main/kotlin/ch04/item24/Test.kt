package ch04.item24

class Test {
    companion object {
        enum class Tt{
            Test, Kwang1
        }
    }

    enum class Qq {
        Test, Kwang2
    }
}

fun main() {
    Test.Companion.Tt.Test
    Test.Qq.Test
    Test.Companion.Tt.Kwang1
}