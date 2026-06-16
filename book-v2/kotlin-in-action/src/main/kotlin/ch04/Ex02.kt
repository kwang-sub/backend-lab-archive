package ch04

fun main() {
    val user = User("현석")
    println(user.isSubscribed)

    val user1 = User("계영", false)
    println(user1.isSubscribed)
}