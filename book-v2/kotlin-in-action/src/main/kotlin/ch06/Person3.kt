package ch06

class Person3(
    val firstName: String,
    val lastName: String,
) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person3 ?: return false
        return otherPerson.firstName == firstName && otherPerson.lastName == lastName
    }

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}