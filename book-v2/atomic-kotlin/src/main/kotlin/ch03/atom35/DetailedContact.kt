package ch03.atom35

data class DetailedContact(
    val name: String,
    val surname: String,
    val number: String,
    val address: String,
) {

}

fun main() {
    val contact = DetailedContact("m", "m", "1", "a")
    val newContact = contact.copy(name = "n")
    println(contact == newContact)
}