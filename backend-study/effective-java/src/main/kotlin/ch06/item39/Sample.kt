package ch06.item39

class Sample {

    fun m2() {}

    @Test
    fun m5() {
    }

    companion object {
        @Test
        fun m1() {
        }

        @Test
        fun m3() {
            throw RuntimeException("실패")
        }

        fun m4() {}

        fun m6() {}

        @Test
        fun m7() {
            throw RuntimeException("실패")
        }

        fun m8() {}

    }
}