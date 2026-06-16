package ch07.item46

fun main() {
    val freq = hashMapOf<String, Long>(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "10" to 10,
        "11" to 11,
        "10" to 11,
    )
    val take = freq.keys.sortedWith(compareByDescending<String> { freq[it] }.thenByDescending { it })
        .take(1)
        .toList()

    println(take)
}