package ch02.item02.ex02

class NyPizza(
    private val builder: Builder
) : Pizza(builder) {
    private val _size: Size = builder.size
    val size: Size
        get() = _size

    class Builder(
        val size: Size
    ) : Pizza.Builder<Builder>() {
        override fun build(): Pizza {
            return NyPizza(this)
        }

        override fun self(): Builder = this
    }

}

enum class Size { SMALL, MEDIUM, LARGE }