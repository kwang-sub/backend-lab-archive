package ch01.atom12

fun main() {
    val s = "abc"
    for (i in 0..s.lastIndex) {
        print(s[i] + 1)
    }
}