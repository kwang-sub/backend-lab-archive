package ch06

class Address(
    val streetAddress: String,
    val zipCode: Int,
    val city: String,
    val country: String
)

class Company(
    val name: String,
    val address: Address?
)

class Person(
    val name: String,
    val company: Company?,
) {
    fun countryName(): String {
        return this.company?.address?.country ?: "Unknown"
    }
}