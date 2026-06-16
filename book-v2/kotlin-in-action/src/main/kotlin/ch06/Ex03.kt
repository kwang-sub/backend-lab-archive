package ch06

fun main() {
    val person = Person3("kwang", "sub")
    val person2 = Person3("kwang", "sub")

    println(person == person2)
    println(person.equals(42))
//    ignoreNulls(null)

    val email: String? = "kwang@asd.sdfaa"
    email?.let(::sendEmailTo)
        ?.let { println("sdfa") }
        .let { println("완료") }

}

fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun sendEmailTo(email: String) {
    println("sending email To $email")
}