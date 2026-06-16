package gpt.recursion

fun main() {
//    recur(1)
    recurNonRecursive(3)
}

fun recur(n: Int) {
    if (n <= 0) return
    var n = n
    var s = 0
    var ptr = -1
    val nStk = IntArray(100)
    val sStk = IntArray(100)
    while (true) {
        if (n > 0) {
            nStk[++ptr] = n
            sStk[ptr] = s
            n -= 1
            continue
        }

        do {
            if (ptr < 0) break
            n = nStk[ptr]
            s = sStk[ptr] + 1
            println(n)
            ptr--
            n -= 2
        } while (s == 2)
    }
}

fun recurNonRecursive(n0: Int) {
    val stack = ArrayDeque<Pair<Int,Int>>() // Pair<n, stage>
    stack.addLast(n0 to 0)

    while (stack.isNotEmpty()) {
        val (n, stage) = stack.removeLast()
        if (n <= 0) continue

        when (stage) {
            0 -> {
                // 왼쪽 → 출력 → 오른쪽 순으로 스택에 역push
                stack.addLast(n to 1)       // 출력 단계
                stack.addLast((n - 1) to 0) // 왼쪽 재귀
            }
            1 -> {
                println(n)
                stack.addLast((n - 2) to 0) // 오른쪽 재귀
            }
        }
    }
}
