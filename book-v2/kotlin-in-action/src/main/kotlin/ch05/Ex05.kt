package ch05

fun sendEmail(person: Person, message: String) {
    println("send email ${person.name} $message")
}

fun Person.isAdult() = this.age >= 21

fun main() {
    val action = {person: Person, message: String -> sendEmail(person, message)}
    val nextAction = ::sendEmail

    val createPerson = ::Person
    val p = createPerson("kwang", 30)
    println(p)
    println(p.isAdult())

    val predicate = Person::isAdult
    println(predicate(p))
}
