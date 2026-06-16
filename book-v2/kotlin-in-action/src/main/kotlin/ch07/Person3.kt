package ch07

class Person3(
    val name: String
) {
    val emails by lazy { loadEmails(this)}
}

fun loadEmails(person: Person3) : List<Email> {
    return listOf()
}