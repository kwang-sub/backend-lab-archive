package ch08.item53

fun main() {
    println(min(188, 123, 124))
    println(min(188, 1))
}

fun sum(vararg args: Int): Int {
    var sum: Int = 0
    for (i in args) {
        sum += i
    }
    return sum
}

//fun min(vararg args: Int): Int {
//    if (args.size == 0) throw IllegalArgumentException("인수가 1개 이상 필요합니다.")
//    var min: Int = args[0]
//    for (i in 1..<args.size) {
//        if (args[i] < min) {
//            min = args[i]
//        }
//    }
//
//    return min
//}

fun min(firstArg: Int, vararg remainIngArgs: Int): Int {
    var min = firstArg
    for (i in remainIngArgs) {
        if (i < min) min = i
    }
    return min
}