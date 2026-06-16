package ch01.ko

data class Person(
    val name: String,
    val age: Int,
    val job: String,
) {

    companion object {
        fun create(name: String, age: Int, job: String): Person {
            return Person(name, age, job)
        }
    }
}

