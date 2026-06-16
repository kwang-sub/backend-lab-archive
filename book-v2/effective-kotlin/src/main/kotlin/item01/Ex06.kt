package item01

data class User(val name: String)

class UserRepository {
    private val storedUser: MutableMap<Int, String> = mutableMapOf()

    fun loadAll(): MutableMap<Int, String> = storedUser
}

fun main() {
    val userRepository = UserRepository()
    val storedUser = userRepository.loadAll()

    storedUser[4] = "Kirill"

    println(userRepository.loadAll())
}
