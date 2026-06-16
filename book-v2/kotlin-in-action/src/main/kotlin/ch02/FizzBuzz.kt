package ch02

fun fizzbuzz(i: Int) =
    when {
        i % 15 == 0 -> "FizzBuzz"
        i % 3 == 0 -> "Fizz"
        i % 5 == 0 -> "Buzz"
        else -> "$i"
    }

fun main() {
    for (i in 1..100) {
//        println(fizzbuzz(i))
    }

    for (i in 1 until  100 step 2) {
        println(fizzbuzz(i))
    }
}