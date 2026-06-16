package item04

open class Animal

class Zebra: Animal()

fun main() {
    var animal: Animal = Zebra()
    animal = Animal()
}
val DEFAULT_CAR: Car = Fiat126P()

interface CarFactory {
    fun produce() = DEFAULT_CAR
}

class Factory : CarFactory {
    override fun produce(): Car {
        return super.produce()
    }
}

open class Car

class Fiat126P: Car()