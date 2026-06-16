package ch04

class User4 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser (email: String) = User(email.substringBefore("@"))
        fun newFacebookUser (accountId: Int) = User(accountId.toString())
    }
}