package ch03.atom36

fun compute(input: Int): Pair<Int, String> {
    return when {
        input > 5 -> Pair(input * 2, "High")
        else -> Pair(input * 2, "Low")
    }
}


fun main() {
    println(compute(7))
    println(compute(3))

    val result: Pair<Int, String> = compute(3)
    println(result.first) // 출력: 6
    println(result.second) // 출력: Low

    val (value, description) = compute(3)
    println(value) // 출력: 6
    println(description) // 출력: Low
}