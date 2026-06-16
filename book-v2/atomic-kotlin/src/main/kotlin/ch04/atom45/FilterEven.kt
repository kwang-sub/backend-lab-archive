package ch04.atom45

fun filterEven(nums: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (i in nums) {
        if (i % 2 == 0) result += i
    }
    return result
}

fun main() {
    println(filterEven(listOf(1, 2, 3, 4)))
}