package ch06

class Employee(
    val name: String,
    val manager: Employee?,
) {
    fun managerName(): String? {
        return this.manager?.name
    }
}
