package ch02

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }

        is Sum -> {
            val left = eval(e.left)
            val right = eval(e.right)
            println("sum: $left + $right")
            eval(e.right) + eval(e.left)
        }

        else -> throw IllegalArgumentException("Unknown expression")
    }
//    if (e is Num) return e.value
//    if (e is Sum) return eval(e.right) + eval(e.left)
