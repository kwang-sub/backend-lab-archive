package ch01.ko

sealed class Topping {
    object HAM: Topping()
    object MUSHROOM: Topping()
    object ONION: Topping()
    object SAUSAGE: Topping()
}

abstract class Pizza {
    private val toppingList = arrayListOf<Topping>()

    fun setToppingList(list: List<Topping>) {
        toppingList.clear()
        toppingList.addAll(list)
    }

    abstract class Builder {
        protected val toppingList = arrayListOf<Topping>()

        fun addTopping(topping: Topping): Builder {
            toppingList.add(topping)
            return this
        }

        abstract fun build(): Pizza
    }

    override fun toString(): String {
        return "Pizza(toppingList=$toppingList)"
    }
}