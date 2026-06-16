package gpt.recursion

data class State(
    val n: Int,
    val from: Int,
    val to: Int,
    val via: Int,
    var phase: Int = 0
)

fun hanoi(n: Int, from: Int, to: Int, via: Int) {
    val stack = ArrayDeque<State>()
    stack.addLast(State(n, from, to, via))
    while (stack.isNotEmpty()) {
        val current = stack.removeLast()
        when (current.phase) {
            0 -> {
                if (current.n <= 0) {
                    continue
                }
                current.phase = 1
                stack.addLast(current)
                stack.addLast(State(current.n - 1, current.from, current.via, current.to))
            }

            1 -> {
                println("Move disk ${current.n} from ${current.from} to ${current.to}")
                current.phase = 2
                stack.addLast(current)

                stack.addLast(State(current.n - 1, current.via, current.to, current.from))
            }
            2 -> {
                continue
            }
        }
    }
}

fun main() {
    hanoi(3, 1, 3, 2)
}