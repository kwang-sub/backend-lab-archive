package item01

import java.util.SortedSet
import java.util.TreeSet

fun main() {
    val names: SortedSet<FullName> = TreeSet()
    val person = FullName("AAA", "AAA")
    names.add(person)
    names.add(FullName("David", "Blanc"))
    names.add(FullName("Jordan", "Hansen"))
    println(names)
    println(person in names)

    person.surName = "ZZZ"
    println(names)
    println(person in names)

    println(person != person.withSurname("test"))
}

data class FullName(
    var name: String,
    var surName: String,
) : Comparable<FullName> {

    fun withSurname(surname: String) = FullName(name, surName)

    override fun toString(): String  = "$name $surName "
    override fun compareTo(other: FullName): Int {
        return if (this.surName >= other.surName) 1 else 0
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
