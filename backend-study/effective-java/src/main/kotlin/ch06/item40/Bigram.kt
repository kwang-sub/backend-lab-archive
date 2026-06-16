package ch06.item40

class Bigram(
    private val first: Char,
    private val second: Char,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bigram

        if (first != other.first) return false
        if (second != other.second) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + second.hashCode()
        return result
    }
}

fun main() {
    val s = hashSetOf<Bigram>()
    for (i in 1..10) {
        for (ch in 'a'..'z') {
            s.add(Bigram(ch, ch))
        }
    }
    println(s.size)
}