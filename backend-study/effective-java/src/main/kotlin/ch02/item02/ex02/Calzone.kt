package ch02.item02.ex02

class Calzone(
    private val builder: Builder
): Pizza(builder) {
    private val _sauceInSide: Boolean = builder.sauceInSide
    val sauceInSide: Boolean
        get() = _sauceInSide

    class Builder(
        val sauceInSide: Boolean
    ) : Pizza.Builder<Builder>() {
        override fun build(): Pizza {
            return Calzone(this)
        }

        override fun self(): Builder = this
    }
}