package modernjavainaction.study.ch09

abstract class ProcessingObject<T> {
    var successor: ProcessingObject<T>? = null

    fun handle(input: T): T {
        val r: T = handleWork(input)
        if (successor != null) {
            return successor!!.handle(r)
        }
        return r
    }

    abstract fun handleWork(input: T): T
}

class HeaderTextProcessing : ProcessingObject<String>() {
    override fun handleWork(input: String): String {
        return "From Raoul, mario and Alan: $input"
    }
}

class SpellCheckerProcessing : ProcessingObject<String>() {
    override fun handleWork(input: String): String {
        return input.replace("labda", "lambda")
    }
}

fun main() {
    val p1 = HeaderTextProcessing()
    val p2 = SpellCheckerProcessing()
    p1.successor = p2
    val result = p1.handle("Aren't labdas really sexy?!!")
    println(result)
}