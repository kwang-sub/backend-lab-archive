package ch02

data class Team(
    val name: String,

    ) {

    val userList: MutableList<User> = mutableListOf()

    fun getUserNameList(): List<String> = userList
        .mapNotNull { user ->
            user.name
        }


    companion object {
        fun create(name: String): Team = Team(name)
    }
}
