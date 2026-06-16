package book.chap01.question

fun max(vararg intList: Int): Int {
    return intList.max()
}

fun min(vararg intList: Int): Int {
    return intList.min()
}

fun plusPrint(n: Int) {
    var sum = 0
    for (i in 1..n) {
        if (i != n) {
            print("$i + ")
        } else {
            print("$i = ")
        }
        sum += i
    }
    println(sum)
}

fun gaussianAddition(n: Int): Int {
    return if (n % 2 == 0) {
        n / 2 * (1 + n)
    } else {
        n / 2 * (1 + n) + (n / 2 + 1)
    }
}

fun sumOf(a: Int, b: Int): Int {
    val min = a.coerceAtMost(b)
    val max = a.coerceAtLeast(b)
    var sum = 0
    for (i in min..max) {
        sum += i
    }
    return sum
}

fun minus() {
    println("a의 값 : ")
    val a = readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException()
    var b: Int
    do {
        println("b의 값 : ")
        b = readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException()
        if (a < b) println("a보다 큰 값을 입력하세요")
    } while (a < b)
    println(a - b)
}

fun unitCount(n: Int): Int {
    if (n <= 0) return 0
    var count = 1
    do {
        val i = n / 10
        if (i > 0) {
            count++
        }
    } while (i > 10)

    return count
}

fun squarePrint(i: Int) {
    for (j in 1..i) {
        for (z in 1..i) {
            print("*")
        }
        println()
    }
}

fun triangleLB(n: Int) {
    for (i in 1..n) {
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

fun triangleLU(n: Int) {
    for (i in n downTo 1) {
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

fun triangleRU(n: Int) {
    for (i in n downTo 1) {
        for (j in n - 1 downTo i) {
            print(" ")
        }
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

fun triangleRB(n: Int) {
    for (i in 1..n) {
        for (j in n - 1 downTo i) {
            print(" ")
        }
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

fun spira(n: Int) {
    for (i in 1..n) {
        for (j in i..n - 1) {
            print(" ")
        }
        for (j in 1..< i + i) {
            print("*")
        }
        println()
    }
}

fun npira(n: Int) {
    for (i in 1..n) {
        for (j in i..n - 1) {
            print(" ")
        }
        for (j in 1..< i + i) {
            print(i)
        }
        println()
    }
}