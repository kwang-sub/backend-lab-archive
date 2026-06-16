package ch04

class PrivateUser(
    override val nickname: String
) : User2

class SubScribingUser(val email: String) : User2 {
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : User2 {
    override val nickname: String = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int): String {
        return accountId.toString()
    }
}