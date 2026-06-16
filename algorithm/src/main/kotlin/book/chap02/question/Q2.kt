package book.chap02.question

fun sumOf(a: IntArray): Int {
    var sum = 0
    a.forEach { sum += it }
    return sum
}

fun copy(a: IntArray, b: IntArray) {
    if (a.size != b.size) return
    for (i in 0 until a.size) {
        a[i] = b[i]
    }
}

fun rcopy(a: IntArray, b: IntArray) {
    if (a.size != b.size) return
    for (i in 0 until a.size) {
        a[a.size - i - 1] = b[i]
    }
}

fun cardConv(x: Int, r: Int, d: CharArray): Int {
    var num = x
    var count = 0
    val digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    do {
        d[count++] = digit[num % r]
        num /= r
    } while (num > 0)

    for (i in 0 until count / 2) {
        val temp = d[i]
        d[i] = d[count - i - 1]
        d[count - i - 1] = temp
    }

    return count
}

fun cardConvPrint(x: Int, r: Int) {
    var num = x
    val digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var z = 0
    var list = mutableListOf<String>()

    do {
        print(String.format("%2d |%5d", r, num))
        if (num != x) println("    ''' $z") else println()
        z = num % r
        num /= r
        println("   +---------------")
        list.add(0, z.toString())

    } while (num > 0)

    print(String.format("    %5d", num))
    println("    ''' $z")
    list.add(0, z.toString())
    println("2진수로 ${list.joinToString(separator = "")} 입니다.")
}