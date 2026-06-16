package ch02

import java.time.LocalDateTime

data class User(
    var name: String? = null,
    var age: Int? = null,
    var lastLoginAt: LocalDateTime? = null
) {

    var teamList: MutableList<Team> = mutableListOf()

    companion object {
        fun create(name: String, age: Int, teamList: List<Team>): User {
            return User()
                .apply {
                    this.name = name
                    this.age = age
                    this.addTeamList(teamList)
                }
        }
    }

    fun addTeamList(teamList: List<Team>) {
        teamList.forEach { team ->
            this.teamList.add(team)
            team.userList.add(this)
        }
    }

    fun getMyTeamUserNamePairList() = teamList
        .map { team ->
            val teamUserList = team.getUserNameList()
            team to teamUserList
        }
}

fun User.nameUpdate(name: String) {
    this.name = name
}

fun User.login() = run { this.lastLoginAt = LocalDateTime.now() }
