package question

fun main() {
//    println(q1(4))
//    println(q2(3, 6))
//    println(q2(5, 15))
//    println(gcdArray(intArrayOf(12, 6, 9)))
//    println(gcdArrayV2(intArrayOf(12, 6, 3, 4), 0, 4))
//    q5(1)
//    q6(3, 1, 3)
    q8Queen(0)
}

fun q1(n: Int): Int {
    if (n <= 1) return 1
    var result = 1
    for (i in 2..n) {
        result *= i
    }
    return result
}

fun q2(x: Int, y: Int): Int {
    var a = if (x > y) x else y
    var b = if (x > y) y else x

    while (b != 0) {
        val temp = a
        a = b
        b = temp % b
    }

    return a
}

fun gcdArray(a: IntArray): Int {
    if (a.isEmpty()) return 0
    var result = a[0]
    for (i in 1..a.lastIndex) {
        result = gcd(result, a[i])
    }

    return result
}

fun gcdArrayV2(a: IntArray, idx: Int, size: Int): Int {
    if (size == 1) return a[0]
    else if (size == 2) return gcd(a[idx], a[idx + 1])
    else return gcd(a[idx], gcdArrayV2(a, idx + 1, size - 1))

}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}

fun q5(n: Int) {
    var a = n
    var ptr = -1
    var s = 0
    val nStk = IntArray(1000)
    val sStk = IntArray(1000)

    while (true) {
        if (a > 0) {
            nStk[++ptr] = a
            sStk[ptr] = s
            if (s == 0) a -= 1
            else if (s == 1) {
                a = a - 2
                s = 0
            }
            continue
        }
        do {
            a = nStk[ptr]
            s = sStk[ptr--] + 1
            if (s == 2) {
                println(a)
                if (ptr < 0) return
            }
        } while (s == 2)
    }
}

fun q6(n: Int, x: Int, y: Int) {
    val arr = arrayOf("A기둥", "B기둥", "C기둥")

    if (n > 1) {
        q6(n - 1, x, 6 - x - y)
    }
    println("${arr[x - 1]}에서 ${arr[y - 1]}으로 ${n}을 옮겼습니다.")
    if (n > 1) {
        q6(n - 1, 6 - x - y, y)
    }
}


fun q7(n: Int, x: Int, y: Int) {
    val arr = arrayOf("A기둥", "B기둥", "C기둥")

    var n = n
    var x = x
    var y = y
    val xStk = IntArray(100)
    val yStk = IntArray(100)
    val sStk = IntArray(100)
    var ptr = -1

    while (true) {
        if (n > 0) {
            xStk[++ptr] = x
            yStk[ptr] = y
            n = n - 1
            y = 6 - x - y
            continue
        }
        println("${n}을 ${x}기둥에서 ${y}기둥으로 옮겼습니다.")
    }
}

val nArr = IntArray(8)
val nFlag = BooleanArray(8)
val xFlag = BooleanArray(15)
val yFlag = BooleanArray(15)

fun q8Queen(i: Int) {

    for (j in 0..7) {

        if (nFlag[j] || xFlag[i + j] || yFlag[i - j + 7]) continue
        nArr[i] = j
        if (i == 7) {
            q8_print(nArr)
        } else {
            nFlag[j] = true
            xFlag[i + j] = true
            yFlag[i - j + 7] = true

            q8Queen(i + 1)
            nFlag[j] = false
            xFlag[i + j] = false
            yFlag[i - j + 7] = false
        }
    }
}

fun q8_print(nArr: IntArray) {
    for (row in 0..7) {
        for (col in 0..7) {
            if (nArr[col] == row) {
                print("${row} ")
            } else {
                print("x ")
            }
        }
        println()
    }
    println("====================")
}