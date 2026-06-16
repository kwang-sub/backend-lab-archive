package modernjavainaction.study.ch11

import java.util.Optional

class Person(
    var car: Car? = null,
) {
    fun getCarInsurance(): String? {
        return car?.insurance?.name ?: "UNKNOWN"
    }
}

class Car(
    var insurance: Insurance? = null,
)

class Insurance(
    var name: String? = null,
)

fun main() {
    val insurance = Insurance()
    val insuranceOptional = Optional.of(insurance)
    val insuranceOptional2 = Optional.ofNullable<Insurance>(null)

    val map: Optional<String> = insuranceOptional.map { it.name }
    val map2: Optional<String> = insuranceOptional2.map { it.name }

    insuranceOptional.filter {it.name == null}
        .ifPresent { println("rhkd")}
    println(map2)
}