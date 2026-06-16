package item02

import item01.User

fun main() {
    val userRepo = UserRepo()
    val user1 = userRepo.user
    val user2: User = userRepo.user
    val user3: User = userRepo.user

    val users: List<User> = userRepo.users
    statedType()
}

fun statedType() {
    val value: String = JavaClass().value
    println(value.length)
}