package ch06

data class Person5(
    val name: String,
    val age: Int? = null
) {

    fun isOlderThan(other: Person5): Boolean? {
        if (age == null || other.age == null) return null

        return age > other.age
    }
}