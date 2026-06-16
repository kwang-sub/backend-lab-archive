package ch03.item10

class CaseInsensitiveString(
    val s: String
): Comparable<CaseInsensitiveString> {
    fun badEquals(other: Any?): Boolean {
        if (other is CaseInsensitiveString)
            return s.equals(other.s, true)

        if (other is String)
            return s.equals(other, true)

        return false
    }

    override fun equals(other: Any?): Boolean {
        return other is CaseInsensitiveString && other.s.equals(s, true)
    }

    override fun compareTo(other: CaseInsensitiveString): Int {
        return String.CASE_INSENSITIVE_ORDER.compare(s, other.s)
    }
}

fun main() {
    val cis = CaseInsensitiveString("Polish")
    val s = "polish"
    println("둘이 같나? ${cis.equals(s)}")

    val list = mutableListOf<Any>()
    list.add(cis)
    println(list.contains(s))
}