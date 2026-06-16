package ch03.atom35

class Person(
    val name: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

data class Contact(
    val name: String,
    val number: String
)

fun main() {
    println(Person("Cleo") == Person("Cleo"))
    println(Contact("Cleo", "123") == Contact("Cleo", "123"))
}