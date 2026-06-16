package ch06

class Address2(
    val streetAddress: String,
    val zipCode: Int,
    val city: String,
    val country: String,
)

class Company2(
    val name: String,
    val address: Address?,
)

class Person2(
    val name: String,
    val company: Company?,
) {
    fun printShippingLabel() {
        val address = this.company?.address ?: throw IllegalArgumentException("No address")
        with(address) {
            println(streetAddress)
            println("$zipCode $city, $country")
        }
    }
}