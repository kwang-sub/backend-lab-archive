package ch06.item39

class Sample2 {
    @ExceptionTest(ArithmeticException::class)
    fun m1() {
        var i = 0
        i = i / i
    }

    @ExceptionTest(ArithmeticException::class)
    fun m2() {
        var a = intArrayOf()
        val i = a[1]
    }

    @ExceptionTest(ArithmeticException::class)
    fun m3() {
    }

}