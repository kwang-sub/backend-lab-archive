package ch07

data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int
) : Comparable<Person>{
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::age, Person::lastName, Person::firstName)
    }
}