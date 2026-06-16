package ch02.item02.ex02

import java.util.*

open class Pizza(builder: Builder<*>) {
    private var _toppings: MutableSet<Topping> = mutableSetOf()

    val toppings: MutableSet<Topping>
        get() = _toppings

    init {
        _toppings = builder.toppings.clone()
    }

    abstract class Builder<T : Builder<T>> {
        val toppings: EnumSet<Topping> = EnumSet.noneOf(Topping::class.java)

        fun addTopping(topping: Topping): T {
            toppings.add(Objects.requireNonNull(topping))
            return self()
        }

        abstract fun build(): Pizza

        protected abstract fun self(): T
    }
}

enum class Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
