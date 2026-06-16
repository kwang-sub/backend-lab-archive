package ch07.atom80

data class Tag(var n: Int = 0) {
    var s: String = ""
    fun increment() = ++n
}

fun main() {
    val two = Tag(1).let {
        it.s = "let: ${it.n}"
        it.increment()
    }

    val three = Tag(2).let { tag ->
        tag.s = "let: ${tag.n}"
        tag.increment()
    }

    val fore = Tag(3).run {
        s = "run: ${this.n}"
        increment()
    }
    with(Tag(4)) {
        s = "with: ${this.n}"
        increment()
    }

    println("two = $two ")
    println("three = $three ")
    println("fore = $fore ")

}