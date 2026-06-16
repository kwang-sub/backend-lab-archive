package ch10

class LocationTracker(
    val serialNo: String,
    val user: User
)

class User(
    val userName: String,
    val telNo: String,
    val address: Address,
)