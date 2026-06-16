package ch08.item49

fun main() {
    sort(longArrayOf(1L, 2L), 2, 3)
}

fun sort(a: LongArray, offset: Int, length: Int) {
    assert(offset >= 0 && offset <= a.size)
    assert(length >= 0 && length <= a.size - offset)

    println("완료")
}