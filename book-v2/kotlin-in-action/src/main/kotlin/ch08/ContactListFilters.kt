package ch08

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        return if (!onlyWithPhoneNumber) startsWithPrefix else { p -> startsWithPrefix(p) && p.phoneNumber != null }
    }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
)